package com.comrade.service;


import com.comrade.model.FileResponse;
import org.springframework.web.multipart.MultipartFile;

public interface FileOperationsService {

    FileResponse upload(MultipartFile file);

    FileResponse delete(String fileName);
}
