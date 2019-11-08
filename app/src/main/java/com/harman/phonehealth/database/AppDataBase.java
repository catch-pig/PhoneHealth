package com.harman.phonehealth.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.harman.phonehealth.database.dao.PackageInfoBeanDAO;
import com.harman.phonehealth.database.dao.PackageInfoDao;
import com.harman.phonehealth.database.dao.PublicKeyDao;
import com.harman.phonehealth.database.entity.PackageInfo;
import com.harman.phonehealth.database.entity.PublicKey;
import com.harman.phonehealth.entity.PackageInfoBean;

@Database(entities = {PublicKey.class, PackageInfo.class, PackageInfoBean.class}, version = 1, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {
    public abstract PublicKeyDao keyDao();

    public abstract PackageInfoDao packageInfoDao();

    public abstract PackageInfoBeanDAO packageInfoBeanDao();
}

