package com.morissf.sdkmodel.util;

import android.view.WindowManager;

/**
 * Created by lgh on 2018/5/16.
 */
public class Init {

    private static WindowManager.LayoutParams wmParams = new WindowManager.LayoutParams();


    public static WindowManager.LayoutParams getMywmParams() {
        return wmParams;
    }
}
