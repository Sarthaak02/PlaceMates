package com.placemates.response;

import java.time.LocalDateTime;
import java.util.List;


public class APIResponse {

    private LocalDateTime timestamp;
    private List<String> message;

    public APIResponse(LocalDateTime timestamp, List<String> message) {
        this.timestamp = timestamp;
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public List<String> getMessage() {
        return message;
    }

    public void setMessage(List<String> message) {
        this.message = message;
    }
}
