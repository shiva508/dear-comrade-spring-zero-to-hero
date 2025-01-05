package com.comrade.service;

import com.comrade.config.FtpProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

@Slf4j
@Component
public class FtpFileOperationsFactory {

    @Autowired
    private FtpProperties ftpProperties;

    FTPClient ftpClient;

    boolean connected = false;


    public boolean openFtpConnection(){
        log.info("openFtpConnection::started");
        if (null == ftpClient && !connected){
            log.info("openFtpConnection::connection not available, creating new connection");
            ftpClient = new FTPClient();
            try {
                ftpClient.connect(ftpProperties.getHost(),ftpProperties.getPort());
                connected = ftpClient.login(ftpProperties.getUsername(), ftpProperties.getPassword());
                if (ftpProperties.isTtlEnabled()){
                    ftpClient.setControlKeepAliveTimeout(Duration.of(ftpProperties.getTtl(), ChronoUnit.MINUTES));
                }
                log.info("openFtpConnection:: creating new connection-completed");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else {
            log.info("openFtpConnection::connection available");
        }
        log.info("openFtpConnection::completed");
        return connected;
    }

    public void closeFtpConnection(){
        log.info("openFtpConnection::closeFtpConnection::started");
        if (null !=ftpClient){
            try {
               boolean connectionClosed = ftpClient.logout();
                log.info("openFtpConnection::closeFtpConnection::closed::{}",connectionClosed);
               ftpClient.disconnect();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        log.info("openFtpConnection::closeFtpConnection::completed");
    }

    public boolean saveFile(InputStream inputStream, String fileName){
        log.info("saveFile::upload started with file-name::{}::started",fileName);
        boolean fileSaved = false;
        try {
           fileSaved = ftpClient.storeFile(String.format("%s/%s",ftpProperties.getPath(), fileName), inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        log.info("saveFile::upload completed");
        return fileSaved;
    }

    public boolean delete(String fileName){
        log.info("delete::deleting started with file-name::{}::started",fileName);
        boolean fileDeleted = false;
        try {
            fileDeleted = ftpClient.deleteFile(String.format("%s/%s",ftpProperties.getPath(), fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        log.info("saveFile::delete completed");
        return fileDeleted;
    }
}
