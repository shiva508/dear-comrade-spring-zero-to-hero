package com.comrade.config;

import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.filesystem.nativefs.NativeFileSystemFactory;
import org.apache.ftpserver.ftplet.*;
import org.apache.ftpserver.listener.Listener;
import org.apache.ftpserver.listener.ListenerFactory;
import org.apache.ftpserver.usermanager.ClearTextPasswordEncryptor;
import org.apache.ftpserver.usermanager.PropertiesUserManagerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.Assert;

import java.io.File;
import java.io.IOException;
import java.util.Collections;

@Configuration
public class DearComradeFtpServerConfig {

    @Autowired
    private FtpProperties ftpProperties;

    @Autowired
    private ResourceLoader resourceLoader;

    @Bean
    public FileSystemFactory dcFtpServerFactory(){
        NativeFileSystemFactory fileSystemFactory = new NativeFileSystemFactory();
        fileSystemFactory.setCreateHome(true);
        fileSystemFactory.setCaseInsensitive(false);
        return fileSystemFactory;
    }

    @Bean
    public Listener dcFtplistener(){
        ListenerFactory listenerFactory = new ListenerFactory();
        listenerFactory.setPort(ftpProperties.getPort());
        return listenerFactory.createListener();
    }

    @Bean
    public FtpServer dcFtpServer(Listener dcFtplistener,
                                 @Qualifier("propertiesUserManager") UserManager propertiesUserManager,
                                 FileSystemFactory dcFtpServerFactory){
        FtpServerFactory ftpServerFactory = new FtpServerFactory();
        ftpServerFactory.setListeners(Collections.singletonMap("default", dcFtplistener));
        ftpServerFactory.setUserManager(propertiesUserManager);
        ftpServerFactory.setFileSystem(dcFtpServerFactory);
        return ftpServerFactory.createServer();
    }

    @Bean(name = "propertiesUserManager")
    public UserManager propertiesUserManager(){
        PropertiesUserManagerFactory userManagerFactory = new PropertiesUserManagerFactory();
        userManagerFactory.setAdminName(ftpProperties.getAdminName());
        userManagerFactory.setPasswordEncryptor(new ClearTextPasswordEncryptor());
        Resource resource = resourceLoader.getResource("classpath:user.properties");
        try {
            File file = resource.getFile();
            userManagerFactory.setFile(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return userManagerFactory.createUserManager();
    }

    @Bean
    public InitializingBean startDcFtpServer(FtpServer dcFtpServer){
        return dcFtpServer::start;
    }

    @Bean
    public DisposableBean stopDcFtpServer(FtpServer dcFtpServer){
        return dcFtpServer::stop;
    }

    @Bean("dcFtpUserManager")
    public  UserManager dcFtpUserManager(@Value("${dc-ftp.root-path}") File root,
                                    JdbcTemplate template){
        Assert.isTrue(root.exists() || root.mkdirs(), "the root directory must exist.");
        return new DcFtpUserManager(root, template);
    }

}
