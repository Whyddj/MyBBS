package com.wany.myuestcbbs.controller;

import com.wany.myuestcbbs.entity.Result;
import com.wany.myuestcbbs.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

@Api(value = "FileController", tags = "文件接口")
@RestController
@RequestMapping("/v1/files")
public class FileController {

    @Resource
    private FileService fileService;

    @ApiOperation("上传图片文件 10MB以内 仅支持jpg和png")
    @ApiResponses({
            @ApiResponse(code = 400, message = "File size is too large.", response = Result.class),
            @ApiResponse(code = 400, message = "File type is not supported.", response = Result.class),
            @ApiResponse(code = 403, message = "Unauthorized", response = Result.class),
            @ApiResponse(code = 200, message = "File upload successfully: {filePath}", response = Result.class),
    })
    @PostMapping("/upload")
    public Result upload(@RequestParam("file") MultipartFile file) {
        // 限制文件大小
        if (file.getSize() > 1048576) { // 1MB org.apache.tomcat.util.http.fileupload.impl 支持的最大文件大小
            return Result.FAIL("File size is too large.");
        }
        // 限制文件类型
        String contentType = file.getContentType();
        if (!contentType.equals("image/jpeg") && !contentType.equals("image/png")) {
            return Result.FAIL("File type is not supported.");
        }
        // 压缩图片
        // TODO

        // 上传文件
        try {

            String filePath = fileService.upLoadSingleFile(file.getBytes(), file.getOriginalFilename());
            return Result.SUCCESS("File upload successfully: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
            return Result.FAIL("File upload failed: " + e.getMessage());
        }

    }
}
