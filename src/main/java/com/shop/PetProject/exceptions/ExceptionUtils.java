package com.shop.PetProject.exceptions;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExceptionUtils {

    public static String getResponseBody(String msg, Class aClass) {
        return """
                The next error occurs: %s
                In this class: %s
                At this time: %s"""
                .formatted(msg, aClass.getSimpleName(),
                        LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
    }
}
