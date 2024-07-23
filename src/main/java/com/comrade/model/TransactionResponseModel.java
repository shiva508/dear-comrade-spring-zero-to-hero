package com.comrade.model;


import com.comrade.util.TransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionResponseModel {
    private Long userId;
    private Integer amount;
    private TransactionStatus status;
}
