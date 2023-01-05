package com.rakamintest.chatapp.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    private DateUtils() {
    }

    public static String format(Timestamp timestamp){
        Date date = new Date();
        date.setTime(timestamp.getTime());
        return new SimpleDateFormat("dd-MM-yyyy HH:mm").format(date);
    }
}
