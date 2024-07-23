package com.comrade.service;

import com.comrade.model.CounterModel;
import java.util.List;

public interface CounterService {
    public CounterModel createCounter(CounterModel counterModel);

    public CounterModel increamentCounterByName(String counterName);

    public CounterModel getCounterByName(String counterName);

    public List<CounterModel> getAllConters();

}
