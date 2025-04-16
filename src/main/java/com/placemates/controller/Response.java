package com.placemates.controller;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class Response {
    private LocalDateTime timeStamp;
    private String message;

    public Response(LocalDateTime timeStamp, String message) {
        this.timeStamp = timeStamp;
        this.message = message;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
