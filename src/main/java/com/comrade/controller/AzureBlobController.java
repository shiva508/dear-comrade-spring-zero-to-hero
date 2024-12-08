package com.comrade.controller;

import com.azure.storage.blob.BlobServiceClient;
import com.comrade.config.builder.AzureServiceClientBuilder;
import com.comrade.service.BlobService;
import com.comrade.util.DearComradeConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/blob")
@Slf4j
public class AzureBlobController {

    @Autowired
    BlobServiceClient blobServiceClient;

    @Autowired
    BlobService blobService;

    @Autowired
    AzureServiceClientBuilder azureServiceClientBuilder;

    @GetMapping("/upload")
    public String uploadJsonFile(){
        Map<String, String> map = Map.of("name", "Shiva");
        String jsonAsString = azureServiceClientBuilder.objectToJsonConvertor(map);
        InputStream inputStream = new ByteArrayInputStream(jsonAsString.getBytes(StandardCharsets.UTF_8));
        String fileName = azureServiceClientBuilder.fileNameBuilder(DearComradeConstant.DEFAULT_FILE_NAME);
        return blobService.uploadFile(inputStream, fileName);
    }

    @GetMapping("/download/{fileName}")
    public String downloadAsString(@PathVariable("fileName") String fileName){
        String response = "";
        String actualFileName = StringUtils.isBlank(fileName) ? DearComradeConstant.DEFAULT_AZURE_FILE_NAME : fileName ;
        response = blobService.downloadAsString(actualFileName);
        return response;
    }

    @PostMapping("/uploadMultipart")
    public void uploadMultipartFile(@RequestParam("file") MultipartFile file){
        try {
            InputStream inputStream = file.getInputStream();
            String originalFilename = file.getOriginalFilename();
            String name = file.getName();
            long size = file.getSize();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
