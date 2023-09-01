package com.atguigu.ggkt.vod.service;

import org.springframework.web.multipart.MultipartFile;

public interface VodService {
    /**
     * 上传视频到腾讯云点播平台
     * 返回fileId后，前端会调用视频保存save接口保存视频进库
     * @param file file
     * @return fileId
     */
    String uploadVod(MultipartFile file);

    /**
     * 删除视频
     * @param videoSourceId 视频id
     */
    void removeVideo(String videoSourceId);
}
