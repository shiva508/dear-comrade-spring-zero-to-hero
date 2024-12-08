package com.comrade.service;

import com.azure.storage.blob.BlobClient;
import com.comrade.config.builder.AzureServiceClientBuilder;
import com.comrade.config.exception.AzureException;
import com.comrade.domine.AzureFileEntity;
import com.comrade.repository.AzureFileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;

@Service
@Slf4j
@RequiredArgsConstructor
public class AzureBlobService implements BlobService{

    private final AzureServiceClientBuilder azureServiceClientBuilder;

    private final AzureFileRepository azureFileRepository;

    @Override
    public String uploadFile(InputStream inputStream, String fileName) {
        log.info("AzureBlobService::uploadFile::Filename :{}", fileName);
        String fileUrl = "";
        try{
            BlobClient blobClient = azureServiceClientBuilder.blobClient("testcantainer", fileName);
            blobClient.upload(inputStream);
            fileUrl = blobClient.getBlobUrl();
        } catch (Exception exception){
            throw new AzureException(exception.getMessage());
        }
        return fileUrl;
    }

    @Override
    public String downloadAsString(String fileName) {
        String response = "";
        try {
            BlobClient blobClient = azureServiceClientBuilder.blobClient("testcantainer", fileName);
            OutputStream outputStream = new ByteArrayOutputStream();
            blobClient.downloadStream(outputStream);
            response = outputStream.toString();
        } catch (Exception exception){
            throw new AzureException(exception.getMessage());
        }
        return response;
    }

    @Override
    public AzureFileEntity uploadMultipartFile(MultipartFile file) {
        AzureFileEntity azureFileEntity = new AzureFileEntity();
        try {
            InputStream inputStream = file.getInputStream();
            String originalFilename = file.getOriginalFilename();
            long size = file.getSize();
            String fileName = azureServiceClientBuilder.fileNameBuilder(originalFilename);
            log.info("originalFilename={}",originalFilename);
            BlobClient blobClient = azureServiceClientBuilder.blobClient("testcantainer", fileName);
            blobClient.upload(inputStream);
            azureFileEntity.setFileName(fileName);
            azureFileEntity.setFileUrl(blobClient.getBlobUrl());
            azureFileEntity.setFileSize(size);
            azureFileEntity.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            azureFileEntity.setModifiedAt(new Timestamp(System.currentTimeMillis()));
            azureFileEntity = azureFileRepository.save(azureFileEntity);
        } catch (IOException exception) {
            throw new AzureException(exception.getMessage());
        }
        return azureFileEntity;
    }
}
