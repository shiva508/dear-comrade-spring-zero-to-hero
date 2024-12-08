package com.comrade.service;

import java.io.InputStream;

public interface BlobService {
    String uploadFile(InputStream inputStream, String fileName);
    String downloadAsString(String fileName);
}
