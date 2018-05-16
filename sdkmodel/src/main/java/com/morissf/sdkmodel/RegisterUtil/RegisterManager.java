package com.morissf.sdkmodel.RegisterUtil;

import android.app.Activity;
import android.app.ProgressDialog;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.morissf.sdkmodel.R;
import com.morissf.sdkmodel.main.MsegListener;
import com.morissf.sdkmodel.main.Result;
import com.morissf.sdkmodel.util.FloatScanImpl;
import com.morissf.sdkmodel.util.MsgInfo;
import com.morissf.sdkmodel.util.SharedPreferencesUtil;

/**
 * Created by lgh on 2018/5/16.
 * 注册界面
 */
public class RegisterManager {

    public static void registerLogin(final Activity activity, final View view, final PopupWindow
            popupWindow, final MsegListener resultListener) {
        final EditText account = (EditText) view.findViewById(R.id.edit_account);
        final EditText password1 = (EditText) view.findViewById(R.id.edit_psw);
        final EditText password2 = (EditText) view.findViewById(R.id.edit_psws);
        final ProgressDialog dialog = getDialog(activity);
        Button isHidden = (Button) view.findViewById(R.id.show_psw);
        isHidden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password1.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                password2.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }
        });

        Button registerBtn = (Button) view.findViewById(R.id.login_register);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                String act = account.getText().toString();
                                String psw1 = password1.getText().toString();
                                String psw2 = password1.getText().toString();
                                showProDialog(dialog);
                                try{
                                    Thread.sleep(1500);
                                }catch (InterruptedException e){

                                }
                                if (!act.equals("") && !psw1.equals("") && !psw2.equals("") && psw1.equals(psw2) && psw1.length() >= 6 && psw2.length() >= 6){
                                    SharedPreferencesUtil.putString(activity, MsgInfo.ACCOUNT,account.getText().toString());
                                    SharedPreferencesUtil.putString(activity, MsgInfo.PASSWORD,password1.getText().toString());
                                    popupWindow.dismiss();
                                    dismissProDialog(dialog);
                                    Toast.makeText(activity,MsgInfo.SUCCEED_LOGIN,Toast.LENGTH_SHORT).show();
                                    FloatScanImpl.createFloatView(activity);
                                    if (resultListener != null){
                                        resultListener.onMsegResult(Result.SUCCESS);
                                    }
                                }else {
                                    Toast.makeText(activity,MsgInfo.HINTMSG,Toast.LENGTH_SHORT).show();
                                    dismissProDialog(dialog);
                                }
                            }
                        });

                    }
                }).start();

            }
        });
    }

    private static ProgressDialog getDialog(Activity activity){
        ProgressDialog dialog = new ProgressDialog(activity);
        dialog.setMessage("Loading...");//设置提示语
        dialog.setCancelable(false);//返回键是否能退出对话框
        return dialog;
    }
    private static void showProDialog(ProgressDialog dialog){
        dialog.show();
    }
    private static void dismissProDialog(ProgressDialog dialog){
        dialog.dismiss();
    }
}