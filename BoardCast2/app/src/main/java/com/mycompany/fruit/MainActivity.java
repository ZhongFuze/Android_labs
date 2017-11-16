package com.mycompany.fruit;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static android.R.attr.action;

public class MainActivity extends AppCompatActivity {
    public static MainActivity instance_main = null;
    public static List<Fruit> fruitList = new ArrayList<>();
    public static List<Fruit> car_fruitList = new ArrayList<>();
    public static String all = "总计0件商品：共0元";
    public static int a = 0;
    public static int b = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instance_main = MainActivity.this;
        initFruit();

        Random random = new Random();
        int number=random.nextInt(fruitList.size());
        Fruit random_fruit = fruitList.get(number);
        random_fruit.setPos(number);

        Intent intent = new Intent(MainActivity.this, MyBroadCastReceiver.class);
        Bundle mBundle = new Bundle();
        mBundle.putSerializable("random_fruit",random_fruit);
        intent.putExtras(mBundle);
        sendBroadcast(intent);

        final Button button = (Button) findViewById(R.id.start_button);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(MainActivity.this, GoodsActivity.class);
                startActivity(intent);
            }
        });


    }

    public void initFruit(){
        for(int i=0;i<2;i++)
        {
            Fruit EnchatedForest = new Fruit("Enchated Forest",5.00,R.drawable.enchatedforest,"作者","Johanna Basford",0);
            fruitList.add(EnchatedForest);

            Fruit ArlaMilk = new Fruit("Arla Milk",59.00,R.drawable.arla,"产地","德国",0);
            fruitList.add(ArlaMilk);

            Fruit DevondaleMilk = new Fruit("Devondale Milk",79.00,R.drawable.devondale,"产地","澳大利亚",0);
            fruitList.add(DevondaleMilk);

            Fruit KindleOasis = new Fruit("Kindle Oasis",2399.00,R.drawable.kindle,"版本","8GB",0);
            fruitList.add(KindleOasis);

            Fruit waitrose = new Fruit("waitrose 早餐麦片",179.00,R.drawable.waitrose,"重量","2Kg",0);
            fruitList.add(waitrose);

            Fruit Mcvities = new Fruit("Mcvitie's 饼干",14.90,R.drawable.mcvitie,"产地","英国",0);
            fruitList.add(Mcvities);

            Fruit FerreroRocher = new Fruit("Ferrero Rocher",132.59,R.drawable.ferrero,"重量","300g",0);
            fruitList.add(FerreroRocher);

            Fruit Maltesers = new Fruit("Maltesers",141.43,R.drawable.maltesers,"重量","118g",0);
            fruitList.add(Maltesers);

            Fruit Lindt = new Fruit("Lindt",139.43,R.drawable.lindt,"重量","249g",0);
            fruitList.add(Lindt);

            Fruit Borggreve = new Fruit("Borggreve",28.90,R.drawable.borggreve,"重量","640g",0);
            fruitList.add(Borggreve);
        }
    }

}
