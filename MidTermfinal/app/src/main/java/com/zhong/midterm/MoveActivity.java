package com.zhong.midterm;

import android.content.pm.ActivityInfo;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.Toast;

import org.litepal.crud.DataSupport;

import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MoveActivity extends AppCompatActivity implements OnProgressBarListener{
    float dx,dy;
    private NumberProgressBar left_live_bnp;
    private NumberProgressBar left_attr_bnp;
    private NumberProgressBar left_def_bnp;

    private NumberProgressBar right_live_bnp;
    private NumberProgressBar right_attr_bnp;
    private NumberProgressBar right_def_bnp;

    private SoundPool mSoundPool = null;
    private HashMap<Integer, Integer> soundID = new HashMap<Integer, Integer>();
    private Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        getSupportActionBar().hide();

        setContentView(R.layout.activity_move);
        //timer.schedule(task, delay, period)
        // delay为long,period为long：从现在起过delay毫秒以后，每隔period
        // 毫秒执行一次。
        //timer.schedule(task, firstTime, period);
        // firstTime为Date类型,period为long
        // 从firstTime时刻开始，每隔period毫秒执行一次。

        //final User_heros people = (String) getIntent().getSerializableExtra("youruser_idid");

        try {
            initSP();
        } catch (Exception e) {
            e.printStackTrace();
        }


        final User_heros people = (User_heros) getIntent().getSerializableExtra("user_people");
        final String yourname= String.valueOf(people.getUser_id());
        final String heroname = people.getName();

        final List<User_heros> fight = DataSupport.where("user_id=? and name =?",yourname,heroname).find(User_heros.class);

        Log.d("Debug",fight.get(0).getName());
        ImageView left_people = (ImageView)findViewById(R.id.left_people);


        left_people.setImageResource(fight.get(0).getImage());

        left_live_bnp = (NumberProgressBar)findViewById(R.id.left_live);
        int live = fight.get(0).getLive();
        Log.d("Debug",String.valueOf(live));
        left_live_bnp.setProgress(live);


        left_attr_bnp = (NumberProgressBar)findViewById(R.id.left_attr);
        int attr = fight.get(0).getAttack();
        Log.d("Debug",String.valueOf(attr));
        left_attr_bnp.setProgress(attr);

        left_def_bnp = (NumberProgressBar)findViewById(R.id.left_def);
        int def = fight.get(0).getDefense();
        Log.d("Debug",String.valueOf(def));
        left_def_bnp.setProgress(def);


        right_live_bnp = (NumberProgressBar)findViewById(R.id.right_live);
        right_live_bnp.setProgress(20);


        right_attr_bnp = (NumberProgressBar)findViewById(R.id.right_attr);
        right_attr_bnp.setProgress(20);

        right_def_bnp = (NumberProgressBar)findViewById(R.id.right_def);
        right_def_bnp.setProgress(70);


        ImageView LeftBingqi = (ImageView)findViewById(R.id.left);
        LeftBingqi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mSoundPool.play(soundID.get(1), 1, 1, 0, 0, 1);

                AnimationSet animationSet = new AnimationSet(true);//共用动画
                animationSet.setDuration(1000);
                RotateAnimation rotateAnimation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, -0.3f, Animation.RELATIVE_TO_SELF, 0f);
                //上述参数解释分别为：旋转起始角度，旋转结束角度，相对与自身，x轴方向的一半，相对于自身，y轴方向的一半
                rotateAnimation.setDuration(1000);
                animationSet.addAnimation(rotateAnimation);
                TranslateAnimation translateAnimation = new TranslateAnimation(0,1100,0,0);
                //起始x轴，最终x轴，起始y轴，最终y轴
                translateAnimation.setDuration(1000);
                animationSet.addAnimation(translateAnimation);
                v.startAnimation(animationSet);


                AnimationSet animationSet2 = new AnimationSet(true);
                //animationSet2.setDuration(2000);
                ImageView Right_people = (ImageView) findViewById(R.id.right_people);
                AlphaAnimation alphaAnimation6 = new AlphaAnimation(255, 255);
                alphaAnimation6.setDuration(100);
                animationSet2.addAnimation(alphaAnimation6);
                AlphaAnimation alphaAnimation2 = new AlphaAnimation(1, 0);
                alphaAnimation2.setDuration(1000);
                animationSet2.addAnimation(alphaAnimation2);
                Right_people.startAnimation(animationSet2);

                right_live_bnp.setOnProgressBarListener(MoveActivity.this);
                timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                right_live_bnp.incrementProgressBy(10);
                            }
                        });
                    }
                }, 1000);
            }
        });

        ImageView RightBingqi = (ImageView)findViewById(R.id.right);

        RightBingqi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mSoundPool.play(soundID.get(2), 1, 1, 0, 0, 1);

                ImageView Right = (ImageView)findViewById(R.id.right);
                AnimationSet animationSet3 = new AnimationSet(true);//共用动画
                animationSet3.setDuration(1000);
                TranslateAnimation translateAnimation3 = new TranslateAnimation(0,-1100,0,0);
                //起始x轴，最终x轴，起始y轴，最终y轴
                translateAnimation3.setDuration(1000);
                animationSet3.addAnimation(translateAnimation3);
                Right.startAnimation(animationSet3);

                AnimationSet animationSet4 = new AnimationSet(true);
                //animationSet4.setDuration(2000);
                ImageView Left_people = (ImageView) findViewById(R.id.left_people);
                AlphaAnimation alphaAnimation5 = new AlphaAnimation(255, 255);
                alphaAnimation5.setDuration(100);
                animationSet4.addAnimation(alphaAnimation5);
                AlphaAnimation alphaAnimation4 = new AlphaAnimation(1, 0);
                alphaAnimation4.setDuration(800);
                animationSet4.addAnimation(alphaAnimation4);
                Left_people.startAnimation(animationSet4);

                left_live_bnp.setOnProgressBarListener(MoveActivity.this);
                timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                left_live_bnp.incrementProgressBy(-5);
                            }
                        });
                    }
                }, 1000);
            }
        });
    }

    public int getStatusBarHeight(){
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }


    public int getTitleBarHeight(){
        Window window = getWindow();
        int contentViewTop = getWindow()
                .findViewById(Window.ID_ANDROID_CONTENT).getTop();
        // statusBarHeight是上面所求的状态栏的高度
        int titleBarHeight = contentViewTop - getStatusBarHeight();
        return titleBarHeight;
    }

    @Override
    protected void onResume() {
        /**
         * 设置为横屏
         */
        if(getRequestedOrientation()!= ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        super.onResume();
    }


    @Override
    public void onProgressChange(int current, int max) {
        if(current <= 0) {
            Toast.makeText(getApplicationContext(), "Over", Toast.LENGTH_SHORT).show();
            left_live_bnp.setProgress(100);
        }
    }


    private void initSP() throws Exception {
        //当前系统的SDK版本大于等于21(Android 5.0)时
        if (Build.VERSION.SDK_INT >= 21) {
            SoundPool.Builder builder = new SoundPool.Builder();
            //传入音频数量
            builder.setMaxStreams(2);
            //AudioAttributes是一个封装音频各种属性的方法
            AudioAttributes.Builder attrBuilder = new AudioAttributes.Builder();
            //设置音频流的合适的属性
            attrBuilder.setLegacyStreamType(AudioManager.STREAM_MUSIC);
            //加载一个AudioAttributes
            builder.setAudioAttributes(attrBuilder.build());
            mSoundPool = builder.build();
        }
        //当系统的SDK版本小于21时
        else {//设置最多可容纳2个音频流，音频的品质为5
            mSoundPool = new SoundPool(2, AudioManager.STREAM_SYSTEM, 5);
        }

        soundID.put(1, mSoundPool.load(this, R.raw.hit, 1));
        soundID.put(2, mSoundPool.load(this,R.raw.hit3, 1));  //需要捕获IO异常  //需要捕获IO异常
    }

}
