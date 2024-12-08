package com.comrade.service;

import com.comrade.domine.AzureFileEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface BlobService {
    String uploadFile(InputStream inputStream, String fileName);
    String downloadAsString(String fileName);
    AzureFileEntity uploadMultipartFile(MultipartFile file);
}
