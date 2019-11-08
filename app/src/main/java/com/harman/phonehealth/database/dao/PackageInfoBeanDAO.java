package com.harman.phonehealth.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.harman.phonehealth.database.entity.PackageInfo;
import com.harman.phonehealth.entity.PackageInfoBean;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface PackageInfoBeanDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPackageInfos(List<PackageInfoBean> publicKeys);
    @Update
    void updatePackageInfos(List<PackageInfoBean> publicKeys);
    @Delete
    void deletePackageInfos(List<PackageInfoBean> publicKeys);

    @Query("delete from PackageInfoBean")
    void deletePackageInfoBean();

    @Query("SELECT * FROM PackageInfoBean")
    Single <List<PackageInfoBean>> findPackageInfoAll();

    @Query("SELECT * FROM PackageInfoBean WHERE usedCount =:search")
    Single<List<PackageInfoBean>> findPackageInfoWithUsedCount(int search);

    @Query("SELECT * FROM PackageInfoBean WHERE usedTime =:search")
    Single <List<PackageInfoBean>> findPackageInfoWithUserTime(long search);

    @Query("SELECT * FROM PackageInfoBean WHERE packageName =:search")
    Single <List<PackageInfoBean>> findPackageInfoWithPackageName(String search);

    @Query("SELECT * FROM PackageInfoBean WHERE appName =:search")
    Single <List<PackageInfoBean>> findPackageInfoWithAppName(String search);

    @Query("SELECT * FROM PackageInfoBean WHERE date =:time order by usedTime desc ")
    Single <List<PackageInfoBean>> findPackageInfoSomeDayByUsedTimeDesc(long time);

    @Query("SELECT * FROM PackageInfoBean WHERE date BETWEEN :start AND :end order by usedTime desc")
    Single <List<PackageInfoBean>> findPackageInfoSomeSevenDayUsedTimeDesc(long start,long end);

    @Query("SELECT * FROM PackageInfoBean WHERE date BETWEEN :start AND :end  and appName =:appName order by usedTime asc")
    Single <List<PackageInfoBean>> findPackageInfoSomeAppSevenDayUsedTimeAsc(String appName,long start,long end);

    @Query("SELECT * FROM PackageInfoBean WHERE date =:time and appName =:appName order by usedTime asc ")
    Single <List<PackageInfoBean>> findPackageInfoSomeAppSomeDayByUsedTimeAsc(String appName,long time);

    @Query("SELECT * FROM PackageInfoBean WHERE date BETWEEN :start AND :end order by usedCount asc")
    Single <List<PackageInfoBean>> findPackageInfoSomeAppSevenDayByUseCountAsc(long start,long end);

}
