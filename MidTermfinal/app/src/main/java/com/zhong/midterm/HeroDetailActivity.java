package com.zhong.midterm;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.litepal.crud.DataSupport;

import java.util.List;

import static com.zhong.midterm.R.styleable.Toolbar;
import static com.zhong.midterm.R.styleable.View;

/**
 * Created by user on 2017/11/26.
 */

public class HeroDetailActivity extends AppCompatActivity {

    final List <Hero> hero = DataSupport.findAll(Hero.class);
    final List<User_heros> user_heros = DataSupport.where("user_id=?",String.valueOf(Data.getUser_id()) ).find(User_heros.class);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.herodetailactivity);

        initial_fonts();
        Log.d("Debug","hero_user_id: "+Data.getUser_id());

        ImageView buttonadd = (ImageView) findViewById(R.id.buttonadd);
        ImageView buttonreturn = (ImageView) findViewById(R.id.buttonreturn);
        final GoodMan hero_name = (GoodMan) getIntent().getSerializableExtra("myhero");
        final List<Hero> heroList = DataSupport.where("name=?",hero_name.getName()).find(Hero.class);

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

        show_name.setText(heroList.get(0).getName());
        show_sex.setText(heroList.get(0).getSex());
        show_birth.setText(heroList.get(0).getBirth());
        show_death.setText(heroList.get(0).getDeath());
        show_native_.setText(heroList.get(0).getNative_());
        attack_num.setText(String.valueOf(heroList.get(0).getAttack()));
        defense_num.setText(String.valueOf(heroList.get(0).getDefense()));
        live_num.setText(String.valueOf(heroList.get(0).getLive()));
//        Log.d("Debug","Bimage: "+heroList.get(0).getBimage());
        hero_piture.setImageResource(heroList.get(0).getBimage());
        show_attack.setProgress(heroList.get(0).getAttack());
        show_defense.setProgress(heroList.get(0).getDefense());
        show_live.setProgress(heroList.get(0).getLive());




        buttonadd.setOnClickListener(new ImageView.OnClickListener(){
            @Override
            public void onClick(android.view.View v){
                ///////
                AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0);
                alphaAnimation.setDuration(200);
                v.startAnimation(alphaAnimation);

                boolean flag = true;
                String HN = heroList.get(0).getName();
                for(int i=0;i<user_heros.size();++i){
                    if(user_heros.get(i).getName().equals(HN)){
                        flag = false;
                        break;
                    }
                }
                if(flag){
                    User_heros user_heros = new User_heros();
                    user_heros.setAll(0,Data.getUser_id(),heroList.get(0));
                    user_heros.save();
                    Log.d("Debug","user_id: "+Data.getUser_id());
                    Toast.makeText(HeroDetailActivity.this,"英雄"+heroList.get(0).getName()+"被纳入麾下",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(HeroDetailActivity.this,"英雄"+heroList.get(0).getName()+"已是阁下的得力干将",Toast.LENGTH_SHORT).show();
                }

            }
        });

        buttonreturn.setOnClickListener(new ImageView.OnClickListener(){
            @Override
            public void onClick(android.view.View v){
                ///////////
                AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0);
                alphaAnimation.setDuration(1000);
                v.startAnimation(alphaAnimation);

                Intent intent = new Intent(HeroDetailActivity.this, HorizontalActivity.class);
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
