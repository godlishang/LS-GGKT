package com.atguigu.ggkt.vod.service;

import com.atguigu.ggkt.model.vod.Video;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author lishang
 * @since 2023-07-12
 */
public interface VideoService extends IService<Video> {

    /**
     * 根据courseId删除小节
     * @param id courseId
     */
    void removeVideoByCourseId(Long id);

    /**
     * 根据小节Id删除小节视频
     * @param id 小节id
     */
    void removeVideoById(Long id);
}
