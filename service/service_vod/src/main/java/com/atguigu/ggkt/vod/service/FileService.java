package com.atguigu.ggkt.vod.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author : lishang
 * @date : 2023/7/4 17:41
 */
public interface FileService {

    /**
     * 头像上传接口
     * @param file file
     * @return uploadUrl
     */
    String upload(MultipartFile file);
}
