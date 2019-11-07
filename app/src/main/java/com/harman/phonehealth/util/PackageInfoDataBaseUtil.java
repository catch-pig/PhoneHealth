package com.harman.phonehealth.util;

import android.content.Context;

import com.harman.phonehealth.app.PhoneHealthApp;
import com.harman.phonehealth.database.entity.PackageInfo;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class PackageInfoDataBaseUtil {
    private static class PackageInfoDataBaseUtilUtilsHolder{
        private static final PackageInfoDataBaseUtil instance=new PackageInfoDataBaseUtil();
    }
    public static PackageInfoDataBaseUtil getInstance(Context context){
        return PackageInfoDataBaseUtilUtilsHolder.instance;
    }

    public static Flowable<List<PackageInfo>> getPackagesByPackagename(String packageName){
        return RoomUtils.getDataBase(PhoneHealthApp.sApplication).packageInfoDao().findPackageInfoWithPackageName(packageName)
                .subscribeOn(Schedulers.io());

    }
    public static Flowable<List<PackageInfo>> getPackagesByAppname(String appName){
        return RoomUtils.getDataBase(PhoneHealthApp.sApplication).packageInfoDao().findPackageInfoWithAppName(appName)
                .subscribeOn(Schedulers.io());
    }
    public static Flowable<List<PackageInfo>> getPackagesByTime(int time){
        return RoomUtils.getDataBase(PhoneHealthApp.sApplication).packageInfoDao().findPackageInfoWithUserTime(time)
                .subscribeOn(Schedulers.io());
    }
    public static Flowable<List<PackageInfo>> getPackagesByUseCount(int useCount){
        return RoomUtils.getDataBase(PhoneHealthApp.sApplication).packageInfoDao().findPackageInfoWithUsedCount(useCount)
                .subscribeOn(Schedulers.io());
    }
    public static void insertByAppName(final PackageInfo packageInfo){
        if(packageInfo!=null&&packageInfo.getAppName()!=null&&!packageInfo.getAppName().equals("")){
            String appName = packageInfo.getPackageName();
            RoomUtils.getDataBase(PhoneHealthApp.sApplication).packageInfoDao().findPackageInfoWithAppName(appName)
                    .subscribeOn(Schedulers.io()).observeOn(Schedulers.io())
                    .subscribe(new Consumer<List<PackageInfo>>() {
                        @Override
                        public void accept(List<PackageInfo> packageInfos) throws Exception {
                            if(packageInfos!=null&&packageInfos.size()>0){
                                RoomUtils.getDataBase(PhoneHealthApp.sApplication).packageInfoDao().updatePackageInfos((PackageInfo[]) packageInfos.toArray());
                            }else {
                                RoomUtils.getDataBase(PhoneHealthApp.sApplication).packageInfoDao().insertPackageInfos((PackageInfo[])packageInfos.toArray());
                            }
                        }
                    });
        }
    }
    public static void deleteByAppName(final PackageInfo packageInfo){
        if(packageInfo!=null&&packageInfo.getAppName()!=null&&!packageInfo.getAppName().equals("")){
            String appName = packageInfo.getAppName();
            RoomUtils.getDataBase(PhoneHealthApp.sApplication).packageInfoDao().findPackageInfoWithAppName(appName)
                    .subscribeOn(Schedulers.io()).observeOn(Schedulers.io())
                    .subscribe(new Consumer<List<PackageInfo>>() {
                        @Override
                        public void accept(List<PackageInfo> packageInfos) throws Exception {
                            if(packageInfos!=null&&packageInfos.size()>0){
                                RoomUtils.getDataBase(PhoneHealthApp.sApplication).packageInfoDao().deletePackageInfos((PackageInfo[]) packageInfos.toArray());
                            }
                        }
                    });
        }
    }
    public static void deleteByPackageName(final PackageInfo packageInfo){
        if(packageInfo!=null&&packageInfo.getAppName()!=null&&!packageInfo.getAppName().equals("")){
            String packageName = packageInfo.getPackageName();
            RoomUtils.getDataBase(PhoneHealthApp.sApplication).packageInfoDao().findPackageInfoWithPackageName(packageName)
                    .subscribeOn(Schedulers.io()).observeOn(Schedulers.io())
                    .subscribe(new Consumer<List<PackageInfo>>() {
                        @Override
                        public void accept(List<PackageInfo> packageInfos) throws Exception {
                            if(packageInfos!=null&&packageInfos.size()>0){
                                RoomUtils.getDataBase(PhoneHealthApp.sApplication).packageInfoDao().deletePackageInfos((PackageInfo[]) packageInfos.toArray());
                            }
                        }
                    });
        }
    }
    public static void deleteByUsedCount(final PackageInfo packageInfo){
        if(packageInfo!=null&&packageInfo.getAppName()!=null&&!packageInfo.getAppName().equals("")){
            int usedCount = packageInfo.getUsedCount();
            RoomUtils.getDataBase(PhoneHealthApp.sApplication).packageInfoDao().findPackageInfoWithUsedCount(usedCount)
                    .subscribeOn(Schedulers.io()).observeOn(Schedulers.io())
                    .subscribe(new Consumer<List<PackageInfo>>() {
                        @Override
                        public void accept(List<PackageInfo> packageInfos) throws Exception {
                            if(packageInfos!=null&&packageInfos.size()>0){
                                RoomUtils.getDataBase(PhoneHealthApp.sApplication).packageInfoDao().deletePackageInfos((PackageInfo[]) packageInfos.toArray());
                            }
                        }
                    });
        }
    }

    public static void deleteByUsedTime(final PackageInfo packageInfo){
        if(packageInfo!=null&&packageInfo.getAppName()!=null&&!packageInfo.getAppName().equals("")){
            long time = packageInfo.getUsedTime();
            RoomUtils.getDataBase(PhoneHealthApp.sApplication).packageInfoDao().findPackageInfoWithUserTime(time)
                    .subscribeOn(Schedulers.io()).observeOn(Schedulers.io())
                    .subscribe(new Consumer<List<PackageInfo>>() {
                        @Override
                        public void accept(List<PackageInfo> packageInfos) throws Exception {
                            if(packageInfos!=null&&packageInfos.size()>0){
                                RoomUtils.getDataBase(PhoneHealthApp.sApplication).packageInfoDao().deletePackageInfos((PackageInfo[]) packageInfos.toArray());
                            }
                        }
                    });
        }
    }
}
