package com.zhong.midterm;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.litepal.crud.DataSupport;

/**
 * Created by user on 2017/11/26.
 */

public class UserHeroDetail extends AppCompatActivity {

    private boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.userheroldetail);
        initial_fonts();

        final User_heros user_people = (User_heros) getIntent().getSerializableExtra("user_people");

        TextView show_name = (TextView)findViewById(R.id.show_name);
        TextView show_sex = (TextView)findViewById(R.id.show_sex);
        TextView show_birth = (TextView)findViewById(R.id.show_birth);
        TextView show_death = (TextView)findViewById(R.id.show_death);
        TextView show_native_ = (TextView)findViewById(R.id.show_native_);
        TextView attack_num = (TextView)findViewById(R.id.attack_num);
        TextView defense_num = (TextView)findViewById(R.id.defense_num);
        TextView live_num = (TextView)findViewById(R.id.live_num);
        ProgressBar show_attack = (ProgressBar)findViewById(R.id.show_attack);
        ProgressBar show_defense = (ProgressBar)findViewById(R.id.show_defense);
        ProgressBar show_live = (ProgressBar)findViewById(R.id.show_live);
        ImageView hero_piture = (ImageView)findViewById(R.id.hero_piture);

        show_name.setText(user_people.getName());
        show_sex.setText(user_people.getSex());
        show_birth.setText(user_people.getBirth());
        show_death.setText(user_people.getDeath());
        show_native_.setText(user_people.getNative_());
        attack_num.setText(String.valueOf(user_people.getAttack()));
        defense_num.setText(String.valueOf(user_people.getDefense()));
        live_num.setText(String.valueOf(user_people.getLive()));
        hero_piture.setImageResource(user_people.getBimage());
        show_attack.setProgress(user_people.getAttack());
        show_defense.setProgress(user_people.getDefense());
        show_live.setProgress(user_people.getLive());


        ImageView buttonstart = (ImageView)findViewById(R.id.buttonstart);
        ImageView buttondelete = (ImageView)findViewById(R.id.buttondelete);
        ImageView buttonreturn = (ImageView)findViewById(R.id.buttonreturn);



        buttonstart.setOnClickListener(new ImageView.OnClickListener(){
            @Override
            public void onClick(android.view.View v){
                ///////////
                AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0);
                alphaAnimation.setDuration(1000);
                v.startAnimation(alphaAnimation);

                if(flag){
                    Intent intent = new Intent(UserHeroDetail.this, MoveActivity.class);
                    Bundle mBundle = new Bundle();
                    mBundle.putSerializable("user_people",user_people);
                    intent.putExtras(mBundle);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(UserHeroDetail.this,user_people.getName()+"已被压入天牢，无法参战",Toast.LENGTH_SHORT).show();
                }

            }
        });

        buttondelete.setOnClickListener(new ImageView.OnClickListener(){
            @Override
            public void onClick(android.view.View v){
                ///////////
                AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0);
                alphaAnimation.setDuration(1000);
                v.startAnimation(alphaAnimation);
                Toast.makeText(UserHeroDetail.this,user_people.getName()+"已被压入天牢",Toast.LENGTH_SHORT).show();
                DataSupport.deleteAll(User_heros.class,"user_id=? and name=?",String.valueOf(Data.getUser_id()),user_people.getName());
                flag = false;
            }
        });

        buttonreturn.setOnClickListener(new ImageView.OnClickListener(){
            @Override
            public void onClick(android.view.View v){
                ///////////
                AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0);
                alphaAnimation.setDuration(1000);
                v.startAnimation(alphaAnimation);

                Intent intent = new Intent(UserHeroDetail.this, HorizontalActivity.class);
                startActivity(intent);
            }
        });


    }

    private void initial_fonts(){
        Typeface text = Typeface.createFromAsset(getAssets(),"fonts/lishu_.ttf");

        final TextView name = (TextView)findViewById(R.id.name);
        name.setTypeface(text);

        final TextView show_name = (TextView)findViewById(R.id.show_name);
        show_name.setTypeface(text);

        final TextView sex = (TextView)findViewById(R.id.sex);
        sex.setTypeface(text);

        final TextView show_sex = (TextView)findViewById(R.id.show_sex);
        show_sex.setTypeface(text);

        final TextView birth = (TextView)findViewById(R.id.birth);
        birth.setTypeface(text);

        final TextView show_birth = (TextView)findViewById(R.id.show_birth);
        show_birth.setTypeface(text);

        final TextView death = (TextView)findViewById(R.id.death);
        death.setTypeface(text);

        final TextView show_death = (TextView)findViewById(R.id.show_death);
        show_death.setTypeface(text);

        final TextView native_ = (TextView)findViewById(R.id.native_);
        native_.setTypeface(text);

        final TextView show_native_ = (TextView)findViewById(R.id.show_native_);
        show_native_.setTypeface(text);

        final TextView attack = (TextView)findViewById(R.id.attack);
        attack.setTypeface(text);


        final TextView attack_num = (TextView)findViewById(R.id.attack_num);
        attack_num.setTypeface(text);

        final TextView defense = (TextView)findViewById(R.id.defense);
        defense.setTypeface(text);

        final TextView defense_num = (TextView)findViewById(R.id.defense_num);
        defense_num.setTypeface(text);

        final TextView live = (TextView)findViewById(R.id.live);
        live.setTypeface(text);

        final TextView live_num = (TextView)findViewById(R.id.live_num);
        live_num.setTypeface(text);
    }

}
