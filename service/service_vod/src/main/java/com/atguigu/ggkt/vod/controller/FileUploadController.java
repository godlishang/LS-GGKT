package com.atguigu.ggkt.vod.controller;

import com.atguigu.ggkt.vod.service.FileService;
import com.atguigu.resultUtils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * CrossOrigin解决跨域问题
 * @author : lishang
 * @date : 2023/7/4 17:38
 */
@Api(tags = "文件上传接口")
@RestController
@RequestMapping("/admin/vod/file")
@CrossOrigin
public class FileUploadController {

    @Resource
    private FileService fileService;

    @ApiOperation(value = "文件上传")
    @PostMapping("upload")
    public Result<String> upload(
            @ApiParam(name = "file",value = "文件",required = true)
            @RequestParam("file")MultipartFile file
            ){
        String url = fileService.upload(file);

        return Result.ok(url).message("文件上传成功");
    }
}

