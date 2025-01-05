package com.comrade.service;

import com.comrade.model.FileResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.ftpserver.ftplet.FtpSession;
import org.apache.ftpserver.impl.FtpIoSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Service("ftpFileOperationsService")
@Slf4j
public class FtpFileOperationsService implements FileOperationsService{

    @Autowired
    private FtpFileOperationsFactory ftpFileOperationsFactory;

    @Override
    public FileResponse upload(MultipartFile file) {
        log.info("upload::started");
        FileResponse fileResponse = null;
        try {
            boolean openConnection = ftpFileOperationsFactory.openFtpConnection();
            log.info("upload::openConnection::{}",openConnection);
            boolean fileUploaded = ftpFileOperationsFactory.saveFile(file.getInputStream(), file.getOriginalFilename());
            log.info("upload::FTP server file upload status ::{}",fileUploaded);
            fileResponse = FileResponse.builder().fileName(file.getOriginalFilename()).fileSize(file.getSize()).storePath("").uploaded(true).build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        log.info("upload::completed");
        return fileResponse;
    }

    @Override
    public FileResponse delete(String fileName) {
        return null;
    }
}
