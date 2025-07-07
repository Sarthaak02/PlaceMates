package com.placemates.util.logger;

import org.springframework.stereotype.Component;

@Component
public class LoggerUtil {
    public static String buildLog(String module,String activityType,String description, String performedBy, double duration, String result) {
        return String.format(
                "\nModule: %s\nActivity Type: %s\nModule Description: %s\nPerformed By: %s\nTime Taken: %fs\nResult: %s",
                module, activityType, description, performedBy, duration, result
        );
    }
}
