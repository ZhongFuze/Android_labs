package com.zhong.midterm;

/**
 * Created by Zhong on 2017/11/19.
 */
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

public class HorizontalActivity extends AppCompatActivity {

    String[] titles = {
            "Android",
            "Beta",
            "Cupcake",
            "Donut",
            "Eclair",
            "Froyo",
            "Gingerbread",
            "Honeycomb",
            "Ice Cream Sandwich",
            "Jelly Bean",
            "KitKat",
            "Lollipop",
            "Marshmallow",
            "Nougat",
            "Android O",
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizontal);

        HorizontalAdapter firstAdapter = new HorizontalAdapter(titles);
        MultiSnapRecyclerView firstRecyclerView = (MultiSnapRecyclerView)findViewById(R.id.first_recycler_view);
        LinearLayoutManager firstManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        firstRecyclerView.setLayoutManager(firstManager);
        firstRecyclerView.setAdapter(firstAdapter);

        HorizontalAdapter secondAdapter = new HorizontalAdapter(titles);
        MultiSnapRecyclerView secondRecyclerView =(MultiSnapRecyclerView) findViewById(R.id.second_recycler_view);
        LinearLayoutManager secondManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        secondRecyclerView.setLayoutManager(secondManager);
        secondRecyclerView.setAdapter(secondAdapter);

        HorizontalAdapter thirdAdapter = new HorizontalAdapter(titles);
        MultiSnapRecyclerView thirdRecyclerView = (MultiSnapRecyclerView)findViewById(R.id.third_recycler_view);
        LinearLayoutManager thirdManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        thirdRecyclerView.setLayoutManager(thirdManager);
        thirdRecyclerView.setAdapter(thirdAdapter);
    }
}
