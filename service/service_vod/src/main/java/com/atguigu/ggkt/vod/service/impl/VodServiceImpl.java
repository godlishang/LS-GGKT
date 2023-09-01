package com.atguigu.ggkt.vod.service.impl;

import com.atguigu.ggkt.vod.service.VodService;
import com.atguigu.ggkt.vod.util.ConstantPropertiesUtil;
import com.qcloud.vod.VodUploadClient;
import com.qcloud.vod.model.VodUploadRequest;
import com.qcloud.vod.model.VodUploadResponse;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.vod.v20180717.VodClient;
import com.tencentcloudapi.vod.v20180717.models.DeleteMediaRequest;
import com.tencentcloudapi.vod.v20180717.models.DeleteMediaResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author: lishang2
 * @create: 2023-09-01
 */
@Service
public class VodServiceImpl implements VodService {

    @Override
    public String uploadVod(MultipartFile file) {

        try {
            String originalFilename = file.getOriginalFilename();

            VodUploadClient vodUploadClient = new VodUploadClient(ConstantPropertiesUtil.ACCESS_KEY_ID,
                    ConstantPropertiesUtil.ACCESS_KEY_SECRET);

            VodUploadRequest vodUploadRequest = new VodUploadRequest();
            //视频地址
            vodUploadRequest.setMediaFilePath(originalFilename);
            //指定任务流
            vodUploadRequest.setProcedure("LongVideoPreset");
            //调用上传方法，传入接入点地域及上传请求
            VodUploadResponse response = vodUploadClient.upload("ap-guangzhou", vodUploadRequest);
            //返回文件id保存到业务表，用于控制视频播放
            return response.getFileId();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeVideo(String videoSourceId) {

        // 实例化一个认证对象，入参需要传入腾讯云账户secretId，secretKey,此处还需注意密钥对的保密
        Credential cred = new Credential(ConstantPropertiesUtil.ACCESS_KEY_ID,
                        ConstantPropertiesUtil.ACCESS_KEY_SECRET);

        // 实例化要请求产品的client对象,clientProfile是可选的
        VodClient vodClient = new VodClient(cred, "");

        // 实例化一个请求对象,每个接口都会对应一个request对象
        DeleteMediaRequest deleteMediaRequest = new DeleteMediaRequest();
        deleteMediaRequest.setFileId(videoSourceId);
        // 返回的resp是一个DeleteMediaResponse的实例，与请求对象对应
        DeleteMediaResponse deleteMediaResponse = null;
        try {
            deleteMediaResponse = vodClient.DeleteMedia(deleteMediaRequest);
        } catch (TencentCloudSDKException e) {
            throw new RuntimeException(e);
        }
        // 输出json格式的字符串回包
        System.out.println(deleteMediaResponse);
    }
}
