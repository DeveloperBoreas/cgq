package com.boreas.utils;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {
    public static String getTime(Date date) {
        Log.d("getTime()", "choice date millis: " + date.getTime());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }
}
