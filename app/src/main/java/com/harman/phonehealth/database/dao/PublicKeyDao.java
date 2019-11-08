package com.harman.phonehealth.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.harman.phonehealth.database.entity.PublicKey;

import java.util.List;

@Dao
public interface PublicKeyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertKeys(PublicKey... publicKeys);

    @Update
    void updateKeys(PublicKey... publicKeys);

    @Delete
    void deleteUsers(PublicKey... publicKeys);

    @Query("SELECT * FROM PublicKey WHERE keyMode =:search ")
    List<PublicKey> findKeyWithMode(int search);
}
