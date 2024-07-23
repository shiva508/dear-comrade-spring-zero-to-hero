package com.comrade.service;

import com.comrade.mapper.UserMapper;
import com.comrade.model.TransactionModel;
import com.comrade.model.TransactionResponseModel;
import com.comrade.repository.UserProfileRepository;
import com.comrade.repository.UserTransactionRepository;
import com.comrade.util.TransactionStatus;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class UserTransactionServiceImpl implements UserTransactionService{

    private final UserProfileRepository userProfileRepository;
    private final UserTransactionRepository userTransactionRepository;
    private final UserMapper userMapper;


    @Override
    public Mono<TransactionResponseModel> createTransaction(TransactionModel transactionModel){
       return userProfileRepository.updateUserBalance(transactionModel.getUserId(),transactionModel.getAmount())
                .filter(aBoolean -> aBoolean)
                .map(aBoolean ->  userMapper.transactionModelToEntity(transactionModel))
                .flatMap(userTransactionRepository::save)
                .map(userTransaction -> userMapper.toTransactionResponseModel(transactionModel, TransactionStatus.APPROVED))
                .defaultIfEmpty(userMapper.toTransactionResponseModel(transactionModel, TransactionStatus.DECLINED));
    }
}
