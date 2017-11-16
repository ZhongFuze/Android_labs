package com.mycompany.fruit;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.support.v7.widget.helper.ItemTouchHelper.Callback.makeMovementFlags;

public class GoodsActivity extends AppCompatActivity {
    FloatingActionButton fab_goods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods);
        MainActivity.instance_main.b++;
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        final FruitRecycleAdapter adapter =  new FruitRecycleAdapter(MainActivity.instance_main.fruitList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        RecyItemTouchHelperCallback itemTouchHelperCallback = new RecyItemTouchHelperCallback(adapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemTouchHelperCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
        MainActivity.instance_main.fruitList = adapter.getmFruitList();
        recyclerView.addOnItemTouchListener(new OnRecyclerItemClickListener(recyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder viewHolder) {
                FruitRecycleAdapter.ViewHolder viewHolder1 = (FruitRecycleAdapter.ViewHolder) viewHolder;
                int position = viewHolder1.getAdapterPosition();
                final Fruit fruit = adapter.getmFruitList().get(position);
                fruit.setPos(position);
                //Toast.makeText(GoodsActivity.this,"Click "+fruit.getName(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(GoodsActivity.this, CarActivity.class);
                Bundle mBundle = new Bundle();
                mBundle.putSerializable("fruit",fruit);
                intent.putExtras(mBundle);
                startActivity(intent);
            }

            @Override
            public void onLongClick(RecyclerView.ViewHolder viewHolder) {
                FruitRecycleAdapter.ViewHolder viewHolder1 = (FruitRecycleAdapter.ViewHolder) viewHolder;
                int position = viewHolder1.getAdapterPosition();
                Fruit fruit = MainActivity.instance_main.fruitList.get(position);
                Toast.makeText(GoodsActivity.this,"Long Click "+fruit.getName(),Toast.LENGTH_SHORT).show();
            }

        });
        recyclerView.setAdapter(adapter);
        MainActivity.instance_main.fruitList = adapter.getmFruitList();
        fab_goods = (FloatingActionButton) findViewById(R.id.fab_shop);
        fab_goods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(fab_goods, "切换到购物车", Snackbar.LENGTH_SHORT)
                        .setAction("确认", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                            }
                        })
                        .setActionTextColor(getResources().getColor(R.color.colorPrimary))
                        .setDuration(2000)
                        .show();
                Intent intent = new Intent(GoodsActivity.this, DetailActivity.class);
                startActivity(intent);
            }
        });
    }


}
