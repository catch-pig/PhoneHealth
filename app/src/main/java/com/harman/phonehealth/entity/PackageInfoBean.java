package com.harman.phonehealth.entity;


public class PackageInfoBean {
    private int mUsedCount;
    private long mUsedTime;
    private String mPackageName;
    private String mAppName;

    public PackageInfoBean(int mUsedCount, long mUsedTime, String mPackageName, String appName) {
        this.mUsedCount = mUsedCount;
        this.mUsedTime = mUsedTime;
        this.mPackageName = mPackageName;
        this.mAppName = appName;
    }

    public void addCount() {
        mUsedCount++;
    }

    public int getmUsedCount() {
        return mUsedCount;
    }

    public void setmUsedCount(int mUsedCount) {
        this.mUsedCount = mUsedCount;
    }

    public long getmUsedTime() {
        return mUsedTime;
    }

    public void setmUsedTime(long mUsedTime) {
        this.mUsedTime = mUsedTime;
    }

    public String getmPackageName() {
        return mPackageName;
    }

    public void setmPackageName(String mPackageName) {
        this.mPackageName = mPackageName;
    }

    public String getmAppName() {
        return mAppName;
    }

    public void setmAppName(String mAppName) {
        this.mAppName = mAppName;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (this == o) return true;
        PackageInfoBean standardDetail = (PackageInfoBean) o;
        if (standardDetail.getmPackageName().equals(this.mPackageName)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return (mPackageName + mUsedTime).hashCode();
    }

    @Override
    public String toString() {
        return "PackageInfoBean{" +
                "mUsedCount=" + mUsedCount +
                ", mUsedTime=" + mUsedTime +
                ", mPackageName='" + mPackageName + '\'' +
                ", mAppName='" + mAppName + '\'' +
                '}';
    }
}