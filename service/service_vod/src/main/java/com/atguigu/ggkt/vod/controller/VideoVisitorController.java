package com.atguigu.ggkt.vod.controller;

import com.atguigu.ggkt.vod.service.VideoVisitorService;
import com.atguigu.resultUtils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author: ls
 * @create: 2023-08-31
 */
@Api(value = "VideoVisitor管理", tags = "VideoVisitor管理")
@RequestMapping(value="/admin/vod/videoVisitor")
@CrossOrigin
@RestController
public class VideoVisitorController {

    @Resource
    private VideoVisitorService videoVisitorService;

    @ApiOperation("显示统计数据")
    @GetMapping("findCount/{courseId}/{startDate}/{endDate}")
    public Result<Object> showChart(@ApiParam("课程id") @PathVariable Long courseId,
                                    @ApiParam("开始时间") @PathVariable String startDate,
                                    @ApiParam("结束时间") @PathVariable String endDate){
        Map<String,Object> map = videoVisitorService.findCount(courseId, startDate, endDate);

        return Result.ok(map);
    }
}
