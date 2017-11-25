package com.zhong.midterm;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.ButterKnife;

public class MoveActivity extends AppCompatActivity implements OnProgressBarListener{
    float dx,dy;
    private NumberProgressBar bnp;
    private Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move);
        ButterKnife.bind(this);

        bnp = (NumberProgressBar)findViewById(R.id.numberbar1);


        //timer.schedule(task, delay, period)
        // delay为long,period为long：从现在起过delay毫秒以后，每隔period
        // 毫秒执行一次。
        //timer.schedule(task, firstTime, period);
        // firstTime为Date类型,period为long
        // 从firstTime时刻开始，每隔period毫秒执行一次。


        bnp = (NumberProgressBar)findViewById(R.id.left_live);
        bnp.setProgress(100);
        ImageView Left = (ImageView)findViewById(R.id.left);
        Left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);//透明度从0~1
//                alphaAnimation.setDuration(1000);//持续时间
//                v.startAnimation(alphaAnimation);
                AnimationSet animationSet = new AnimationSet(true);//共用动画
                animationSet.setDuration(1000);

                RotateAnimation rotateAnimation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, -0.5f, Animation.RELATIVE_TO_SELF, -0.5f);
                //上述参数解释分别为：旋转起始角度，旋转结束角度，相对与自身，x轴方向的一半，相对于自身，y轴方向的一半
                rotateAnimation.setDuration(1000);
                animationSet.addAnimation(rotateAnimation);

                TranslateAnimation translateAnimation = new TranslateAnimation(0,1100,0,0);
                //起始x轴，最终x轴，起始y轴，最终y轴
                translateAnimation.setDuration(1000);
                animationSet.addAnimation(translateAnimation);

                v.startAnimation(animationSet);

                AnimationSet animationSet2 = new AnimationSet(true);
                animationSet2.setDuration(2000);
                ImageView Right_people = (ImageView) findViewById(R.id.right_people);

                AlphaAnimation alphaAnimation2 = new AlphaAnimation(1, 0);
                alphaAnimation2.setDuration(500);
                animationSet2.addAnimation(alphaAnimation2);

                Right_people.startAnimation(animationSet2);

                bnp.setOnProgressBarListener(MoveActivity.this);
                timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                bnp.incrementProgressBy(-10);
                            }
                        });
                    }
                }, 1000);
            }
        });

        ImageView Right = (ImageView)findViewById(R.id.right);

        Right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimationSet animationSet = new AnimationSet(true);//共用动画
                animationSet.setDuration(1000);

                TranslateAnimation translateAnimation = new TranslateAnimation(0,-1100,0,0);
                //起始x轴，最终x轴，起始y轴，最终y轴
                translateAnimation.setDuration(1000);
                animationSet.addAnimation(translateAnimation);

                v.startAnimation(animationSet);

                AnimationSet animationSet2 = new AnimationSet(true);
                animationSet2.setDuration(1000);
                ImageView Left_people = (ImageView) findViewById(R.id.left_people);

                AlphaAnimation alphaAnimation2 = new AlphaAnimation(1, 0);
                alphaAnimation2.setDuration(500);
                animationSet2.addAnimation(alphaAnimation2);

                Left_people.startAnimation(animationSet2);
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
        if(current == 0) {
            Toast.makeText(getApplicationContext(), getString(R.string.finish), Toast.LENGTH_SHORT).show();
            bnp.setProgress(100);
        }
    }

}
