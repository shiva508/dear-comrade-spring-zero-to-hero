package com.comrade.model;

import java.util.Date;

public record ErrorResponse(String message, Integer status, Date timestamp) {
}
