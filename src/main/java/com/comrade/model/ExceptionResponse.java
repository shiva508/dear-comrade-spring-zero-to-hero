package com.comrade.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponse implements Serializable {
    private String message;
    private Date timeStamp;
    private int statusCode;
}
