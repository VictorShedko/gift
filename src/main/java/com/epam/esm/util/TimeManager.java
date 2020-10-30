package com.epam.esm.util;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class TimeManager {

    private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'.'S Z";

    private static final DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
    private static final TimeZone tz = TimeZone.getTimeZone("UTC");

    static {
        dateFormat.setTimeZone(tz);
    }


    public static Date now() {
        return new Date();
    }
}
