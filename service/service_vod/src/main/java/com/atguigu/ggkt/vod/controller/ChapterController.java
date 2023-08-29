package com.atguigu.ggkt.vod.controller;


import com.atguigu.ggkt.model.vod.Chapter;
import com.atguigu.ggkt.vo.vod.ChapterVo;
import com.atguigu.ggkt.vod.service.ChapterService;
import com.atguigu.resultUtils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author lishang
 * @since 2023-07-12
 */
@Api(tags = "点播管理接口")
@RestController
@RequestMapping("/admin/vod/chapter")
@CrossOrigin
public class ChapterController {

    @Resource
    private ChapterService chapterService;

    @ApiOperation("嵌套章节数据列表")
    @GetMapping("getNestedTreeList/{courseId}")
    public Result<Object> getNestedTreeList(@ApiParam(value = "课程ID",required = true)
                                            @PathVariable Long courseId){

        List<ChapterVo> chapterVoList = chapterService.getNestedTreeList(courseId);

        return Result.ok(chapterVoList);
    }

    @ApiOperation("添加章节")
    @PostMapping("save")
    public Result<Object> save(@RequestBody Chapter chapter){
        chapterService.save(chapter);
        return Result.ok(null);
    }

    @ApiOperation("修改-根据id查询")
    @GetMapping("get/{id}")
    public Result<Object> get(@PathVariable Long id){
        Chapter chapter = chapterService.getById(id);
        return Result.ok(chapter);
    }

    @ApiOperation("修改-最终实现")
    @PostMapping("update")
    public Result<Object> update(@RequestBody Chapter chapter){
        chapterService.updateById(chapter);
        return Result.ok(null);
    }

    @ApiOperation("删除章节")
    @DeleteMapping("remove/{id}")
    public Result<Object> remove(@PathVariable Long id){
        chapterService.removeById(id);
        return Result.ok(null);
    }

}

