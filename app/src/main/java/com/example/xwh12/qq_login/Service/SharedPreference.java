package com.example.xwh12.qq_login.Service;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.xwh12.qq_login.Util.ConstValue;

/**
 * Created by xwh12 on 2016/7/7.
 */
public class SharedPreference {
    Context context;
    private ConstValue mConstValue;
    public SharedPreference(Context context) {
        this.context = context;
        mConstValue=new ConstValue();
    }

    public void set(String key, String value){
        if (key.equals("")||value.equals(" ")||value.equals("null")){
            Log.v("localShare","wrong input");
            return;
        }
        SharedPreferences mySharedPreferences = context.getSharedPreferences(mConstValue.APP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public String get(String key,String defaultString) {
        SharedPreferences sharedPreferences= context.getSharedPreferences(mConstValue.APP_NAME,Context.MODE_PRIVATE);
        String value =sharedPreferences.getString(key, defaultString);
        return value;
    }
}
