package com.harman.phonehealth.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.harman.phonehealth.database.dao.PublicKeyDao;
import com.harman.phonehealth.database.entity.PublicKey;

@Database(entities = {PublicKey.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {
    public abstract PublicKeyDao keyDao();
}

