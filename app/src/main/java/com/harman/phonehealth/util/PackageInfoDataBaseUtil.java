package com.harman.phonehealth.util;

import android.content.Context;
import android.util.Log;

import com.harman.phonehealth.app.PhoneHealthApp;
import com.harman.phonehealth.database.entity.PackageInfo;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PackageInfoDataBaseUtil {
    public static PackageInfoDataBaseUtil getInstance(Context context) {
        return PackageInfoDataBaseUtilUtilsHolder.instance;
    }

    public static Single<List<PackageInfo>> getPackagesByPackagename(String packageName) {
        return RoomUtils.getDataBase(PhoneHealthApp.sApplication).packageInfoDao().findPackageInfoWithPackageName(packageName)
                .subscribeOn(Schedulers.single());

    }

    public static Single<List<PackageInfo>> getPackages() {
        return RoomUtils.getDataBase(PhoneHealthApp.sApplication).packageInfoDao().findPackageInfoAll()
                .subscribeOn(Schedulers.single());

    }

    public static Single<List<PackageInfo>> getPackagesByAppname(String appName) {
        return RoomUtils.getDataBase(PhoneHealthApp.sApplication).packageInfoDao().findPackageInfoWithAppName(appName)
                .subscribeOn(Schedulers.single());
    }

    public static Single<List<PackageInfo>> getPackagesByTime(int time) {
        return RoomUtils.getDataBase(PhoneHealthApp.sApplication).packageInfoDao().findPackageInfoWithUserTime(time)
                .subscribeOn(Schedulers.single());
    }

    public static Single<List<PackageInfo>> getPackagesByUseCount(int useCount) {
        return RoomUtils.getDataBase(PhoneHealthApp.sApplication).packageInfoDao().findPackageInfoWithUsedCount(useCount)
                .subscribeOn(Schedulers.single());
    }

    public static void insertByAppName(final PackageInfo packageInfo) {
        if (packageInfo != null && packageInfo.getAppName() != null && !packageInfo.getAppName().equals("")) {
            String appName = packageInfo.getAppName();
            RoomUtils.getDataBase(PhoneHealthApp.sApplication).packageInfoDao().findPackageInfoWithAppName(appName)
                    .subscribeOn(Schedulers.single()).observeOn(Schedulers.single())
                    .subscribe(new SingleObserver<List<PackageInfo>>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onSuccess(List<PackageInfo> packageInfos) {
                            Log.i("PHoneHealthAPP1", "");
                            if (packageInfos.size() == 0) {
                                List<PackageInfo> packageInfos1 = new ArrayList<>();
                                packageInfos1.add(packageInfo);
                                RoomUtils.getDataBase(PhoneHealthApp.sApplication).packageInfoDao().insertPackageInfos(packageInfos1);

                            } else {
                                RoomUtils.getDataBase(PhoneHealthApp.sApplication).packageInfoDao().updatePackageInfos(packageInfos);
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            List<PackageInfo> packageInfos1 = new ArrayList<>();
                            packageInfos1.add(packageInfo);
                            RoomUtils.getDataBase(PhoneHealthApp.sApplication).packageInfoDao().insertPackageInfos(packageInfos1);
                        }
                    });
        }
    }

    public static void deleteByAppName(final PackageInfo packageInfo) {
        if (packageInfo != null && packageInfo.getAppName() != null && !packageInfo.getAppName().equals("")) {
            String appName = packageInfo.getAppName();
            RoomUtils.getDataBase(PhoneHealthApp.sApplication).packageInfoDao().findPackageInfoWithAppName(appName)
                    .subscribeOn(Schedulers.single()).observeOn(Schedulers.single())
                    .subscribe(new SingleObserver<List<PackageInfo>>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onSuccess(List<PackageInfo> packageInfos) {
                            if (packageInfos != null && packageInfos.size() > 0) {
                                RoomUtils.getDataBase(PhoneHealthApp.sApplication).packageInfoDao().deletePackageInfos(packageInfos);
                            }
                        }

                        @Override
                        public void onError(Throwable e) {

                        }
                    });
        }
    }

    public static void deleteByPackageName(final PackageInfo packageInfo) {
        if (packageInfo != null && packageInfo.getAppName() != null && !packageInfo.getAppName().equals("")) {
            String packageName = packageInfo.getPackageName();
            RoomUtils.getDataBase(PhoneHealthApp.sApplication).packageInfoDao().findPackageInfoWithPackageName(packageName)
                    .subscribeOn(Schedulers.single()).observeOn(Schedulers.single())
                    .subscribe(new SingleObserver<List<PackageInfo>>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onSuccess(List<PackageInfo> packageInfos) {
                            if (packageInfos != null && packageInfos.size() > 0) {
                                RoomUtils.getDataBase(PhoneHealthApp.sApplication).packageInfoDao().deletePackageInfos(packageInfos);
                            }
                        }

                        @Override
                        public void onError(Throwable e) {

                        }
                    });
        }
    }

    public static void deleteByUsedCount(final PackageInfo packageInfo) {
        if (packageInfo != null && packageInfo.getAppName() != null && !packageInfo.getAppName().equals("")) {
            int usedCount = packageInfo.getUsedCount();
            RoomUtils.getDataBase(PhoneHealthApp.sApplication).packageInfoDao().findPackageInfoWithUsedCount(usedCount)
                    .subscribeOn(Schedulers.single()).observeOn(Schedulers.single())
                    .subscribe(new SingleObserver<List<PackageInfo>>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onSuccess(List<PackageInfo> packageInfos) {
                            if (packageInfos != null && packageInfos.size() > 0) {
                                RoomUtils.getDataBase(PhoneHealthApp.sApplication).packageInfoDao().deletePackageInfos(packageInfos);
                            }
                        }

                        @Override
                        public void onError(Throwable e) {

                        }
                    });
        }
    }

    public static void deleteByUsedTime(final PackageInfo packageInfo) {
        if (packageInfo != null && packageInfo.getAppName() != null && !packageInfo.getAppName().equals("")) {
            long time = packageInfo.getUsedTime();
            RoomUtils.getDataBase(PhoneHealthApp.sApplication).packageInfoDao().findPackageInfoWithUserTime(time)
                    .subscribeOn(Schedulers.single()).observeOn(Schedulers.single())
                    .subscribe(new SingleObserver<List<PackageInfo>>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onSuccess(List<PackageInfo> packageInfos) {
                            if (packageInfos != null && packageInfos.size() > 0) {
                                RoomUtils.getDataBase(PhoneHealthApp.sApplication).packageInfoDao().deletePackageInfos(packageInfos);
                            }
                        }

                        @Override
                        public void onError(Throwable e) {

                        }
                    });
        }
    }

    private static class PackageInfoDataBaseUtilUtilsHolder {
        private static final PackageInfoDataBaseUtil instance = new PackageInfoDataBaseUtil();
    }
}
