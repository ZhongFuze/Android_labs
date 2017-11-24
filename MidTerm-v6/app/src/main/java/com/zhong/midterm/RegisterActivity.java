package com.zhong.midterm;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.transition.Explode;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.litepal.crud.DataSupport;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.cv_add)
    CardView cvAdd;

    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.bt_go)
    Button btGo;
    @BindView(R.id.et_repeatpassword)
    EditText etRepassword;

    final public List<Users> usersList = DataSupport.findAll(Users.class);
    final List <Hero> heroList = DataSupport.findAll(Hero.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        View v = findViewById(R.id.cv_add);
        v.getBackground().setAlpha(200);//0~255
        ButterKnife.bind(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ShowEnterAnimation();
        }
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animateRevealClose();
            }
        });
    }

    @OnClick({R.id.bt_go, R.id.fab})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab:
                getWindow().setExitTransition(null);
                getWindow().setEnterTransition(null);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options =
                            ActivityOptions.makeSceneTransitionAnimation(this, fab, fab.getTransitionName());
                    startActivity(new Intent(this, RegisterActivity.class), options.toBundle());
                } else {
                    startActivity(new Intent(this, RegisterActivity.class));
                }
                break;
            case R.id.bt_go:

                String yourname = etUsername.getText().toString();
                String yourpassword = etPassword.getText().toString();
                String yourrepassword = etRepassword.getText().toString();
//                Toast.makeText(RegisterActivity.this,"user: "+yourname+" "+"password: "+yourpassword,Toast.LENGTH_SHORT).show();

                boolean flag = true;
                for(int i=0;i<usersList.size();++i){
                    if(usersList.get(i).getName().equals(yourname)){ flag = false; }
                }
                if(flag == false){
                    Toast.makeText(RegisterActivity.this,"该用户名已存在",Toast.LENGTH_SHORT).show();
                }
                else{
                    if(!yourpassword.equals(yourrepassword)){
                        Toast.makeText(RegisterActivity.this,"确认密码不一致",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Users newusers = new Users();
                        newusers.setAll(0,yourname,yourpassword,R.drawable.user_image,10);
                        newusers.save();
                        Log.d("Debug","id: "+newusers.getId());

                        User_heros user_heros1 = new User_heros();
                        user_heros1.setAll(1,newusers.getId(),heroList.get(0));
                        user_heros1.save();

                        User_heros user_heros2 = new User_heros();
                        user_heros2.setAll(1,newusers.getId(),heroList.get(1));
                        user_heros2.save();

                        User_heros user_heros3 = new User_heros();
                        user_heros3.setAll(1,newusers.getId(),heroList.get(2));
                        user_heros3.save();

                        User_heros user_heros4 = new User_heros();
                        user_heros4.setAll(1,newusers.getId(),heroList.get(7));
                        user_heros4.save();

                        User_heros user_heros5 = new User_heros();
                        user_heros5.setAll(1,newusers.getId(),heroList.get(8));
                        user_heros5.save();

                        User_heros user_heros6 = new User_heros();
                        user_heros6.setAll(1,newusers.getId(),heroList.get(9));
                        user_heros6.save();

                        User_heros user_heros7 = new User_heros();
                        user_heros7.setAll(1,newusers.getId(),heroList.get(14));
                        user_heros7.save();

                        User_heros user_heros8 = new User_heros();
                        user_heros8.setAll(1,newusers.getId(),heroList.get(15));
                        user_heros8.save();

                        User_heros user_heros9 = new User_heros();
                        user_heros9.setAll(1,newusers.getId(),heroList.get(16));
                        user_heros9.save();
                    }
                }


                Explode explode = new Explode();
                explode.setDuration(500);
                getWindow().setExitTransition(explode);
                getWindow().setEnterTransition(explode);
                ActivityOptionsCompat oc2 = ActivityOptionsCompat.makeSceneTransitionAnimation(this);
                Intent i2 = new Intent(this,MainActivity.class);
                startActivity(i2, oc2.toBundle());
                break;
        }
    }

    private void ShowEnterAnimation() {
        Transition transition = TransitionInflater.from(this).inflateTransition(R.transition.fabtransition);
        getWindow().setSharedElementEnterTransition(transition);

        transition.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {
                cvAdd.setVisibility(View.GONE);
            }

            @Override
            public void onTransitionEnd(Transition transition) {
                transition.removeListener(this);
                animateRevealShow();
            }

            @Override
            public void onTransitionCancel(Transition transition) {

            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }


        });
    }

    public void animateRevealShow() {
        Animator mAnimator = ViewAnimationUtils.createCircularReveal(cvAdd, cvAdd.getWidth()/2,0, fab.getWidth() / 2, cvAdd.getHeight());
        mAnimator.setDuration(500);
        mAnimator.setInterpolator(new AccelerateInterpolator());
        mAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
            }

            @Override
            public void onAnimationStart(Animator animation) {
                cvAdd.setVisibility(View.VISIBLE);
                super.onAnimationStart(animation);
            }
        });
        mAnimator.start();
    }

    public void animateRevealClose() {
        Animator mAnimator = ViewAnimationUtils.createCircularReveal(cvAdd,cvAdd.getWidth()/2,0, cvAdd.getHeight(), fab.getWidth() / 2);
        mAnimator.setDuration(500);
        mAnimator.setInterpolator(new AccelerateInterpolator());
        mAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                cvAdd.setVisibility(View.INVISIBLE);
                super.onAnimationEnd(animation);
                fab.setImageResource(R.drawable.plus);
                RegisterActivity.super.onBackPressed();
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
            }
        });
        mAnimator.start();
    }
    @Override
    public void onBackPressed() {
        animateRevealClose();
    }
}
