package com.zhong.midterm;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import org.litepal.crud.DataSupport;

import java.util.List;

public class InitialActivity extends AppCompatActivity {

    static int user_id = 0;
    static boolean flag = true;

    final List<Hero> heroList = DataSupport.findAll(Hero.class);
    final List <Users> usersList = DataSupport.findAll(Users.class);
    final List <User_heros> user_herosList = DataSupport.findAll(User_heros.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);

        if(first_enter()){
            initial_hero();
            initial_users();
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(InitialActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        }, 3000);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    protected void onPause() {
        super.onPause();
    }

    public void initial_hero(){
        flag = false;
        Hero hero1 = new Hero();
        hero1.setAll(1,R.drawable.guanyu,"关羽","男","876.1.2","800.1.3","蜀",80,80,100,R.drawable.bguanyu);
        hero1.save();

        Hero hero2 = new Hero();
        hero2.setAll(2,R.drawable.liubei,"刘备","男","876.2.2","800.2.3","蜀",80,70,100,R.drawable.bliubei);
        hero2.save();

        Hero hero3 = new Hero();
        hero3.setAll(3,R.drawable.pangtong,"庞统","男","179","214","蜀",80,60,70,R.drawable.bpangtong);
        hero3.save();

        Hero hero4 = new Hero();
        hero4.setAll(4,R.drawable.sunshangxiang,"孙尚香","女","?","?","蜀",80,100,100,R.drawable.bsunshangxiang);
        hero4.save();

        Hero hero5 = new Hero();
        hero5.setAll(5,R.drawable.zhangfei,"张飞","男","?","221","蜀",80,60,100,R.drawable.bzhangfei);
        hero5.save();

        Hero hero6 = new Hero();
        hero6.setAll(6,R.drawable.zhaoyun,"赵云","男","?","229","蜀",80,80,90,R.drawable.bzhaoyun);
        hero6.save();

        Hero hero7 = new Hero();
        hero7.setAll(7,R.drawable.zhugeliang,"诸葛亮","男","181","234","蜀",80,100,100,R.drawable.bzhugeliang);
        hero7.save();

        Hero hero8 = new Hero();
        hero8.setAll(8,R.drawable.caocao,"曹操","男","155","220","魏",80,100,100,R.drawable.bcaocao);
        hero8.save();

        Hero hero9 = new Hero();
        hero9.setAll(9,R.drawable.caopi,"曹丕","男","187","226","魏",80,80,90,R.drawable.bcaopi);
        hero9.save();

        Hero hero10 = new Hero();
        hero10.setAll(10,R.drawable.caoren,"曹仁","男","168","223","魏",80,90,100,R.drawable.bcaoren);
        hero10.save();

        Hero hero11 = new Hero();
        hero11.setAll(11,R.drawable.guojia,"郭嘉","男","170","207","魏",80,100,80,R.drawable.bguojia);
        hero11.save();

        Hero hero12 = new Hero();
        hero12.setAll(12,R.drawable.xuchu,"许褚","男","?","?","魏",80,60,100,R.drawable.bxunzhu);
        hero12.save();

        Hero hero13 = new Hero();
        hero13.setAll(13,R.drawable.xunyu,"荀彧","男","163","212","魏",80,60,90,R.drawable.bxunyu);
        hero13.save();

        Hero hero14 = new Hero();
        hero14.setAll(14,R.drawable.zhangliao,"张辽","男","169","222","魏",80,70,80,R.drawable.bzhangliao);
        hero14.save();

        Hero hero15 = new Hero();
        hero15.setAll(15,R.drawable.huanggai,"黄盖","男","?","?","吴",80,100,90,R.drawable.bhuanggai);
        hero15.save();

        Hero hero16 = new Hero();
        hero16.setAll(16,R.drawable.lusu,"鲁肃","男","172","217","吴",80,90,100,R.drawable.blusu);
        hero16.save();

        Hero hero17 = new Hero();
        hero17.setAll(17,R.drawable.sunce,"孙策","男","175","200","吴",80,70,80,R.drawable.bsunce);
        hero17.save();

        Hero hero18 = new Hero();
        hero18.setAll(18,R.drawable.sunjian,"孙坚","男","155","191","吴",80,80,80,R.drawable.bsunjian);
        hero18.save();

        Hero hero19 = new Hero();
        hero19.setAll(19,R.drawable.sunquan,"孙权","男","213","232","吴",80,90,100,R.drawable.bsunquan);
        hero19.save();

        Hero hero20 = new Hero();
        hero20.setAll(20,R.drawable.xiaoqiao,"小乔","女","?","?","吴",80,80,100,R.drawable.bxiaoqiao);
        hero20.save();

        Hero hero21 = new Hero();
        hero21.setAll(21,R.drawable.zhouyu,"周瑜","男","175","210","吴",80,80,100,R.drawable.bzhouyu);
        hero21.save();

        User_heros user_heros1 = new User_heros();
        user_heros1.setAll(1,1,hero1);
        user_heros1.save();

        User_heros user_heros2 = new User_heros();
        user_heros2.setAll(1,1,hero2);
        user_heros2.save();

        User_heros user_heros3 = new User_heros();
        user_heros3.setAll(1,1,hero3);
        user_heros3.save();

        User_heros user_heros4 = new User_heros();
        user_heros4.setAll(1,1,hero8);
        user_heros4.save();

        User_heros user_heros5 = new User_heros();
        user_heros5.setAll(1,1,hero9);
        user_heros5.save();

        User_heros user_heros6 = new User_heros();
        user_heros6.setAll(1,1,hero10);
        user_heros6.save();

        User_heros user_heros7 = new User_heros();
        user_heros7.setAll(1,1,hero15);
        user_heros7.save();

        User_heros user_heros8 = new User_heros();
        user_heros8.setAll(1,1,hero16);
        user_heros8.save();

        User_heros user_heros9 = new User_heros();
        user_heros9.setAll(1,1,hero17);
        user_heros9.save();
    }

    public void initial_users(){
        Users users3 = new Users();
        users3.setAll(user_id++,"666","666",R.drawable.user_image,10);
        users3.save();
    }

    private boolean first_enter() {
        SharedPreferences shared=getSharedPreferences("is", MODE_PRIVATE);
        boolean isfer=shared.getBoolean("isfer", true);
        SharedPreferences.Editor editor=shared.edit();
        if(isfer){
            editor.putBoolean("isfer", false);
            editor.commit();
            return true;
        }
        else {
            return false;
        }
    }


}
