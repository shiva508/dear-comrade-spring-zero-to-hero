package com.comrade.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.lang.NonNull;

@Data
@Builder
public class Welcome {
    @NonNull
    private String message;
}
