package com.morissf.gamesdkmodel;

import android.app.Activity;
import android.content.res.Configuration;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.morissf.sdkmodel.main.MsegListener;
import com.morissf.sdkmodel.main.PopupIndex;
import com.morissf.sdkmodel.main.Result;

public class MainActivity extends AppCompatActivity {
    private Button mButton;
    private Activity mActivity;
    private boolean isLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        isLogin = false;
        mActivity = this;
        mButton = (Button) findViewById(R.id.btn_show);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isLogin){
                    gameSdkTest();
                }else {
                    Toast.makeText(MainActivity.this,"用户已登录",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){
            setContentView(R.layout.activity_main);
        }
    }

    public void gameSdkTest(){
        PopupIndex.showPop(mActivity, new MsegListener() {
            @Override
            public void onMsegResult(final Result mResult) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        switch (mResult) {
                            case SUCCESS:
                                isLogin = true;
                                break;
                            default:
                                break;
                        }
                    }
                });
            }
        });
    }
}
