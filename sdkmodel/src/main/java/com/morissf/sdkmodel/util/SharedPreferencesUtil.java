package com.morissf.sdkmodel.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesUtil {

    private static SharedPreferences getSharedPreferences(Context mContext) {
        return mContext.getSharedPreferences(mContext.getPackageName(), Context.MODE_PRIVATE);
    }

    private static String getClassName() {
        return SharedPreferencesUtil.class.getName();
    }

    public static boolean putString(Context mContext, String key, String value) {
        return getSharedPreferences(mContext).edit().putString(mContext.getPackageName() + getClassName() + key, value).commit();
    }

    public static String getString(Context mContext, String key, String defaultValue) {
        return getSharedPreferences(mContext).getString(mContext.getPackageName() + getClassName() + key, defaultValue);
    }


    public static boolean putInt(Context mContext, String key, int value) {
        return getSharedPreferences(mContext).edit().putInt(mContext.getPackageName() + getClassName() + key, value).commit();
    }


    public static int getInt(Context mContext, String key, int defaultValue) {
        return getSharedPreferences(mContext).getInt(mContext.getPackageName() + getClassName() + key, defaultValue);
    }


    public static boolean putLong(Context mContext, String key, long value) {
        return getSharedPreferences(mContext).edit().putLong(mContext.getPackageName() + getClassName() + key, value).commit();
    }

    public static long getLong(Context mContext, String key, long defaultValue) {
        return getSharedPreferences(mContext).getLong(mContext.getPackageName() + getClassName() + key, defaultValue);
    }


    public static boolean putFloat(Context mContext, String key, float value) {
        return getSharedPreferences(mContext).edit().putFloat(mContext.getPackageName() + getClassName() + key, value).commit();
    }

    public static float getFloat(Context mContext, String key, float defaultValue) {
        return getSharedPreferences(mContext).getFloat(mContext.getPackageName() + getClassName() + key, defaultValue);
    }


    public static boolean putBoolean(Context mContext, String key, boolean value) {
        return getSharedPreferences(mContext).edit().putBoolean(mContext.getPackageName() + getClassName() + key, value).commit();
    }


    public static boolean getBoolean(Context mContext, String key, boolean defaultValue) {
        return getSharedPreferences(mContext).getBoolean(mContext.getPackageName() + getClassName() + key, defaultValue);
    }
}

