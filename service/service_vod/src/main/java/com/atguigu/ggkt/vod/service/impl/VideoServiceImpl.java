package com.atguigu.ggkt.vod.service.impl;

import com.atguigu.ggkt.model.vod.Video;
import com.atguigu.ggkt.vod.mapper.VideoMapper;
import com.atguigu.ggkt.vod.service.VideoService;
import com.atguigu.ggkt.vod.service.VodService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author lishang
 * @since 2023-07-12
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {

    @Resource
    private VodService vodService;

    @Override
    public void removeVideoByCourseId(Long id) {
        //1、根据课程id查询所有的小节
        QueryWrapper<Video> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id",id);
        List<Video> videoList = baseMapper.selectList(queryWrapper);
        //2、便利list获取所有小节中的视频id
        for (Video video : videoList) {
            String videoSourceId = video.getVideoSourceId();
            //3、若视频id不为空，删除视频
            if (StringUtils.isNotBlank(videoSourceId)){
                vodService.removeVideo(videoSourceId);
            }
        }
        //4、根据课程id删除小节
        baseMapper.delete(queryWrapper);
    }

    @Override
    public void removeVideoById(Long id) {
        //1、根据小节id查询小节中的视频id
        Video video = baseMapper.selectById(id);
        //2、获取视频id
        String videoSourceId = video.getVideoSourceId();
        //3、如果视频id不为空，调用方法删除
        if (StringUtils.isNotBlank(videoSourceId)){
            vodService.removeVideo(videoSourceId);
        }
        //4、删除小节
        baseMapper.deleteById(id);
    }
}
