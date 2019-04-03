package com.sunmoonblog.roomdemo;

import android.text.format.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Utils {

    public static CharSequence formatDate(long time) {
        return DateFormat.format("MM-dd kk:mm", time);
    }

    public static long formatString(String sequence) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd kk:mm", Locale.CHINA);
        try {
            return sdf.parse(sequence).getTime();
        } catch (ParseException e) {
            return 0;
        }
    }

    public static Date beforeDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);

        return c.getTime();
    }

    public static Date afterDate(Date date) {
        Date tmp = new Date(date.getTime() + 24 * 3600 * 1000);
        Calendar c = Calendar.getInstance();
        c.setTime(tmp);

        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);

        return c.getTime();
    }
}
