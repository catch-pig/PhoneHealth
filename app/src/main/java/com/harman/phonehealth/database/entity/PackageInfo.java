package com.harman.phonehealth.database.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class PackageInfo {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private int usedCount;
    private long usedTime;
    private String packageName;
    private String appName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUsedCount() {
        return usedCount;
    }

    public void setUsedCount(int usedCount) {
        this.usedCount = usedCount;
    }

    public long getUsedTime() {
        return usedTime;
    }

    public void setUsedTime(long usedTime) {
        this.usedTime = usedTime;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (this == o) return true;
        PackageInfo standardDetail = (PackageInfo) o;
        return standardDetail.getPackageName().equals(this.packageName);
    }

    @Override
    public int hashCode() {
        return (packageName + usedTime).hashCode();
    }

    @Override
    public String toString() {
        return "PackageInfoBean{" +
                "mUsedCount=" + usedCount +
                ", mUsedTime=" + usedTime +
                ", mPackageName='" + packageName + '\'' +
                ", mAppName='" + appName + '\'' +
                '}';
    }
}
