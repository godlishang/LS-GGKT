package com.atguigu.ggkt.vod.service;

import com.atguigu.ggkt.model.vod.Course;
import com.atguigu.ggkt.vo.vod.CourseFormVo;
import com.atguigu.ggkt.vo.vod.CoursePublishVo;
import com.atguigu.ggkt.vo.vod.CourseQueryVo;
import com.atguigu.resultUtils.Result;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.annotation.Resource;
import java.util.Map;
/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author lishang
 * @since 2023-07-12
 */
public interface CourseService extends IService<Course> {

    /**
     * 课程列表查询接口
     * @param pageParam 分页参数
     * @param courseQueryVo 查询参数
     * @return Map<String, Objects>
     */
    Map<String, Object> findPage(Page<Course> pageParam, CourseQueryVo courseQueryVo);

    /**
     * 添加课程基本信息
     * @param courseFormVo courseFormVo
     * @return Long 课程id
     */
    Long saveCourseInfo(CourseFormVo courseFormVo);

    /**
     * 根据id查询课程信息
     * @param id 课程id
     * @return CourseFormVo
     */
    CourseFormVo getCourseFormVoById(Long id);

    /**
     * 根据id修改课程信息
     * @param courseFormVo 课程info
     */
    void updateCourseById(CourseFormVo courseFormVo);

    /**
     * 根据id获取课程发布信息
     * @param id 课程id
     * @return CoursePublishVo
     */
    CoursePublishVo getCoursePublishVoById(Long id);

    /**
     * 根据id发布课程
     * @param id 课程id
     * @return boolean
     */
    boolean publishCourseById(Long id);
}
