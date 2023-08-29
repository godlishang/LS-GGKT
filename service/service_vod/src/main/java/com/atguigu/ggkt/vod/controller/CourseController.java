package com.atguigu.ggkt.vod.controller;


import com.atguigu.ggkt.model.vod.Course;
import com.atguigu.ggkt.vo.vod.CourseFormVo;
import com.atguigu.ggkt.vo.vod.CoursePublishVo;
import com.atguigu.ggkt.vo.vod.CourseQueryVo;
import com.atguigu.ggkt.vod.service.CourseService;
import com.atguigu.resultUtils.Result;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author lishang
 * @since 2023-07-12
 */
@Api(tags = "课程分类接口")
@RestController
@RequestMapping("/admin/vod/course")
@CrossOrigin
public class CourseController {

    @Resource
    private CourseService courseService;

    @ApiOperation("新增")
    @PostMapping("save")
    public Result<Object> save(@RequestBody CourseFormVo courseFormVo){
        Long courseId = courseService.saveCourseInfo(courseFormVo);

        return Result.ok(courseId);
    }

    @ApiOperation("获取分页列表")
    @GetMapping("{page}/{limit}")
    public Result<Object> index(@ApiParam(name = "page",value = "当前页码",required = true)@PathVariable Long page,
                                @ApiParam(name = "limit",value = "每页记录数",required = true)@PathVariable Long limit,
                                @ApiParam(name = "courseQueryVo",value = "查询对象",required = false)CourseQueryVo courseQueryVo){

        Page<Course> pageParam = new Page<>(page,limit);
        Map<String, Object> map = courseService.findPage(pageParam, courseQueryVo);

        return Result.ok(map);
    }

    @ApiOperation("根据id获取课程信息")
    @GetMapping("get/{id}")
    public Result<Object> getCourseFormVoById(@PathVariable Long id){
        CourseFormVo courseFormVo = courseService.getCourseFormVoById(id);
        return Result.ok(courseFormVo);
    }

    @ApiOperation("根据id修改课程信息")
    @PostMapping("update")
    public Result<Object> update(@RequestBody CourseFormVo courseFormVo){
        courseService.updateCourseById(courseFormVo);
        //将课程Id返回给前端，便于前端回显
        return Result.ok(courseFormVo.getId());
    }

    @ApiOperation("根据id获取课程发布信息")
    @GetMapping("getCoursePublishVo/{id}")
    public Result<Object> getCoursePublishVo(@ApiParam(value = "课程ID",required = true) @PathVariable Long id){
        CoursePublishVo coursePublishVo = courseService.getCoursePublishVoById(id);
        return Result.ok(coursePublishVo);
    }

    @ApiOperation("根据id发布课程")
    @PutMapping("publishCourseById/{id}")
    public Result<Object> publishCourseById(@ApiParam(value = "课程ID",required = true) @PathVariable Long id){
        boolean result = courseService.publishCourseById(id);
        return Result.ok(result);
    }

}

