package com.comrade.service;

import com.azure.storage.blob.BlobClient;
import com.comrade.config.builder.AzureServiceClientBuilder;
import com.comrade.config.exception.AzureException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

@Service
@Slf4j
@RequiredArgsConstructor
public class AzureBlobService implements BlobService{

    private final AzureServiceClientBuilder azureServiceClientBuilder;

    @Override
    public String uploadFile(InputStream inputStream, String fileName) {
        log.info("AzureBlobService::uploadFile::Filename :{}", fileName);
        String fileUrl = "";
        try{
            BlobClient blobClient = azureServiceClientBuilder.blobClient("testcantainer", fileName);
            blobClient.upload(inputStream);
            fileUrl = blobClient.getBlobUrl();
        }catch (Exception exception){
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
}
