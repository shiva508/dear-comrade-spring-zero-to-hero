package com.comrade.mapper;


import com.comrade.entity.UserProfile;
import com.comrade.entity.UserTransaction;
import com.comrade.model.TransactionModel;
import com.comrade.model.TransactionResponseModel;
import com.comrade.model.UserModel;
import com.comrade.util.TransactionStatus;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
public class UserMapper {
    public UserModel userEntityToModel(UserProfile userProfile){
        UserModel userModel=new UserModel();
        BeanUtils.copyProperties(userProfile,userModel);
        return userModel;
    }

    public UserProfile userModelToEntity(UserModel userModel){
        UserProfile userProfile=new UserProfile();
        BeanUtils.copyProperties(userModel,userProfile);
        return userProfile;
    }

    public UserTransaction transactionModelToEntity(TransactionModel transactionModel){
        UserTransaction userTransaction=new UserTransaction();
        userTransaction.setUserId(transactionModel.getUserId());
        userTransaction.setAmount(transactionModel.getAmount());
        userTransaction.setTransactionTime(LocalDateTime.now());
        return userTransaction;
    }

    public TransactionResponseModel toTransactionResponseModel(TransactionModel transactionModel,
                                                               TransactionStatus status){
        TransactionResponseModel transactionResponseModel=new TransactionResponseModel();
        transactionResponseModel.setUserId(transactionModel.getUserId());
        transactionResponseModel.setAmount(transactionModel.getAmount());
        transactionResponseModel.setStatus(status);
        return transactionResponseModel;
    }
}
