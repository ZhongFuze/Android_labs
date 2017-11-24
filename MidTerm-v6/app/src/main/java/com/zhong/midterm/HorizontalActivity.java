package com.zhong.midterm;

/**
 * Created by Zhong on 2017/11/19.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.Toast;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

public class HorizontalActivity extends AppCompatActivity {

    public static List<CountryPeople> countryShu = new ArrayList<>();
    public static List<CountryPeople> countryWei = new ArrayList<>();
    public static List<CountryPeople> countryWu = new ArrayList<>();
    Button peoplebutton;
    final List <Hero> heroList = DataSupport.findAll(Hero.class);
    final List <Users> usersList = DataSupport.findAll(Users.class);
    final List <User_heros> user_herosList = DataSupport.findAll(User_heros.class);

    final List <Hero> WeiHero = DataSupport.where("native_=?","魏").find(Hero.class);
    final List <Hero> ShuHero = DataSupport.where("native_=?","蜀").find(Hero.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizontal);
        ///////////////////////////////////////

        ////////////////////////////////////////
        initCountryShu();
        final HorizontalAdapter firstAdapter = new HorizontalAdapter(countryShu);
        MultiSnapRecyclerView firstRecyclerView = (MultiSnapRecyclerView)findViewById(R.id.first_recycler_view);
        LinearLayoutManager firstManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        firstRecyclerView.setLayoutManager(firstManager);
        firstRecyclerView.setAdapter(firstAdapter);

        firstRecyclerView.addOnItemTouchListener(new OnRecyclerItemClickListener(firstRecyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder viewHolder) {
                HorizontalAdapter.ViewHolder viewHolder1 = (HorizontalAdapter.ViewHolder) viewHolder;
                int postion = viewHolder1.getAdapterPosition();
                final CountryPeople people = firstAdapter.getCountryPeoples().get(postion);
                Toast.makeText(HorizontalActivity.this,"Click "+people.getName(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(RecyclerView.ViewHolder viewHolder) {
                HorizontalAdapter.ViewHolder viewHolder1 = (HorizontalAdapter.ViewHolder) viewHolder;
                int postion = viewHolder1.getAdapterPosition();
                final CountryPeople people = firstAdapter.getCountryPeoples().get(postion);
                Toast.makeText(HorizontalActivity.this,"Long Click "+people.getName(),Toast.LENGTH_SHORT).show();
            }
        });

        initCountryWei();
        HorizontalAdapter secondAdapter = new HorizontalAdapter(countryWei);
        MultiSnapRecyclerView secondRecyclerView =(MultiSnapRecyclerView) findViewById(R.id.second_recycler_view);
        LinearLayoutManager secondManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        secondRecyclerView.setLayoutManager(secondManager);
        secondRecyclerView.setAdapter(secondAdapter);

        initCountryWu();
        HorizontalAdapter thirdAdapter = new HorizontalAdapter(countryWu);
        MultiSnapRecyclerView thirdRecyclerView = (MultiSnapRecyclerView)findViewById(R.id.third_recycler_view);
        LinearLayoutManager thirdManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        thirdRecyclerView.setLayoutManager(thirdManager);
        thirdRecyclerView.setAdapter(thirdAdapter);
    }

    //int imageId, String name, String gender, String BornDate, String native_place, String dependence
    public void initCountryShu(){

        CountryPeople Liubei = new CountryPeople(R.drawable.liubei,"刘备","男","不详","籍贯","蜀国");
        countryShu.add(Liubei);

        CountryPeople Guanyu = new CountryPeople(R.drawable.guanyu,"关羽","男","不详","籍贯","蜀国");
        countryShu.add(Guanyu);

        CountryPeople Pangtong = new CountryPeople(R.drawable.pangtong,"庞统","男","不详","籍贯","蜀国");
        countryShu.add(Pangtong);

        CountryPeople Sunshangxaing = new CountryPeople(R.drawable.sunshangxiang,"孙尚香","女","不详","籍贯","蜀国");
        countryShu.add(Sunshangxaing);

        CountryPeople Zhangfei = new CountryPeople(R.drawable.zhangfei,"张飞","男","不详","籍贯","蜀国");
        countryShu.add(Zhangfei);

        CountryPeople Zhugeliang = new CountryPeople(R.drawable.zhugeliang,"诸葛亮","男","不详","籍贯","蜀国");
        countryShu.add(Zhugeliang);

        CountryPeople Zhaoyun = new CountryPeople(R.drawable.zhaoyun,"赵云","男","不详","籍贯","蜀国");
        countryShu.add(Zhaoyun);

    }
    public void initCountryWei(){

        CountryPeople Caocao = new CountryPeople(R.drawable.caocao,"曹操","男","不详","籍贯","魏国");
        countryWei.add(Caocao);

        CountryPeople Caopi = new CountryPeople(R.drawable.caopi,"曹丕","男","不详","籍贯","魏国");
        countryWei.add(Caopi);

        CountryPeople Xunyu = new CountryPeople(R.drawable.xunyu,"荀彧","男","不详","籍贯","魏国");
        countryWei.add(Xunyu);

        CountryPeople Guojia = new CountryPeople(R.drawable.guojia,"郭嘉","女","不详","籍贯","魏国");
        countryWei.add(Guojia);

        CountryPeople Caoren = new CountryPeople(R.drawable.caoren,"曹仁","男","不详","籍贯","魏国");
        countryWei.add(Caoren);

        CountryPeople Xuchu = new CountryPeople(R.drawable.xuchu,"许褚","男","不详","籍贯","魏国");
        countryWei.add(Xuchu);

        CountryPeople Zhangliao = new CountryPeople(R.drawable.zhangliao,"张辽","男","不详","籍贯","魏国");
        countryWei.add(Zhangliao);

    }

    public void initCountryWu(){

        CountryPeople Sunquan = new CountryPeople(R.drawable.sunquan,"孙权","男","不详","籍贯","魏国");
        countryWu.add(Sunquan);

        CountryPeople Xiaoqiao = new CountryPeople(R.drawable.xiaoqiao,"小乔","男","不详","籍贯","魏国");
        countryWu.add(Xiaoqiao);

        CountryPeople Zhouyu = new CountryPeople(R.drawable.zhouyu,"周瑜","男","不详","籍贯","魏国");
        countryWu.add(Zhouyu);

        CountryPeople Sunjian = new CountryPeople(R.drawable.sunjian,"孙坚","女","不详","籍贯","魏国");
        countryWu.add(Sunjian);

        CountryPeople Huanggai = new CountryPeople(R.drawable.huanggai,"黄盖","男","不详","籍贯","魏国");
        countryWu.add(Huanggai);

        CountryPeople Lusu = new CountryPeople(R.drawable.lusu,"鲁肃","男","不详","籍贯","魏国");
        countryWu.add(Lusu);

        CountryPeople Sunce = new CountryPeople(R.drawable.sunce,"孙策","男","不详","籍贯","魏国");
        countryWu.add(Sunce);

    }

}
