package com.zhong.midterm;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FancyActivity extends AppCompatActivity {

    private TextView tv_index_center;
    final List<Hero> WeiHero = DataSupport.where("native_=?","魏").find(Hero.class);
    final List <Hero> ShuHero = DataSupport.where("native_=?","蜀").find(Hero.class);
    final List <Hero> WuHero = DataSupport.where("native_=?","吴").find(Hero.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_fancy);
        tv_index_center = (TextView) findViewById(R.id.tv_index_center);



        final ArrayList<GoodMan> persons = new ArrayList<GoodMan>();

        // 填充数据, 并排序
        fillAndSortData(persons,WeiHero,ShuHero,WuHero);

        MyAdapter PingyinAdapter = new MyAdapter(persons,FancyActivity.this);
        final ListView listView_content = (ListView) findViewById(R.id.lv_content);
        listView_content.setAdapter(PingyinAdapter);

        listView_content.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final GoodMan people = persons.get(position);
                Toast.makeText(FancyActivity.this,people.getName(),Toast.LENGTH_SHORT).show();
            }
        });







        FancyIndexer mFancyIndexer = (FancyIndexer) findViewById(R.id.bar);
        mFancyIndexer.setOnTouchLetterChangedListener(new FancyIndexer.OnTouchLetterChangedListener() {

            @Override
            public void onTouchLetterChanged(String letter) {
                System.out.println("letter: " + letter);
//				Util.showToast(getApplicationContext(), letter);

//				showLetter(letter);

                // 从集合中查找第一个拼音首字母为letter的索引, 进行跳转
                for (int i = 0; i < persons.size(); i++) {
                    GoodMan goodMan = persons.get(i);
                    String s = goodMan.getPinyin().charAt(0) + "";
                    if(TextUtils.equals(s, letter)){
                        // 匹配成功, 中断循环, 跳转到i位置
                        listView_content.setSelection(i);
                        break;
                    }
                }
            }
        });

    }

    private Handler mHandler = new Handler();

    /**
     * 显示字母提示
     * @param letter
     */
    protected void showLetter(String letter) {
        tv_index_center.setVisibility(View.VISIBLE);
        tv_index_center.setText(letter);

        // 取消掉刚刚所有的演示操作
        mHandler.removeCallbacksAndMessages(null);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // 隐藏
                tv_index_center.setVisibility(View.GONE);
            }
        }, 1000);

    }

    /**
     * 填充,排序
     * @param persons
     */
    private void fillAndSortData(ArrayList<GoodMan> persons,List<Hero> WeiHero,List<Hero> ShuHero,List<Hero> WuHero) {
//        String[] datas = null;
//        boolean china = getResources().getConfiguration().locale.getCountry().equals("CN");
//        //datas = china ? Cheeses.NAMES : Cheeses.sCheeseStrings;
//        datas = Cheeses.sCheeseStrings;
//        for (int i = 0; i < datas.length; i++) {
//            persons.add(new GoodMan(datas[i]));
//        }

        for (int i=0;i<WeiHero.size();i++)
        {
            persons.add(new GoodMan(WeiHero.get(i).getName()));
        }

        for (int i=0;i<ShuHero.size();i++)
        {
            persons.add(new GoodMan(ShuHero.get(i).getName()));
        }

        for (int i=0;i<WuHero.size();i++)
        {
            persons.add(new GoodMan(WuHero.get(i).getName()));
        }
        // 排序
        Collections.sort(persons);
    }
}
