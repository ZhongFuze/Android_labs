package com.mycompany.fruit;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CarActivity extends AppCompatActivity {
    FloatingActionButton fab_car;
    FloatingActionButton fab_goods;
    private IntentFilter dynamic_filter;
    private DynamicReceiver dynamicReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);

        dynamic_filter = new IntentFilter();
        dynamic_filter.addAction("com.mycompany.fruit.DynamicReceiver");
        dynamicReceiver = new DynamicReceiver();
        CarActivity.this.registerReceiver(dynamicReceiver, dynamic_filter);


        final Fruit fruit = (Fruit) getIntent().getSerializableExtra("fruit");
        final Fruit car_fruit = (Fruit) getIntent().getSerializableExtra("car_fruit");
        final Fruit the_random_fruit = (Fruit) getIntent().getSerializableExtra("the_random_fruit");

        final ImageView click_image = (ImageView) findViewById(R.id.click_fruit_image);
        final TextView click_name = (TextView) findViewById(R.id.click_fruit_name);
        final TextView click_mes = (TextView) findViewById(R.id.click_fruit_mes);
        final ImageButton collect_star = (ImageButton) findViewById(R.id.click_star);
        final ImageButton add_to_car = (ImageButton) findViewById(R.id.add_to_car);

        if(MainActivity.instance_main.a>0)
        {
            MainActivity.instance_main.initFruit();
            if (the_random_fruit != null) {
                click_image.setImageResource(the_random_fruit.getImageId());
                click_name.setText(the_random_fruit.getName());
                String jiage = String.valueOf(the_random_fruit.getPrice());
                String zhonglei = the_random_fruit.getGoodsType();
                String ext = the_random_fruit.getExtMessage();
                String in = "￥" + jiage + "\n" + zhonglei + " " + ext;
                click_mes.setText(in);

                if (the_random_fruit.getCollect() == 1) {
                    collect_star.setImageResource(R.drawable.full_star);
                } else {
                    collect_star.setImageResource(R.drawable.empty_star);
                }
                collect_star.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int ju = the_random_fruit.getCollect();
                        if (ju == 0) {
                            the_random_fruit.setCollect(1);
                            MainActivity.instance_main.fruitList.set(the_random_fruit.getPos(), the_random_fruit);
                            collect_star.setImageResource(R.drawable.full_star);
                            Toast.makeText(CarActivity.this, "已收藏该商品", Toast.LENGTH_SHORT).show();
                        } else {
                            fruit.setCollect(0);
                            MainActivity.instance_main.fruitList.set(the_random_fruit.getPos(), the_random_fruit);
                            collect_star.setImageResource(R.drawable.empty_star);
                            Toast.makeText(CarActivity.this, "已取消收藏", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                add_to_car.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        MainActivity.instance_main.car_fruitList.add(the_random_fruit);
                        Toast.makeText(CarActivity.this, "已添加 " + the_random_fruit.getName() + " 到购物车", Toast.LENGTH_SHORT).show();

                        Bundle mBundle = new Bundle();
                        mBundle.putSerializable("the_car_random_fruit", the_random_fruit);
                        Intent intent = new Intent();
                        intent.setAction("com.mycompany.fruit.DynamicReceiver");
                        intent.putExtras(mBundle);
                        CarActivity.this.sendBroadcast(intent);
                    }
                });

                FruitAdapter adapter = new FruitAdapter(CarActivity.this, R.layout.fruit_item, MainActivity.instance_main.fruitList);
                ListView listView = (ListView) findViewById(R.id.list_view);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        final Fruit mfruit = MainActivity.instance_main.fruitList.get(position);
                        mfruit.setPos(position);
                        //Toast.makeText(CarActivity.this,mfruit.getName(),Toast.LENGTH_SHORT).show();

                        click_image.setImageResource(mfruit.getImageId());
                        click_name.setText(mfruit.getName());

                        String jiage = String.valueOf(mfruit.getPrice());
                        String zhonglei = mfruit.getGoodsType();
                        String ext = mfruit.getExtMessage();
                        String in = "￥" + jiage + "\n" + zhonglei + " " + ext;
                        click_mes.setText(in);

                        if (mfruit.getCollect() == 1) {
                            collect_star.setImageResource(R.drawable.full_star);
                        } else {
                            collect_star.setImageResource(R.drawable.empty_star);
                        }

                        collect_star.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (mfruit.getCollect() == 0) {
                                    mfruit.setCollect(1);
                                    MainActivity.instance_main.fruitList.set(mfruit.getPos(), mfruit);
                                    collect_star.setImageResource(R.drawable.full_star);
                                    Toast.makeText(CarActivity.this, "已收藏该商品", Toast.LENGTH_SHORT).show();
                                } else {
                                    mfruit.setCollect(0);
                                    MainActivity.instance_main.fruitList.set(mfruit.getPos(), mfruit);
                                    collect_star.setImageResource(R.drawable.empty_star);
                                    Toast.makeText(CarActivity.this, "已取消收藏", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                        add_to_car.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                MainActivity.instance_main.car_fruitList.add(mfruit);
                                Toast.makeText(CarActivity.this, "已添加 " + mfruit.getName() + " 到购物车", Toast.LENGTH_SHORT).show();

//                                dynamic_filter = new IntentFilter();
//                                dynamic_filter.addAction("com.mycompany.fruit.DynamicReceiver");
//                                dynamicReceiver = new DynamicReceiver();
//                                CarActivity.this.registerReceiver(dynamicReceiver, dynamic_filter);

                                Bundle mBundle = new Bundle();
                                mBundle.putSerializable("the_car_random_fruit", mfruit);
                                Intent intent = new Intent();
                                intent.setAction("com.mycompany.fruit.DynamicReceiver");
                                intent.putExtras(mBundle);
                                CarActivity.this.sendBroadcast(intent);
                            }
                        });
                    }

                });
            }
            MainActivity.instance_main.a=0;


        }
        //MainActivity.instance_main.a=0;

        if (car_fruit!=null)
        {
            click_image.setImageResource(car_fruit.getImageId());
            click_name.setText(car_fruit.getName());
            String jiage = String.valueOf(car_fruit.getPrice());
            String zhonglei = car_fruit.getGoodsType();
            String ext = car_fruit.getExtMessage();
            String in = "￥" + jiage + "\n" + zhonglei + " " + ext;
            click_mes.setText(in);

            if (car_fruit.getCollect() == 1) {
                collect_star.setImageResource(R.drawable.full_star);
            } else {
                collect_star.setImageResource(R.drawable.empty_star);
            }
            collect_star.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int ju = car_fruit.getCollect();
                    if (ju == 0) {
                        car_fruit.setCollect(1);
                        MainActivity.instance_main.car_fruitList.set(car_fruit.getPos(), car_fruit);
                        collect_star.setImageResource(R.drawable.full_star);
                        Toast.makeText(CarActivity.this, "已收藏该商品", Toast.LENGTH_SHORT).show();
                    } else {
                        fruit.setCollect(0);
                        MainActivity.instance_main.car_fruitList.set(car_fruit.getPos(), car_fruit);
                        collect_star.setImageResource(R.drawable.empty_star);
                        Toast.makeText(CarActivity.this, "已取消收藏", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            add_to_car.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


//                    dynamic_filter = new IntentFilter();
//                    dynamic_filter.addAction("com.mycompany.fruit.DynamicReceiver");
//                    dynamicReceiver = new DynamicReceiver();
//                    CarActivity.this.registerReceiver(dynamicReceiver,dynamic_filter);
                    MainActivity.instance_main.car_fruitList.add(car_fruit);
                    Toast.makeText(CarActivity.this, "已添加 " + car_fruit.getName() + " 到购物车", Toast.LENGTH_SHORT).show();

                    Bundle mBundle = new Bundle();
                    mBundle.putSerializable("the_car_random_fruit",car_fruit);
                    Intent intent = new Intent();
                    intent.setAction("com.mycompany.fruit.DynamicReceiver");
                    intent.putExtras(mBundle);
                    CarActivity.this.sendBroadcast(intent);
                }
            });

            FruitAdapter adapter = new FruitAdapter(CarActivity.this, R.layout.fruit_item, MainActivity.instance_main.fruitList);
            ListView listView = (ListView) findViewById(R.id.list_view);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    final Fruit mfruit = MainActivity.instance_main.fruitList.get(position);
                    mfruit.setPos(position);
                    //Toast.makeText(CarActivity.this,mfruit.getName(),Toast.LENGTH_SHORT).show();

                    click_image.setImageResource(mfruit.getImageId());
                    click_name.setText(mfruit.getName());

                    String jiage = String.valueOf(mfruit.getPrice());
                    String zhonglei = mfruit.getGoodsType();
                    String ext = mfruit.getExtMessage();
                    String in = "￥" + jiage + "\n" + zhonglei + " " + ext;
                    click_mes.setText(in);

                    if (mfruit.getCollect() == 1) {
                        collect_star.setImageResource(R.drawable.full_star);
                    } else {
                        collect_star.setImageResource(R.drawable.empty_star);
                    }

                    collect_star.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (mfruit.getCollect() == 0) {
                                mfruit.setCollect(1);
                                MainActivity.instance_main.fruitList.set(mfruit.getPos(), mfruit);
                                collect_star.setImageResource(R.drawable.full_star);
                                Toast.makeText(CarActivity.this, "已收藏该商品", Toast.LENGTH_SHORT).show();
                            } else {
                                mfruit.setCollect(0);
                                MainActivity.instance_main.fruitList.set(mfruit.getPos(), mfruit);
                                collect_star.setImageResource(R.drawable.empty_star);
                                Toast.makeText(CarActivity.this, "已取消收藏", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                    add_to_car.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            MainActivity.instance_main.car_fruitList.add(mfruit);
                            Toast.makeText(CarActivity.this, "已添加 " + mfruit.getName() + " 到购物车", Toast.LENGTH_SHORT).show();

//                            dynamic_filter = new IntentFilter();
//                            dynamic_filter.addAction("com.mycompany.fruit.DynamicReceiver");
//                            dynamicReceiver = new DynamicReceiver();
//                            CarActivity.this.registerReceiver(dynamicReceiver,dynamic_filter);

                            Bundle mBundle = new Bundle();
                            mBundle.putSerializable("the_car_random_fruit",mfruit);
                            Intent intent = new Intent();
                            intent.setAction("com.mycompany.fruit.DynamicReceiver");
                            intent.putExtras(mBundle);
                            CarActivity.this.sendBroadcast(intent);
                        }
                    });
                }

            });
        }

        if(fruit!=null) {
            click_image.setImageResource(fruit.getImageId());
            click_name.setText(fruit.getName());
            String jiage = String.valueOf(fruit.getPrice());
            String zhonglei = fruit.getGoodsType();
            String ext = fruit.getExtMessage();
            String in = "￥" + jiage + "\n" + zhonglei + " " + ext;
            click_mes.setText(in);

            if (fruit.getCollect() == 1) {
                collect_star.setImageResource(R.drawable.full_star);
            } else {
                collect_star.setImageResource(R.drawable.empty_star);
            }
            collect_star.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int ju = fruit.getCollect();
                    if (ju == 0) {
                        fruit.setCollect(1);
                        MainActivity.instance_main.fruitList.set(fruit.getPos(), fruit);
                        collect_star.setImageResource(R.drawable.full_star);
                        Toast.makeText(CarActivity.this, "已收藏该商品", Toast.LENGTH_SHORT).show();
                    } else {
                        fruit.setCollect(0);
                        MainActivity.instance_main.fruitList.set(fruit.getPos(), fruit);
                        collect_star.setImageResource(R.drawable.empty_star);
                        Toast.makeText(CarActivity.this, "已取消收藏", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            add_to_car.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MainActivity.instance_main.car_fruitList.add(fruit);
                    Toast.makeText(CarActivity.this, "已添加 " + fruit.getName() + " 到购物车", Toast.LENGTH_SHORT).show();

//                    dynamic_filter = new IntentFilter();
//                    dynamic_filter.addAction("com.mycompany.fruit.DynamicReceiver");
//                    dynamicReceiver = new DynamicReceiver();
//                    CarActivity.this.registerReceiver(dynamicReceiver,dynamic_filter);

                    Bundle mBundle = new Bundle();
                    mBundle.putSerializable("the_car_random_fruit",fruit);
                    Intent intent = new Intent();
                    intent.setAction("com.mycompany.fruit.DynamicReceiver");
                    intent.putExtras(mBundle);
                    CarActivity.this.sendBroadcast(intent);
                }
            });
            FruitAdapter adapter = new FruitAdapter(CarActivity.this, R.layout.fruit_item, MainActivity.instance_main.fruitList);
            ListView listView = (ListView) findViewById(R.id.list_view);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    final Fruit mfruit = MainActivity.instance_main.fruitList.get(position);
                    mfruit.setPos(position);
                    //Toast.makeText(CarActivity.this,mfruit.getName(),Toast.LENGTH_SHORT).show();

                    click_image.setImageResource(mfruit.getImageId());
                    click_name.setText(mfruit.getName());

                    String jiage = String.valueOf(mfruit.getPrice());
                    String zhonglei = mfruit.getGoodsType();
                    String ext = mfruit.getExtMessage();
                    String in = "￥" + jiage + "\n" + zhonglei + " " + ext;
                    click_mes.setText(in);

                    if (mfruit.getCollect() == 1) {
                        collect_star.setImageResource(R.drawable.full_star);
                    } else {
                        collect_star.setImageResource(R.drawable.empty_star);
                    }

                    collect_star.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (mfruit.getCollect() == 0) {
                                mfruit.setCollect(1);
                                MainActivity.instance_main.fruitList.set(mfruit.getPos(), mfruit);
                                collect_star.setImageResource(R.drawable.full_star);
                                Toast.makeText(CarActivity.this, "已收藏该商品", Toast.LENGTH_SHORT).show();
                            } else {
                                mfruit.setCollect(0);
                                MainActivity.instance_main.fruitList.set(mfruit.getPos(), mfruit);
                                collect_star.setImageResource(R.drawable.empty_star);
                                Toast.makeText(CarActivity.this, "已取消收藏", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                    add_to_car.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
//                            dynamic_filter = new IntentFilter();
//                            dynamic_filter.addAction("com.mycompany.fruit.DynamicReceiver");
//                            dynamicReceiver = new DynamicReceiver();
//                            CarActivity.this.registerReceiver(dynamicReceiver,dynamic_filter);

                            Bundle mBundle = new Bundle();
                            mBundle.putSerializable("the_car_random_fruit",mfruit);
                            Intent intent = new Intent();
                            intent.setAction("com.mycompany.fruit.DynamicReceiver");
                            intent.putExtras(mBundle);
                            CarActivity.this.sendBroadcast(intent);
                        }
                    });
                }

            });
        }




        fab_goods = (FloatingActionButton) findViewById(R.id.fab_shop);
        fab_goods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(fab_goods, "切换到商品列表", Snackbar.LENGTH_SHORT)
                        .setAction("确认", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                            }
                        })
                        .setActionTextColor(getResources().getColor(R.color.colorPrimary))
                        .setDuration(5000)
                        .show();
                Intent intent = new Intent(CarActivity.this, GoodsActivity.class);
                startActivity(intent);
            }
        });

        fab_car = (FloatingActionButton) findViewById(R.id.fab_car);
        fab_car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(fab_car, "切换到购物车", Snackbar.LENGTH_SHORT)
                        .setAction("确认", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                            }
                        })
                        .setActionTextColor(getResources().getColor(R.color.colorPrimary))
                        .setDuration(5000)
                        .show();

                Intent intent = new Intent(CarActivity.this, DetailActivity.class);
                startActivity(intent);

            }
        });




    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(dynamicReceiver);
    }
}
