package com.comrade.domine;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "AZURE_FILE_TBL")
public class AzureFileEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "AZURE_FILE_ID")
    private Long azureFileId;

    @Column(name = "FILE_NAME")
    private String fileName;

    @Column(name = "FILE_URL")
    private String fileUrl;

    @Column(name = "FILE_SIZE")
    private Long fileSize;

    @Column(name = "CREATED_AT")
    private Timestamp createdAt;

    @Column(name = "MODIFIED_AT")
    private Timestamp modifiedAt;
}
