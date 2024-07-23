package com.comrade.service;

import com.comrade.model.CounterModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CounterServiceImpl implements CounterService{
    static  List<CounterModel> counterModels;
    static {
        counterModels=new ArrayList<>();
        counterModels.add(new CounterModel("Shiva",0));
    }


    @Override
    public CounterModel createCounter(CounterModel counterModel) {
        //Check if counter already available
        boolean isCounterAvailable = counterModels.stream()
                                                  .anyMatch(model -> model.getCounterName().equalsIgnoreCase(counterModel.getCounterName()));

        if(!isCounterAvailable){
            counterModel.setCurrentCount(0);
            counterModels.add(counterModel);
        }else{

        }
        return null;
    }

    @Override
    public CounterModel increamentCounterByName(String counterName) {
        return counterModels.stream().peek(counterModel -> {
            if (counterModel.getCounterName().equalsIgnoreCase(counterName)) {
                counterModel.setCurrentCount(counterModel.getCurrentCount() + 1);
            }
        }).toList().stream()
                   .filter(model -> model.getCounterName().equalsIgnoreCase(counterName))
                   .findFirst().orElseThrow(() -> new RuntimeException("Error"));
    }

    @Override
    public CounterModel getCounterByName(String counterName) {
        return counterModels.stream()
                           .filter(model -> model.getCounterName().equalsIgnoreCase(counterName))
                           .findFirst().orElseThrow(()->new RuntimeException(""));
    }

    @Override
    public List<CounterModel> getAllConters() {
        return counterModels;
    }
}
