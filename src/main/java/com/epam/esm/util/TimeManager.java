package com.epam.esm.util;

import com.epam.esm.exception.DataParseException;
import com.epam.esm.exception.ErrorCodeDict;
import com.epam.esm.exception.ErrorMessage;

import java.sql.Timestamp;
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

    public static String dateToString(Date date) {

        String nowAsISO = dateFormat.format(date);
        return nowAsISO;
    }

    public static Date dateFromString(String str) {
        try {
            return dateFormat.parse(str);
        } catch (ParseException e) {
            throw new DataParseException(ErrorMessage.BAD_INPUT_TIME, ErrorCodeDict.BAD_INPUT_TIME);
        }
    }


}
