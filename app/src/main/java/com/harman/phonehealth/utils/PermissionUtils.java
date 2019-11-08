package com.harman.phonehealth.utils;

import android.app.AppOpsManager;
import android.content.Context;

public class PermissionUtils {
    public static boolean checkUsagePermission(Context context) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            AppOpsManager appOps = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
            int mode = appOps.checkOpNoThrow("android:get_usage_stats", android.os.Process.myUid(), context.getPackageName());
            boolean granted = mode == AppOpsManager.MODE_ALLOWED;
            if (!granted) {
                return false;
            }
        }
        return true;
    }
}
