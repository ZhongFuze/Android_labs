package com.mycompany.fruit;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import android.os.Handler;

public class DetailActivity extends AppCompatActivity {
    FloatingActionButton fab_car;
    private TextView sum_money;
    private static final int msgKey1 = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        sum_money = (TextView) findViewById(R.id.sum_money);
        new TimeThread().start();


        RecyclerView car_recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        final FruitRecycleAdapter car_adapter =  new FruitRecycleAdapter(MainActivity.instance_main.car_fruitList);
        car_recyclerView.setLayoutManager(new LinearLayoutManager(this));

        MainActivity.instance_main.car_fruitList=car_adapter.getmFruitList();
        String sum=String.valueOf(car_adapter.getSum());
        String number = String.valueOf(car_adapter.getItemCount());
        String zongji = "总计"+number+"件："+"共"+sum+"元";
        MainActivity.instance_main.all = zongji;


        RecyItemTouchHelperCallback itemTouchHelperCallback2 = new RecyItemTouchHelperCallback(car_adapter);
        ItemTouchHelper itemTouchHelper2 = new ItemTouchHelper(itemTouchHelperCallback2);
        itemTouchHelper2.attachToRecyclerView(car_recyclerView);


        final Button button_pay = (Button) findViewById(R.id.pay_money);
        button_pay.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                if (car_adapter.getSum() == 0)
                {
                    Toast.makeText(DetailActivity.this,"购物车空空如也...",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Snackbar.make(fab_car, "正在跳转支付链接......", Snackbar.LENGTH_SHORT)
                            .setAction("确认", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                }
                            })
                            .setActionTextColor(getResources().getColor(R.color.colorPrimary))
                            .setDuration(5000)
                            .show();
                }

            }
        });
        car_recyclerView.addOnItemTouchListener(new OnRecyclerItemClickListener(car_recyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder viewHolder) {
                FruitRecycleAdapter.ViewHolder viewHolder2 = (FruitRecycleAdapter.ViewHolder) viewHolder;
                int position = viewHolder2.getAdapterPosition();
                final Fruit fruit = car_adapter.getmFruitList().get(position);
                fruit.setPos(position);
                //Toast.makeText(DetailActivity.this,"Click "+fruit.getName(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(DetailActivity.this, CarActivity.class);
                Bundle mBundle = new Bundle();
                mBundle.putSerializable("car_fruit",fruit);
                intent.putExtras(mBundle);
                startActivity(intent);
            }

            @Override
            public void onLongClick(RecyclerView.ViewHolder viewHolder) {
                FruitRecycleAdapter.ViewHolder viewHolder1 = (FruitRecycleAdapter.ViewHolder) viewHolder;
                int position = viewHolder1.getAdapterPosition();
                Fruit fruit = MainActivity.instance_main.car_fruitList.get(position);
                //Toast.makeText(DetailActivity.this,"Long Click "+fruit.getName(),Toast.LENGTH_SHORT).show();
            }

        });
        car_recyclerView.setAdapter(car_adapter);
        MainActivity.instance_main.car_fruitList = car_adapter.getmFruitList();


        fab_car = (FloatingActionButton) findViewById(R.id.fab_car);
        fab_car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(fab_car, "切换到商品列表", Snackbar.LENGTH_SHORT)
                        .setAction("确认", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                            }
                        })
                        .setActionTextColor(getResources().getColor(R.color.colorPrimary))
                        .setDuration(2000)
                        .show();
                Intent intent = new Intent(DetailActivity.this, GoodsActivity.class);
                startActivity(intent);
            }

        });

    }

    public class TimeThread extends  Thread{
        @Override
        public void run() {
            super.run();
            do{
                try {
                    Thread.sleep(1000);
                    Message msg = new Message();
                    msg.what = msgKey1;
                    mHandler.sendMessage(msg);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }while (true);
        }
    }
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case msgKey1:
                    long time = System.currentTimeMillis();
                    Date date = new Date(time);
                    sum_money.setText(MainActivity.instance_main.all);
                    break;
                default:
                    break;
            }
        }
    };



}
