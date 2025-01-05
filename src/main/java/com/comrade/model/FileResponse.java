package com.comrade.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileResponse {

    private String fileName;

    private Long fileSize;

    private boolean uploaded;

    private String storePath;
}
