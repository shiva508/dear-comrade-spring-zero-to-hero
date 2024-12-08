package com.comrade.config.builder;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AzureServiceClientBuilder {

    @Autowired
    BlobServiceClient blobServiceClient;

    public BlobClient blobClient(String containerName, String fileName){
        BlobContainerClient blobContainerClient = blobServiceClient.createBlobContainerIfNotExists(containerName);
        return blobContainerClient.getBlobClient(fileName);
    }

    public String objectToJsonConvertor(Object object){
        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;
        try {
            json = objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return json;
    }

    public String fileNameBuilder(String fileName){
        return String.format("%s_%s", UUID.randomUUID().toString(),fileName);
    }
}
