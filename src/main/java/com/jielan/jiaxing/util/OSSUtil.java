package com.jielan.jiaxing.util;

/**
 * Created by Administrator on 2016/12/13.
 */
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**OSS上传图片
 * Created by YJ on 2016/1/5.
 */
public class OSSUtil {

    public static void upload(MultipartFile file, String fileName){
        //上传到oss
        OSSClient ossClient = new OSSClient(
                "qyy.jielanwx.com", "wHaJouFNAPqjPh0A",
                "y5ExAWtJz2Y57hHLIn0qBaMqufp5Wb");
        try {
            InputStream inputStream = file.getInputStream();
            // 创建上传Object的Metadata
            ObjectMetadata meta = new ObjectMetadata();
            // 必须设置ContentLength
            meta.setContentLength(inputStream.available());
            meta.setContentType("image/jpeg");
            // 上传Object.
            PutObjectResult result = ossClient.putObject("jielan", "jielanwx/jx/face-recognition/upload/"+fileName, inputStream, meta);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}

