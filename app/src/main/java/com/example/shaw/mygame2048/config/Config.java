package com.example.shaw.mygame2048.config;

import android.app.Application;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Shaw on 2017/8/26.
 */

public class Config extends Application {

    //用于存储历史数据
    public static SharedPreferences mSp;
    //目标分数
    public static int mGameGoal;
    //GameView行列数
    public static int mGameLines;
    //Item宽高
    public static int mItemSize;
    //记录分数
    public static int SCORE=0;

    public static String SP_HIGH_SCORE="SP_HIGH_SCORE";

    public static String KEY_HIGH_SCORE="KRY_HIGH_SCORE";

    public static String KEY_GAME_LINES="KEY_GAME_LINES";

    public static String KEY_GAME_GOAL="KEY_GAME_GOAL";

    @Override
    public void onCreate() {
        super.onCreate();
        mSp=getSharedPreferences(SP_HIGH_SCORE,0);
        mGameLines=mSp.getInt(KEY_GAME_LINES,4);
        mGameGoal=mSp.getInt(KEY_GAME_GOAL,2048);
        mItemSize=0;
    }
}
