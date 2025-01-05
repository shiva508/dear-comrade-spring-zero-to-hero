package com.comrade.controller;

import com.comrade.model.FileResponse;
import com.comrade.service.FileOperationsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/file")
@Slf4j
public class FileOperationsController {

    @Autowired
    @Qualifier("systemFileOperationsService")
    private FileOperationsService systemFileOperationsService;

    @Autowired
    @Qualifier("ftpFileOperationsService")
    private FileOperationsService ftpFileOperationsService;

    @PostMapping(value = "/system/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<FileResponse> uploadFileToNativeServer(@RequestParam("file") MultipartFile file){
        log.info("uploadFileToNativeServer::started");
        FileResponse fileResponse = systemFileOperationsService.upload(file);
        log.info("uploadFileToNativeServer::completed");
        return new ResponseEntity<>(fileResponse, HttpStatus.OK);
    }

    @PostMapping(value = "/ftp/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<FileResponse> uploadFileToFtpServer(@RequestParam("file") MultipartFile file){
        log.info("uploadFileToFtpServer::started");
        FileResponse fileResponse = ftpFileOperationsService.upload(file);
        log.info("uploadFileToFtpServer::completed");
        return new ResponseEntity<>(fileResponse, HttpStatus.OK);
    }
}
