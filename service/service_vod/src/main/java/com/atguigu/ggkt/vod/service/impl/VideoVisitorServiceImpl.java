package com.atguigu.ggkt.vod.service.impl;

import com.atguigu.ggkt.vo.vod.VideoVisitorCountVo;
import com.atguigu.ggkt.vod.mapper.VideoVisitorMapper;
import com.atguigu.ggkt.vod.service.VideoVisitorService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author: lishang2
 * @create: 2023-08-31
 */
@Service
public class VideoVisitorServiceImpl implements VideoVisitorService {

    @Resource
    private VideoVisitorMapper videoVisitorMapper;
    @Override
    public Map<String, Object> findCount(Long courseId, String startDate, String endDate) {
        HashMap<String, Object> userCount = new HashMap<>(2);
        List<VideoVisitorCountVo> count = videoVisitorMapper.findCount(courseId, startDate, endDate);

        List<String> timeList = count.stream().map(VideoVisitorCountVo::getJoinTime).collect(Collectors.toList());
        List<Integer> userCountList = count.stream().map(VideoVisitorCountVo::getUserCount).collect(Collectors.toList());
        userCount.put("xData",timeList);
        userCount.put("yData",userCountList);

        return userCount;
    }
}
