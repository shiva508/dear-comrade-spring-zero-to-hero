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
        if (null == ftpClient && !connected){
            ftpClient = new FTPClient();
            try {
                ftpClient.connect(ftpProperties.getHost(),ftpProperties.getPort());
                connected = ftpClient.login(ftpProperties.getUsername(), ftpProperties.getPassword());
                if (ftpProperties.isTtlEnabled()){
                    ftpClient.setControlKeepAliveTimeout(Duration.of(ftpProperties.getTtl(), ChronoUnit.MINUTES));
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return connected;
    }

    public void closeFtpConnection(){

        if (null !=ftpClient){
            try {
               boolean connectionClosed = ftpClient.logout();
               ftpClient.disconnect();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public boolean saveFile(InputStream inputStream, String fileName){
        boolean fileSaved = false;
        try {
           fileSaved = ftpClient.storeFile(String.format("%s/%s",ftpProperties.getPath(), fileName), inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return fileSaved;
    }


}
