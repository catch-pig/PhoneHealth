package com.harman.phonehealth.database.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class PublicKey {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String keyValue;
    private int keyMode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKeyValue() {
        return keyValue;
    }

    public void setKeyValue(String keyValue) {
        this.keyValue = keyValue;
    }

    public int getKeyMode() {
        return keyMode;
    }

    public void setKeyMode(int keyMode) {
        this.keyMode = keyMode;
    }

    @Override
    public String toString() {
        return "key----id:" + getId() + " keyvalue:" + getKeyValue() + " keyMode:" + getKeyMode();
    }
}
