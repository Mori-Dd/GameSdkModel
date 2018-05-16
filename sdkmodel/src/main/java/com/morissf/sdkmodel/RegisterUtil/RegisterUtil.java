package com.morissf.sdkmodel.RegisterUtil;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import com.morissf.sdkmodel.R;
import com.morissf.sdkmodel.main.MsegListener;

/**
 * Created by lgh on 2018/5/16.
 * 注册界面弹窗
 */
public class RegisterUtil {

    public static void showRegister(final Activity activity,MsegListener resultListener) {

        View popupView = activity.getLayoutInflater().inflate(R.layout.layout_register, null);
        PopupWindow mPopupwindow = new PopupWindow(popupView, LinearLayout.LayoutParams
                .MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);
        //产生背景变暗效果
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = 0.4f;
        //popupWindow获取焦点
        mPopupwindow.setFocusable(true);
        mPopupwindow.setTouchable(true);
        //点击外面popupWindow不消失
        mPopupwindow.setOutsideTouchable(true);
        mPopupwindow.setBackgroundDrawable(new BitmapDrawable(activity.getResources(), (Bitmap)
                null));
        mPopupwindow.showAsDropDown(popupView);
        mPopupwindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
                lp.alpha = 1f;
                activity.getWindow().setAttributes(lp);
            }
        });

        mPopupwindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);

        RegisterManager.registerLogin(activity, popupView, mPopupwindow,resultListener);
    }
}
