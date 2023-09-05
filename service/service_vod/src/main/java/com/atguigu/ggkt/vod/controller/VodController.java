package com.atguigu.ggkt.vod.controller;

import com.atguigu.ggkt.vod.service.VodService;
import com.atguigu.resultUtils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @author: lishang2
 * @create: 2023-09-01
 */
@Api(tags = "腾讯云点播")
@RestController
@RequestMapping("/admin/vod")
//@CrossOrigin
public class VodController {

    @Resource
    private VodService vodService;

    @PostMapping("upload")
    public Result<Object> uploadVod(@ApiParam(name = "file",value = "文件")
                                    @RequestParam("file")MultipartFile file){
        String fileId = vodService.uploadVod(file);

        return Result.ok(fileId);
    }

    @DeleteMapping("remove/{videoSourceId}")
    public Result<Object> removeVideo(@PathVariable String videoSourceId){
        vodService.removeVideo(videoSourceId);
        return Result.ok(null);
    }
}
