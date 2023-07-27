package com.erp.blang.logging.api.utils;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AppUtils {

	public static Object convertMessage(Object format, Object ...args) {
        try {
            return MessageFormat.format((String) format, args);
        } catch(Exception e) {
            return null;
        }
    }
    
    public static String dateToString(Date date,String pattern) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(pattern);
            return format.format(date);
        } catch (Exception e) {
            return null;
        }
    }
}
