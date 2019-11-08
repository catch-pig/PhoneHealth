package com.harman.phonehealth.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

public class IconUtils {
    public static Drawable getAppIcon(Context context,String packageName){
        PackageManager packageManager = context.getPackageManager();
        Drawable drawable = null;
        try {
            drawable = packageManager.getApplicationIcon(packageName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return drawable;
    }
}
