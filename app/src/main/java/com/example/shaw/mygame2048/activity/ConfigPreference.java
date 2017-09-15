package com.example.shaw.mygame2048.activity;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.shaw.mygame2048.R;
import com.example.shaw.mygame2048.config.Config;

public class ConfigPreference extends AppCompatActivity implements View.OnClickListener {
    private Button mBtnGameLines;
    private Button mBtnGoal;
    private Button mBtnBack;
    private Button mBtnDone;
    private String[] mGameLinesList;
    private String[] mGameGoalList;
    private AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.config_preference);
        initView();
    }

    private void initView() {
        mBtnBack = (Button) findViewById(R.id.btn_back);
        mBtnDone = (Button) findViewById(R.id.btn_done);
        mBtnGameLines = (Button) findViewById(R.id.btn_gamelines);
        mBtnGoal = (Button) findViewById(R.id.btn_goal);
        mBtnGameLines.setText("" + Config.mSp.getInt(Config.KEY_GAME_LINES, 4));
        mBtnGoal.setText("" + Config.mSp.getInt(Config.KEY_GAME_GOAL, 2048));
        mBtnGameLines.setOnClickListener(this);
        mBtnGoal.setOnClickListener(this);
        mBtnBack.setOnClickListener(this);
        mBtnDone.setOnClickListener(this);
        mGameLinesList = new String[]{"4", "5", "6"};
        mGameGoalList = new String[]{"1024", "2048", "4096"};
    }

    private void saveConfig() {
        SharedPreferences.Editor editor = Config.mSp.edit();
        editor.putInt(Config.KEY_GAME_LINES,
                Integer.parseInt(mBtnGameLines.getText().toString()));
        editor.putInt(Config.KEY_GAME_GOAL,
                Integer.parseInt(mBtnGoal.getText().toString()));
        editor.commit();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                this.finish();
                break;
            case R.id.btn_done:
                saveConfig();
                setResult(RESULT_OK);
                this.finish();
                break;
            case R.id.btn_gamelines:
                builder = new AlertDialog.Builder(this);
                builder.setTitle("Choose the line of the game");
                builder.setItems(mGameLinesList, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mBtnGameLines.setText(mGameLinesList[i]);
                    }
                });
                builder.create().show();
                break;
            case R.id.btn_goal:
                builder = new AlertDialog.Builder(this);
                builder.setTitle("Choose the goal of the game");
                builder.setItems(mGameGoalList, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mBtnGoal.setText(mGameGoalList[i]);
                    }
                });
                builder.create().show();
                break;
            default:
                break;
        }
    }
}
