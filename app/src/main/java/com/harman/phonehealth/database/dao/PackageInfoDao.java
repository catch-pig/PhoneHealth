package com.harman.phonehealth.database.dao;

import com.harman.phonehealth.database.entity.PackageInfo;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import io.reactivex.Flowable;

@Dao
public interface PackageInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertPackageInfos(PackageInfo... publicKeys);
    @Update
    public void updatePackageInfos(PackageInfo... publicKeys);
    @Delete
    public void deletePackageInfos(PackageInfo... publicKeys);
    @Query("SELECT * FROM PackageInfo")
    Flowable <List<PackageInfo>> findPackageInfoAll();

    @Query("SELECT * FROM PackageInfo WHERE usedCount =:search")
    Flowable <List<PackageInfo>> findPackageInfoWithUsedCount(int search);

    @Query("SELECT * FROM PackageInfo WHERE usedTime =:search")
    Flowable <List<PackageInfo>> findPackageInfoWithUserTime(long search);

    @Query("SELECT * FROM PackageInfo WHERE packageName =:search")
    Flowable <List<PackageInfo>> findPackageInfoWithPackageName(String search);

    @Query("SELECT * FROM PackageInfo WHERE appName =:search")
    Flowable <List<PackageInfo>> findPackageInfoWithAppName(String search);
}
