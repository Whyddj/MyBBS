package com.wany.myuestcbbs.service.Impl;

import com.wany.myuestcbbs.service.FileService;
import com.wany.myuestcbbs.util.RandomUtil;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class FileServiceImpl implements FileService {

    @Value("${file.path}")
    private String FILE_PATH;

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");

    @Override
    public String upLoadSingleFile(byte[] content , String originFileName) {
        String folder = GenerateFilePath();
        String fileName = GenerateFileName(originFileName.substring(originFileName.lastIndexOf(".")));
        String filePath = FILE_PATH + folder + java.io.File.separatorChar;
        try {
            java.io.File file = new java.io.File(filePath);
            if (!file.exists()) {
                file.mkdirs();
            }
            java.io.File targetFile = new java.io.File(filePath + fileName);
            java.io.FileOutputStream out = new FileOutputStream(targetFile);
            IOUtils.copy(new ByteArrayInputStream(content), out);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return folder + java.io.File.separatorChar + fileName;
    }

    private String GenerateFileName(String suffix) {
        return System.currentTimeMillis() + "_" + RandomUtil.getRandomNumber(6) + "." + suffix;
    }

    private String GenerateFilePath() {
        return LocalDateTime.now().format(dateTimeFormatter) + java.io.File.separatorChar;
    }
}
