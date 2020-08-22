package com.abhi.aiob;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;

public class Mysharedpref {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;
    String firstTime = "FisrtTime";

    Mysharedpref(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void setFirstTime(boolean first) {
        editor.putBoolean(firstTime, first);
        editor.commit();
    }

    public boolean is_First_Time() {
        boolean check = sharedPreferences.getBoolean(firstTime, true);
        return check;
    }
}
