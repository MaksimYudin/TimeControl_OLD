package com.utils;

public class CommonUtil {
    public static String convertFirstLetterToUpperCase(String value) {
        return value.substring(0, 1).toUpperCase() + value.substring(1);
    }
}
