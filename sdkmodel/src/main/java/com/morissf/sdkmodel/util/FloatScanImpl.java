package com.morissf.sdkmodel.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AppOpsManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.Toast;

import com.morissf.sdkmodel.R;

import java.lang.reflect.Method;

/**
 * Created by lgh on 2018/5/16.
 */
public class FloatScanImpl {
    private static WindowManager wm = null;
    private static WindowManager.LayoutParams wmParams = null;
    private static FloatScanView fsv = null;
    private static AlertDialog dialog;

    public static void createFloatView(Activity activity) {
        //开启悬浮窗前先请求权限
        if ("Xiaomi".equals(Build.MANUFACTURER)) {//小米手机
            requestPermission(activity);
        } else if ("Meizu".equals(Build.MANUFACTURER)) {//魅族手机
            requestPermission(activity);
        } else {//其他手机
            if (Build.VERSION.SDK_INT >= 23) {
                if (!Settings.canDrawOverlays(activity)) {
                    Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                    activity.startActivityForResult(intent, 12);
                } else {
                    //
                }
            } else {


            }
        }


        wm = (WindowManager) activity.getApplicationContext().getSystemService(Context
                .WINDOW_SERVICE);
        wmParams = new WindowManager.LayoutParams();
        wmParams = Init.getMywmParams();


//                wmParams.type=2002;          //type是关键，这里的2002表示系统级窗口
        wmParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        wmParams.format = PixelFormat.RGBA_8888;//设置图片格式，效果为背景透明


        wmParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager
                .LayoutParams.FLAG_NOT_FOCUSABLE;//
        wmParams.gravity = Gravity.LEFT | Gravity.TOP;//
        wmParams.x = 0;
        wmParams.y = 0;
        wmParams.width = 100;
        wmParams.height = 100;
        fsv = new FloatScanView(activity.getApplicationContext());
        fsv.setImageResource(R.drawable.user_icon);
//        fsv.setBackgroundColor(activity.getResources().getColor(R.color.));
        wm.addView(fsv, wmParams);
    }

    protected void onActivityResult(Activity activity, int requestCode, int resultCode, Intent
            data) {

        if (requestCode == 11) {
            if (isFloatWindowOpAllowed(activity)) {//已经开启
            } else {
                Toast.makeText(activity, MsgInfo.Float_FAIL, Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == 12) {
            if (Build.VERSION.SDK_INT >= 23) {
                if (!Settings.canDrawOverlays(activity)) {
                    Toast.makeText(activity, "权限授予失败,无法开启悬浮窗", Toast.LENGTH_SHORT).show();
                } else {
                }
            }
        }
    }

    /**
     * 判断悬浮窗权限
     *
     * @param context
     * @return
     */
    public static boolean isFloatWindowOpAllowed(Context context) {
        final int version = Build.VERSION.SDK_INT;
        if (version >= 19) {
            return checkOp(context, 24);  // AppOpsManager.OP_SYSTEM_ALERT_WINDOW
        } else {
            if ((context.getApplicationInfo().flags & 1 << 27) == 1 << 27) {
                return true;
            } else {
                return false;
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static boolean checkOp(Context context, int op) {
        final int version = Build.VERSION.SDK_INT;


        if (version >= 19) {
            AppOpsManager manager = (AppOpsManager) context.getSystemService(Context
                    .APP_OPS_SERVICE);
            try {
                Class<?> spClazz = Class.forName(manager.getClass().getName());
                Method method = manager.getClass().getDeclaredMethod("checkOp", int.class, int
                        .class, String.class);
                int property = (Integer) method.invoke(manager, op,
                        Binder.getCallingUid(), context.getPackageName());
                Log.e("399", " property: " + property);


                if (AppOpsManager.MODE_ALLOWED == property) {
                    return true;
                } else {
                    return false;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Log.e("399", "Below API 19 cannot invoke!");
        }
        return false;
    }


    /**
     * 请求用户给予悬浮窗的权限
     */
    public static void requestPermission(Activity activity) {
        if (isFloatWindowOpAllowed(activity)) {//已经开启


        } else {
            dialog.show();
        }
    }


    /**
     * 打开权限设置界面
     */
    public void openSetting(Activity activity) {
        try {
            Intent localIntent = new Intent(
                    "miui.intent.action.APP_PERM_EDITOR");
            localIntent.setClassName("com.miui.securitycenter",
                    "com.miui.permcenter.permissions.AppPermissionsEditorActivity");
            localIntent.putExtra("extra_pkgname", activity.getPackageName());
            activity.startActivityForResult(localIntent, 11);
            //            LogUtil.E("启动小米悬浮窗设置界面");
        } catch (ActivityNotFoundException localActivityNotFoundException) {
            Intent intent1 = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            Uri uri = Uri.fromParts("package", activity.getPackageName(), null);
            intent1.setData(uri);
            activity.startActivityForResult(intent1, 11);
            //            LogUtil.E("启动悬浮窗界面");
        }


    }
}
