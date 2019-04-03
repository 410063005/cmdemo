package com.sunmoonblog.roomdemo;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class UtilsTest {

    @Test
    public void beforeDate() {
        Calendar c = Calendar.getInstance();
        c.set(1900 + 89, 1, 5);
        Date date = c.getTime();

        Date d = Utils.beforeDate(date);

        assertEquals(5, getDate(d));
    }

    @Test
    public void afterDate() {
        Calendar c = Calendar.getInstance();
        c.set(1900 + 89, 1, 5);
        Date date = c.getTime();

        Date d = Utils.afterDate(date);

        assertEquals(6, getDate(d));
    }

    private static int getDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DATE);
    }
}