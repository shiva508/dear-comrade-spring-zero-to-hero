package com.comrade.autoconfiguration;

public class PropertiesMilkySweetyLlmConnectionDetails implements MilkySweetyLlmConnectionDetails{

    private final MilkySweetyLlmProperties milkySweetyLlmProperties;

    public PropertiesMilkySweetyLlmConnectionDetails( MilkySweetyLlmProperties milkySweetyLlmProperties){
        this.milkySweetyLlmProperties = milkySweetyLlmProperties;
    }

    @Override
    public String getBaseUrl() {
        return milkySweetyLlmProperties.getBaseUrl();
    }
}
