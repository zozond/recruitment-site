package com.recruitment.site.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Util {
    private static String pattern = "yyyy-MM-dd HH:mm:ss";

    public static LocalDateTime convertLocalDateTime(String date){
        return LocalDateTime.parse(date, DateTimeFormatter.ofPattern(pattern));
    }
}
