package com.harman.phonehealth.entity;

import android.app.usage.UsageEvents;

import java.util.ArrayList;


public class OneTimeDetails {
    private String pkgName;
    private long useTime;
    private ArrayList<UsageEvents.Event> OneTimeDetailEventList;

    public OneTimeDetails(String pkg, long useTime, ArrayList<UsageEvents.Event> oneTimeDetailList) {
        this.pkgName = pkg;
        this.useTime = useTime;
        OneTimeDetailEventList = oneTimeDetailList;
    }

    public String getPkgName() {
        return pkgName;
    }

    public void setPkgName(String pkgName) {
        this.pkgName = pkgName;
    }

    public long getUseTime() {
        return useTime;
    }

    public void setUseTime(long useTime) {
        this.useTime = useTime;
    }

    public ArrayList<UsageEvents.Event> getOneTimeDetailEventList() {
        return OneTimeDetailEventList;
    }

    public void setOneTimeDetailEventList(ArrayList<UsageEvents.Event> oneTimeDetailEventList) {
        OneTimeDetailEventList = oneTimeDetailEventList;
    }
}