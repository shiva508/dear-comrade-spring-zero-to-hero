package com.comrade.service;

import com.comrade.model.FileResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Service("systemFileOperationsService")
@Slf4j
public class SystemFileOperationsService implements FileOperationsService{

    @Override
    public FileResponse upload(MultipartFile file) {
        log.info("upload::started");
        FileResponse fileResponse = null;
        try {
            Path path = Path.of(String.format("%s","C:\\shiva\\file-store"), file.getOriginalFilename());
            long size = Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            log.info("upload::size::{}",size);
            fileResponse = FileResponse.builder().fileName(file.getOriginalFilename()).fileSize(file.getSize()).storePath(path.toString()).uploaded(true).build();
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
