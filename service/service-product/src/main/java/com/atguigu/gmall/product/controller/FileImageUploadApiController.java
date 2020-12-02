package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;
import lombok.SneakyThrows;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("admin/product")
@CrossOrigin
public class FileImageUploadApiController {

    @SneakyThrows
    @RequestMapping("fileUpload")
    public Result baseSaleAttrList(@RequestParam("file") MultipartFile multipartFile){
//        String path = TestFdfs.class.getClassLoader().getResource("tracker.conf").getPath();
        String configFile = this.getClass().getResource("/tracker.conf").getFile();
        //初始化conf文件
        ClientGlobal.init(configFile);
        //连接tracker分发客户端
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        //连接storage仓库
        StorageClient storageClient = new StorageClient(trackerServer, null);
        //通过文件名回去文件后缀名jpg
        String filenameExtension = StringUtils.getFilenameExtension(multipartFile.getOriginalFilename());
        //上传
        String[] jpgs = storageClient.upload_file(multipartFile.getBytes(), filenameExtension, null);

        String url="http://192.168.200.128:8080";
        //返回URL地址
        for (String jpg : jpgs) {
            url=url+"/"+jpg;
        }
        System.out.println(url);
        return Result.ok(url);
    }
}
