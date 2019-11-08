package com.harman.phonehealth.utils;

import android.annotation.TargetApi;
import android.app.usage.UsageEvents;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;


public class EventUtils {

    public static final String TAG = "EventUtils";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    @SuppressWarnings("ResourceType")
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static ArrayList<UsageEvents.Event> getEventList(Context context, long startTime, long endTime) {
        ArrayList<UsageEvents.Event> mEventList = new ArrayList<>();

        Log.i(TAG, "getEventList() Range start:" + startTime + " " + dateFormat.format(startTime));
        Log.i(TAG, "getEventList() Range end:" + endTime + " " + dateFormat.format(endTime));

        UsageStatsManager mUsmManager = (UsageStatsManager) context.getSystemService("usagestats");
        UsageEvents events = mUsmManager.queryEvents(startTime, endTime);

        while (events.hasNextEvent()) {
            UsageEvents.Event e = new UsageEvents.Event();
            events.getNextEvent(e);
            if (e != null && (e.getEventType() == 1 || e.getEventType() == 2)) {
                Log.i(TAG, "getEventList() " + e.getTimeStamp() + " event:" + e.getClassName() + " type = " + e.getEventType());
                mEventList.add(e);
            }
        }

        return mEventList;
    }

    @SuppressWarnings("ResourceType")
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static ArrayList<UsageStats> getUsageList(Context context, long startTime, long endTime) {

        Log.i(TAG, "getUsageList() Range start:" + startTime + " " + dateFormat.format(startTime));
        Log.i(TAG, "getUsageList() Range end:" + endTime + " " + dateFormat.format(endTime));

        ArrayList<UsageStats> list = new ArrayList<>();

        UsageStatsManager mUsmManager = (UsageStatsManager) context.getSystemService("usagestats");
        Map<String, UsageStats> map = mUsmManager.queryAndAggregateUsageStats(startTime, endTime);
        for (Map.Entry<String, UsageStats> entry : map.entrySet()) {
            UsageStats stats = entry.getValue();
            if (stats.getTotalTimeInForeground() > 0) {
                list.add(stats);
                Log.i(TAG, "getUsageList() stats:" + stats.getPackageName() + " TotalTimeInForeground = " + stats.getTotalTimeInForeground());
            }
        }
        return list;
    }
}