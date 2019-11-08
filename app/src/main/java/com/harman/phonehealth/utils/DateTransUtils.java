package com.harman.phonehealth.utils;

import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateTransUtils {

    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final String TAG = "DateTransUtils";

    //将时间戳转换为时间(String)
    public static String stampToDate(String stamp) {
        String res;
        SimpleDateFormat simpleDateFormat = format;
        long lt = new Long(stamp);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }

    //将时间戳转换为时间(long)
    public static String stampToDate(long stamp) {
        String res;
        SimpleDateFormat simpleDateFormat = format;
        Date date = new Date(stamp);
        res = simpleDateFormat.format(date);
        return res;
    }

    //获取当日00:00:00的时间戳
    public static long getZeroClockTimestamp(long time) {
        long nowTime = System.currentTimeMillis();
        long todayStartTime = nowTime - ((nowTime + TimeZone.getDefault().getRawOffset()) % (24 * 60 * 60 * 1000L));
        Log.d(TAG, "getZeroClockTimestamp: " + todayStartTime);
        return todayStartTime;
    }

    //获取查询日期0点时间戳
    public static long getStartClockTimeStamp(String date) throws Exception {
        DateFormat dateFormat = format;
        long startTime = dateFormat.parse(date + " 00:00:00").getTime();
        Log.d(TAG, "getStartClockTimeStamp: " + startTime);
        return startTime;
    }

    //获取查询日期最后一分时间戳
    public static long getEndClockTimeStamp(String date) throws Exception {
        DateFormat dateFormat = format;
        long endTime = dateFormat.parse(date + " 23:59:59").getTime();
        Log.d(TAG, "getEndClockTimeStamp: " + endTime);
        return endTime;
    }
}