package com.atguigu.ggkt.vod.service.impl;

import com.atguigu.ggkt.model.vod.Course;
import com.atguigu.ggkt.model.vod.CourseDescription;
import com.atguigu.ggkt.model.vod.Subject;
import com.atguigu.ggkt.model.vod.Teacher;
import com.atguigu.ggkt.vo.vod.CourseFormVo;
import com.atguigu.ggkt.vo.vod.CoursePublishVo;
import com.atguigu.ggkt.vo.vod.CourseQueryVo;
import com.atguigu.ggkt.vod.mapper.CourseDescriptionMapper;
import com.atguigu.ggkt.vod.mapper.CourseMapper;
import com.atguigu.ggkt.vod.mapper.SubjectMapper;
import com.atguigu.ggkt.vod.mapper.TeacherMapper;
import com.atguigu.ggkt.vod.service.ChapterService;
import com.atguigu.ggkt.vod.service.CourseDescriptionService;
import com.atguigu.ggkt.vod.service.CourseService;
import com.atguigu.ggkt.vod.service.VideoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author lishang
 * @since 2023-07-12
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Resource
    private CourseMapper courseMapper;
    @Resource
    private TeacherMapper teacherMapper;
    @Resource
    private SubjectMapper subjectMapper;
    @Resource
    private CourseDescriptionMapper courseDescriptionMapper;
    @Resource
    private VideoService videoService;
    @Resource
    private ChapterService chapterService;
    @Resource
    private CourseDescriptionService courseDescriptionService;

    @Override
    public Map<String, Object> findPage(Page<Course> pageParam, CourseQueryVo courseQueryVo) {
        //获取条件值
        String title = courseQueryVo.getTitle();
        Long subjectId = courseQueryVo.getSubjectId();
        Long subjectParentId = courseQueryVo.getSubjectParentId();
        Long teacherId = courseQueryVo.getTeacherId();

        //封装条件
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(title)){
            queryWrapper.like("title",title);
        }
        if (!StringUtils.isEmpty(subjectId)){
            queryWrapper.eq("subject_id",subjectId);
        }
        if (!StringUtils.isEmpty(subjectParentId)){
            queryWrapper.eq("subject_parent_id",subjectParentId);
        }
        if (!StringUtils.isEmpty(teacherId)){
            queryWrapper.eq("teacher_id",teacherId);
        }

        //调用方法查询
        Page<Course> pages = courseMapper.selectPage(pageParam, queryWrapper);
        //总记录数
        long totalCount = pages.getTotal();
        //总页数
        long totalPage = pages.getPages();
        //当前页
        long currentPage = pages.getCurrent();
        //每页记录数
        long size = pages.getSize();

        List<Course> records = pages.getRecords();
        //遍历封装讲师和分类名称
        records.forEach(this::getTeacherOrSubjectName);

        //封装返回数据
        Map<String, Object> map = new HashMap<>(3);
        map.put("totalCount",totalCount);
        map.put("totalPage",totalPage);
        map.put("records",records);

        return map;
    }

    @Override
    public Long saveCourseInfo(CourseFormVo courseFormVo) {
        //保存课程基本信息
        Course course = new Course();
        BeanUtils.copyProperties(courseFormVo,course);
        courseMapper.insert(course);

        //保存课程详情信息
        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setId(course.getId());
        courseDescription.setDescription(courseFormVo.getDescription());
        courseDescription.setCreateTime(new Date());
        courseDescription.setUpdateTime(new Date());
        courseDescription.setIsDeleted(0);
        courseDescription.setCourseId(course.getId());
        courseDescriptionMapper.insert(courseDescription);

        return course.getId();
    }

    @Override
    public CourseFormVo getCourseFormVoById(Long id) {
        //从course表中取数据
        Course course = courseMapper.selectById(id);
        if (course == null){
            return null;
        }
        //从course_description表中取数据
        QueryWrapper<CourseDescription> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", course.getId());
        CourseDescription courseDescription = courseDescriptionMapper.selectOne(queryWrapper);

        CourseFormVo courseFormVo = new CourseFormVo();
        BeanUtils.copyProperties(course,courseFormVo);
        if (courseDescription != null){
            courseFormVo.setDescription(courseDescription.getDescription());
        }

        return courseFormVo;
    }

    @Override
    public void updateCourseById(CourseFormVo courseFormVo) {
        //修改课程基本信息
        Course course = new Course();
        BeanUtils.copyProperties(courseFormVo,course);
        courseMapper.updateById(course);
        //修改课程详情信息
        QueryWrapper<CourseDescription> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id",courseFormVo.getId());
        CourseDescription courseDescription = courseDescriptionMapper.selectOne(queryWrapper);
        courseDescription.setDescription(StringUtils.isEmpty(courseFormVo.getDescription())?"":courseFormVo.getDescription());
        courseDescription.setId(course.getId());
        courseDescriptionMapper.updateById(courseDescription);
    }

    @Override
    public CoursePublishVo getCoursePublishVoById(Long id) {
        return courseMapper.selectCoursePublishVoById(id);
    }

    @Override
    public boolean publishCourseById(Long id) {
        Course course = new Course();
        course.setId(id);
        course.setStatus(1);
        course.setPublishTime(new Date());
        this.courseMapper.updateById(course);
        return true;
    }

    @Override
    public void removeCourseById(Long id) {

        //根据课程id删除小节
        videoService.removeVideoByCourseId(id);
        //根据课程id删除章节
        chapterService.removeChapterByCourseId(id);
        //根据课程id删除描述
        courseDescriptionService.removeCourseDescriptionByCourseId(id);
        //根据课程id删除课程
        baseMapper.deleteById(id);
    }

    public void getTeacherOrSubjectName(Course course){
        //查询讲师名称
        Teacher teacher = teacherMapper.selectById(course.getTeacherId());
        if (teacher != null){
            course.getParam().put("teacherName",teacher.getName());
        }

        //查询分类名称
        Subject subjectOne = subjectMapper.selectById(course.getSubjectParentId());
        if (subjectOne != null){
            course.getParam().put("subjectParentTitle",subjectOne.getTitle());
        }
        Subject subjectTwo = subjectMapper.selectById(course.getSubjectId());
        if (subjectTwo != null){
            course.getParam().put("subjectTitle",subjectTwo.getTitle());
        }
    }
}
