package com.wany.myuestcbbs.controller;

import com.wany.myuestcbbs.entity.Result;
import com.wany.myuestcbbs.service.FileService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

@RestController
@RequestMapping("/v1/files")
public class FileController {

    @Resource
    private FileService fileService;

    @PostMapping("/upload")
    public Result upload(@RequestParam("file") MultipartFile file) {
        //限制文件大小
        if (file.getSize() > 1024 * 1024 * 10) { // 10MB
            return Result.FAIL("File size is too large.");
        }
        //限制文件类型
        String contentType = file.getContentType();
        if (!contentType.equals("image/jpeg") && !contentType.equals("image/png")) {
            return Result.FAIL("File type is not supported.");
        }
        //压缩图片
        // TODO

        // 上传文件
        try {
            String filePath = fileService.upLoadSingleFile(file.getBytes(), file.getOriginalFilename());
            return Result.SUCCESS(filePath);
        } catch (IOException e) {
            e.printStackTrace();
            return Result.FAIL("File upload failed: " + e.getMessage());
        }

    }
}
