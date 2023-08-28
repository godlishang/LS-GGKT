package com.atguigu.ggkt.vod.controller;



import com.atguigu.ggkt.model.vod.Teacher;
import com.atguigu.ggkt.vo.vod.TeacherQueryVo;
import com.atguigu.ggkt.vod.service.TeacherService;
import com.atguigu.resultUtils.Result;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author lishang
 * @since 2023-02-27
 */
@Api(tags = "讲师管理接口")
@RestController
@RequestMapping("/admin/vod/teacher")
@CrossOrigin
public class TeacherController {

    @Resource
    private TeacherService teacherService;

    @ApiOperation("查询所有讲师")
    @GetMapping("findAll")
    public Result<List<Teacher>> findAll(){

        List<Teacher> teachers = teacherService.list();

        return Result.ok(teachers).message("查询数据成功");
    }

    @ApiOperation("逻辑删除讲师")
    @DeleteMapping("/remove/{id}")
    public Result<Boolean> removeById(@ApiParam(name = "id",value = "ID",required = true) @PathVariable String id){
        boolean isSuccess = teacherService.removeById(id);
        if (isSuccess){
            return Result.ok();
        }else {
            return Result.fail();
        }
    }

    /**
     * <a href="http://127.0.0.1:8301/admin/vod/teacher/1/2">...</a>
     * @param page 1
     * @param limit 2
     * @param teacherQueryVo 查询参数
     * @return Result<IPage<Teacher>>
     */
    @ApiOperation("获取分页列表")
    @PostMapping("/findQueryPage/{page}/{limit}")
    public Result<IPage<Teacher>> index(@ApiParam(name = "page",value = "当前页码",required = true) @PathVariable Long page,
                                        @ApiParam(name = "limit",value = "每页记录数",required = true)@PathVariable Long limit,
                                        @ApiParam(name = "teacherVo",value = "查询对象")@RequestBody(required = false) TeacherQueryVo teacherQueryVo){

        //创建page对象，传递当前页和每页记录数
        Page<Teacher> pageParam = new Page<>(page, limit);

        //不能在if的括号中直接使用teacherQueryVo.getName() != null，当没有值传入时会报空指针异常
        String name = teacherQueryVo.getName();
        Integer level = teacherQueryVo.getLevel();
        String joinDateBegin = teacherQueryVo.getJoinDateBegin();
        String joinDateEnd = teacherQueryVo.getJoinDateEnd();

        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(name)){
            queryWrapper.like("name",teacherQueryVo.getName());
        }
        if (!StringUtils.isEmpty(level)){
            queryWrapper.like("level",teacherQueryVo.getLevel());
        }
        if (!StringUtils.isEmpty(joinDateBegin)){
            queryWrapper.like("joinDateBegin",teacherQueryVo.getJoinDateBegin());
        }
        if (!StringUtils.isEmpty(joinDateEnd)){
            queryWrapper.like("joinDateEnd",teacherQueryVo.getJoinDateEnd());
        }

        //调用方法获得分页查询结果
        IPage<Teacher> pageModel = teacherService.page(pageParam, queryWrapper);

        return Result.ok(pageModel);
    }

    @ApiOperation(value = "新增")
    @PostMapping("saveTeacher")
    public Result<Boolean> saveTeacher(@RequestBody Teacher teacher){

        boolean result = teacherService.save(teacher);
        return Result.ok(result);
    }

    @ApiOperation(value = "获取")
    @GetMapping("getTeacher/{id}")
    public Result<Teacher> getTeacher(@PathVariable Long id){
        //try {
        //    int i = 10 / 0;
        //}catch (Exception e){
        //    throw new GgktException(201,"执行了自定义异常处理，GgktException");
        //}
        Teacher teacher = teacherService.getById(id);
        return Result.ok(teacher);
    }

    @ApiOperation(value = "修改")
    @PostMapping("updateTeacher")
    public Result<Boolean> updateTeacher(@RequestBody Teacher teacher){

        boolean result = teacherService.updateById(teacher);
        return Result.ok(result);
    }


    @ApiOperation(value = "批量删除")
    @DeleteMapping("batchRemove")
    public Result<Boolean> batchRemove(@RequestBody List<Long> idList){

        boolean result = teacherService.removeByIds(idList);
        return Result.ok(result);
    }

}

