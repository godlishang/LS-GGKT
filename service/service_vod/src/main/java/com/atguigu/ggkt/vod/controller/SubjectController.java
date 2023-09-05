package com.atguigu.ggkt.vod.controller;


import com.atguigu.ggkt.model.vod.Subject;
import com.atguigu.ggkt.vod.service.SubjectService;
import com.atguigu.resultUtils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author lishang
 * @since 2023-07-05
 */
@Api(tags = "课程分类管理")
@RestController
@RequestMapping("/admin/vod/subject")
//@CrossOrigin
public class SubjectController {

    @Resource
    private SubjectService subjectService;

    @ApiOperation("查询下一层的可成分类")
    @GetMapping("getChildSubject/{id}")
    public Result<List<Subject>> getChildSubject(@PathVariable Long id){

        List<Subject> subjectList = subjectService.findChildSubject(id);

        return Result.ok(subjectList);
    }

    @ApiOperation("导出")
    @GetMapping(value = "exportData")
    public void exportData(HttpServletResponse response){
        subjectService.exportData(response);
    }

    @ApiOperation("导入")
    @PostMapping(value = "importData")
    public void importData(MultipartFile file){
        subjectService.importData(file);
    }

}

