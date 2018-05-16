package com.morissf.sdkmodel.main;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.PopupWindow;
import android.widget.Toast;


import com.morissf.sdkmodel.R;
import com.morissf.sdkmodel.RegisterUtil.RegisterUtil;
import com.morissf.sdkmodel.util.MsgInfo;

/**
 * Created by lgh on 2018/5/16.
 * SDK首页弹窗
 */
public class LoginManager {

    public static void login_now(final Activity activity, final View view, final PopupWindow popupWindow, final MsegListener resultListener){

        final CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkbox);
        Button nowPlay = (Button) view.findViewById(R.id.btn_now);
        nowPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkBox.isChecked()){
                    popupWindow.dismiss();
                }else {
                    Toast.makeText(activity, MsgInfo.USERWORD,Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button mcPlay = (Button) view.findViewById(R.id.btn_mc);
        mcPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkBox.isChecked()){
                    RegisterUtil.showRegister(activity,resultListener);
                    popupWindow.dismiss();
                }else {
                    Toast.makeText(activity,MsgInfo.USERWORD,Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button fccePlay = (Button) view.findViewById(R.id.btn_face);
        fccePlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkBox.isChecked()){
                    Toast.makeText(activity,"请登录Facebook账号",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(activity,"请先阅读并同意用户协议",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
