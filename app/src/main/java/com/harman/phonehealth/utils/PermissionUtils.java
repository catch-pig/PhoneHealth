package com.harman.phonehealth.utils;

import android.app.AppOpsManager;
import android.content.Context;
import android.content.Intent;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.harman.phonehealth.mvp.main.view.MainActivity;

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
        Intent intent = new Intent(MainActivity.ACTION_USAGE_ACCESS);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
        return true;
    }
}
