package com.comrade.model;

import java.io.Serializable;

public class CounterModel implements Serializable {
    private String counterName;
    private Integer currentCount;

    public CounterModel() {
    }

    public CounterModel(String counterName, Integer currentCount) {
        this.counterName = counterName;
        this.currentCount = currentCount;
    }

    public String getCounterName() {
        return counterName;
    }

    public Integer getCurrentCount() {
        return currentCount;
    }

    public void setCounterName(String counterName) {
        this.counterName = counterName;
    }

    public void setCurrentCount(Integer currentCount) {
        this.currentCount = currentCount;
    }

}
