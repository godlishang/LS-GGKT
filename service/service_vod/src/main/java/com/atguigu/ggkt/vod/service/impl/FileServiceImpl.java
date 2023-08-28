package com.atguigu.ggkt.vod.service.impl;

import com.alibaba.fastjson.JSON;
import com.atguigu.ggkt.vod.service.FileService;
import com.atguigu.ggkt.vod.util.ConstantPropertiesUtil;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

/**
 * @author : lishang
 * @date : 2023/7/4 17:41
 */
@Service
@Slf4j
public class FileServiceImpl implements FileService {

    @Override
    public String upload(MultipartFile file) {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = ConstantPropertiesUtil.END_POINT;

        String bucketName = ConstantPropertiesUtil.BUCKET_NAME;
        // 1 初始化用户身份信息（secretId, secretKey）。
        String secretId = ConstantPropertiesUtil.ACCESS_KEY_ID;
        String secretKey = ConstantPropertiesUtil.ACCESS_KEY_SECRET;
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);

        // 2 设置 bucket 的地域
        // clientConfig 中包含了设置 region, https(默认 http),超时, 代理等 set 方法
        Region region = new Region(ConstantPropertiesUtil.END_POINT);
        ClientConfig clientConfig = new ClientConfig(region);
        // 这里建议设置使用 https 协议
        // 从 5.6.54 版本开始，默认使用了 https
        clientConfig.setHttpProtocol(HttpProtocol.https);
        // 3 生成 cos 客户端。
        COSClient cosClient = new COSClient(cred, clientConfig);

        try {
            //指定要上传的文件
            InputStream inputStream = file.getInputStream();
            //指定文件要上传到存储桶的路径
            // 指定文件上传到 COS 上的路径，即对象键。例如对象键为folder/picture.jpg，则表示将文件 picture.jpg 上传到 folder 路径下
            String key = UUID.randomUUID().toString().replace("-","")
                + file.getOriginalFilename();
            String dateUrl = new DateTime().toString("yyyy/MM/dd");
            //xxxx/xx/xx/.jpg存储在cos中
            key = dateUrl+ "/" + key;

            ObjectMetadata objectMetadata = new ObjectMetadata();
            PutObjectRequest putObjectRequest = new PutObjectRequest(
                    bucketName,
                    key,
                    inputStream,
                    objectMetadata);

            PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
            log.info(JSON.toJSONString(putObjectResult));
            //https://ggkt-atguigu-1310644373.cos.ap-beijing.myqcloud.com/01.jpg
            return "https://" + bucketName + "." + "cos" + "." + endpoint + ".myqcloud.com/" + key;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}

