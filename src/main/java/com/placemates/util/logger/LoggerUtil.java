package com.placemates.util.logger;

import org.springframework.stereotype.Component;

@Component
public class LoggerUtil {
    public static String buildLog(String module,String activityType,String description, String performedBy, double duration, String status) {
        return String.format(
                "\nModule: %s\nActivity Type: %s\nModule Description: %s\nPerformed By: %s\nTime Taken: %fs\nStatus: %s",
                module, activityType, description, performedBy, duration, status
        );
    }
}
