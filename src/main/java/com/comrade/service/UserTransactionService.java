package com.comrade.service;

import com.comrade.model.TransactionModel;
import com.comrade.model.TransactionResponseModel;
import reactor.core.publisher.Mono;

public interface UserTransactionService {
    public Mono<TransactionResponseModel> createTransaction(TransactionModel transactionModel);
}
