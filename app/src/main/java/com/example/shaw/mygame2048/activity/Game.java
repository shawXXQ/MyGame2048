package com.example.shaw.mygame2048.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.shaw.mygame2048.R;
import com.example.shaw.mygame2048.config.Config;
import com.example.shaw.mygame2048.view.GameView;

public class Game extends AppCompatActivity implements View.OnClickListener {
    //Activity的引用
    private static Game mGame;
    //记录分数
    private TextView mTvScore;
    //历史记录分数
    private TextView mTvHightScore;
    private int mHightScore;
    //目标分数
    private TextView mTvGoal;
    private int mGoal;
    //重新开始按钮
    private Button mBtnRestart;
    //撤销按钮
    private Button mBtnRevert;
    //游戏按钮
    private Button mBtnOptions;
    //游戏面板
    private GameView mGameView;

    public Game() {
        mGame = this;
    }

    /**
     * 获取当前Activity的引用
     *
     * @return
     */
    public static Game getGameActivity() {
        return mGame;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        //初始化View
        initView();
        mGameView = new GameView(this);
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.game_panel);
        //为了GameView能够居中显示
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.game_panel_rl);
        relativeLayout.addView(mGameView);
    }

    /**
     * 初始化 View
     */
    private void initView() {
        mTvScore = (TextView) findViewById(R.id.score);
        mTvGoal = (TextView) findViewById(R.id.tv_Goal);
        mTvHightScore = (TextView) findViewById(R.id.record);
        mBtnOptions = (Button) findViewById(R.id.btn_option);
        mBtnRestart = (Button) findViewById(R.id.btn_restart);
        mBtnRevert = (Button) findViewById(R.id.btn_revert);
        mBtnRevert.setOnClickListener(this);
        mBtnRestart.setOnClickListener(this);
        mBtnOptions.setOnClickListener(this);
        mHightScore = Config.mSp.getInt(Config.KEY_HIGH_SCORE, 0);
        mGoal = Config.mSp.getInt(Config.KEY_GAME_GOAL, 2048);
        mTvHightScore.setText("" + mHightScore);
        mTvGoal.setText("" + mGoal);
        mTvScore.setText("0");
        setScore(0, 0);
    }

    public void setGoal(int num) {
        mTvGoal.setText(String.valueOf(num));
    }

    /**
     * 修改得分
     *
     * @param score
     * @param flag  0：当前分数 1：新高分数
     */
    public void setScore(int score, int flag) {
        switch (flag) {
            case 0:
                mTvScore.setText("" + score);
                break;
            case 1:
                mTvHightScore.setText("" + score);
                break;
            default:
                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_restart:
                mGameView.startGame();
                setScore(0, 0);
                break;
            case R.id.btn_revert:
                mGameView.revertGame();
                break;
            case R.id.btn_option:
                Intent intent = new Intent(Game.this, ConfigPreference.class);
                startActivityForResult(intent, 0);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            mGoal = Config.mSp.getInt(Config.KEY_GAME_GOAL, 2048);
            mTvGoal.setText("" + mGoal);
            getHighScore();
            mGameView.startGame();
        }
    }

    /**
     * 获取最高记录
     */
    private void getHighScore() {
        int score = Config.mSp.getInt(Config.KEY_HIGH_SCORE, 0);
        setScore(score, 1);
    }
}
