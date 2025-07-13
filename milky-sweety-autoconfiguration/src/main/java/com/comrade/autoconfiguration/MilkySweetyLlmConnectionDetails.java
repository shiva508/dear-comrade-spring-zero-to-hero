package com.comrade.autoconfiguration;

import org.springframework.boot.autoconfigure.service.connection.ConnectionDetails;

public interface MilkySweetyLlmConnectionDetails extends ConnectionDetails {

    public String getBaseUrl();
}
