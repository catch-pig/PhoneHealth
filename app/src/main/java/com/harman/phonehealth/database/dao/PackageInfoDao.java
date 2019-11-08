package com.harman.phonehealth.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.harman.phonehealth.database.entity.PackageInfo;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface PackageInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPackageInfos(List<PackageInfo> publicKeys);

    @Update
    void updatePackageInfos(List<PackageInfo> publicKeys);

    @Delete
    void deletePackageInfos(List<PackageInfo> publicKeys);

    @Query("SELECT * FROM PackageInfo")
    Single<List<PackageInfo>> findPackageInfoAll();

    @Query("SELECT * FROM PackageInfo WHERE usedCount =:search")
    Single<List<PackageInfo>> findPackageInfoWithUsedCount(int search);

    @Query("SELECT * FROM PackageInfo WHERE usedTime =:search")
    Single<List<PackageInfo>> findPackageInfoWithUserTime(long search);

    @Query("SELECT * FROM PackageInfo WHERE packageName =:search")
    Single<List<PackageInfo>> findPackageInfoWithPackageName(String search);

    @Query("SELECT * FROM PackageInfo WHERE appName =:search")
    Single<List<PackageInfo>> findPackageInfoWithAppName(String search);
}
