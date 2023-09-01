package com.atguigu.ggkt.vod.controller;


import com.atguigu.ggkt.model.vod.Video;
import com.atguigu.ggkt.vod.service.VideoService;
import com.atguigu.resultUtils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author lishang
 * @since 2023-07-12
 */
@Api(tags = "课程小结(课时)")
@RestController
@RequestMapping("/admin/vod/video")
@CrossOrigin
public class VideoController {

    @Resource
    private VideoService videoService;

    @ApiOperation(value = "获取")
    @GetMapping("get/{id}")
    public Result<Object> get(@PathVariable Long id) {
        Video video = videoService.getById(id);
        return Result.ok(video);
    }

    @ApiOperation(value = "新增")
    @PostMapping("save")
    public Result<Object> save(@RequestBody Video video) {
        videoService.save(video);
        return Result.ok(null);
    }

    @ApiOperation(value = "修改")
    @PostMapping("update")
    public Result<Object> updateById(@RequestBody Video video) {
        videoService.updateById(video);
        return Result.ok(null);
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("remove/{id}")
    public Result<Object> remove(@PathVariable Long id) {
        videoService.removeVideoById(id);
        return Result.ok(null);
    }

}

