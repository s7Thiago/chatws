package br.com.ws.chatws.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StringUtils {

    private static final String TIME_FORMATTER = "HH:mm:ss";
    private static final String DATE_TIME_FORMATTER = "yyyy-MM-dd'T'HH:mm:ss'Z'";

    public static String getCurrentTimeStamp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(TIME_FORMATTER);
        return LocalDateTime.now().format(formatter);
    }

    public static DateTimeFormatter getCurrentDateFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMATTER);
        return formatter;
    }
}