package com.harman.phonehealth.entity;


public class PackageInfoBean {
    private int usedCount;
    private long usedTime;
    private String packageName;
    private String appName;
    private long date;

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public PackageInfoBean(long date, int usedCount, long usedTime, String packageName, String appName) {
        this.date = date;
        this.usedCount = usedCount;
        this.usedTime = usedTime;
        this.packageName = packageName;
        this.appName = appName;
    }

    public void addCount() {
        usedCount++;
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
        PackageInfoBean standardDetail = (PackageInfoBean) o;
        if (standardDetail.getPackageName().equals(this.packageName)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return (packageName + usedTime).hashCode();
    }

    @Override
    public String toString() {
        return "PackageInfoBean{" +
                "usedCount=" + usedCount +
                ", usedTime=" + usedTime +
                ", packageName='" + packageName + '\'' +
                ", appName='" + appName + '\'' +
                '}';
    }
}