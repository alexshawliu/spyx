package com.hy.spyx;

import android.app.Application;
import android.content.Context;
import android.util.Log;


public class NationApp extends Application {
    public static final String NT_LOG_TAG="NATION";
    public static final String APP_INFO="INFO";
    public static final String ALLCOUNT="ALLCOUNT";
    public static final String WINCOUNT="WINCOUNT";
    public static final String TOTALTIME="TOTALTIME";

    public static Context getAppContext() {
        return NationApp.context;
    }

    public void setUserInfo(int exp,int win,long used){
        mExp = exp;
        mWin = win;
        mUsed = used;
    }

    public int getExp(){
        return mExp;
    }

    public int getWin(){
        return mWin;
    }

    public long getUsed(){
        return mUsed;
    }

    public double getWinRate(){
        if (mExp==0){
            return 0.0;
        }
        return 100*((double)mWin/(double)mExp);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        NationApp.context = getApplicationContext();
    }

    @Override
    public void onTerminate() {
        // 程序终止的时候执行
        Log.d(NationApp.NT_LOG_TAG, "NationApp onTerminate");
        super.onTerminate();
    }

    private static Context context;
    private int mExp;
    private int mWin;
    private long mUsed;
}
