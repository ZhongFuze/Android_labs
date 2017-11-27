package com.zhong.midterm;

/**
 * Created by Zhong on 2017/11/19.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import org.litepal.crud.DataSupport;

import java.util.List;

public class HorizontalActivity extends AppCompatActivity {

    final List <Hero> heroList = DataSupport.findAll(Hero.class);
    final List <Users> usersList = DataSupport.findAll(Users.class);
    final List <User_heros> user_herosList = DataSupport.findAll(User_heros.class);

    final List <Hero> WeiHero = DataSupport.where("native_=?","魏").find(Hero.class);
    final List <Hero> ShuHero = DataSupport.where("native_=?","蜀").find(Hero.class);
    final List <Hero> WuHero = DataSupport.where("native_=?","吴").find(Hero.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizontal);
        ///////////////////////////////////////

        Log.d("Debug","horizon_user_id: "+Data.getUser_id());


        final String yourname= String.valueOf(Data.getUser_id());
        final List <User_heros> Weiuser = DataSupport.where("user_id=? and native_=?",yourname,"魏").find(User_heros.class);
        final List <User_heros> Shuuser = DataSupport.where("user_id=? and native_=?",yourname,"蜀").find(User_heros.class);
        final List <User_heros> Wuuser = DataSupport.where("user_id=? and native_=?",yourname,"吴").find(User_heros.class);

        for(int i=0;i<Weiuser.size();++i){
            Log.d("Debug",Weiuser.get(i).getNative_()+" "+Weiuser.get(i).getName()+" "+Weiuser.get(i).getImage()+" "+R.drawable.bcaocao);
        }
        //查重
        final Bundle mBundle = new Bundle();



        final HorizontalAdapter firstAdapter = new HorizontalAdapter(Shuuser);
        MultiSnapRecyclerView firstRecyclerView = (MultiSnapRecyclerView)findViewById(R.id.first_recycler_view);
        LinearLayoutManager firstManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        firstRecyclerView.setLayoutManager(firstManager);
        firstRecyclerView.setAdapter(firstAdapter);

        firstRecyclerView.addOnItemTouchListener(new OnRecyclerItemClickListener(firstRecyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder viewHolder) {
                HorizontalAdapter.ViewHolder viewHolder1 = (HorizontalAdapter.ViewHolder) viewHolder;
                int postion = viewHolder1.getAdapterPosition();
                final User_heros people = firstAdapter.getCountryPeoples().get(postion);
//                Toast.makeText(HorizontalActivity.this,"Click "+people.getName(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(HorizontalActivity.this, UserHeroDetail.class);
                mBundle.putSerializable("user_people",people);
                intent.putExtras(mBundle);
                startActivity(intent);

            }

            @Override
            public void onLongClick(RecyclerView.ViewHolder viewHolder) {
                HorizontalAdapter.ViewHolder viewHolder1 = (HorizontalAdapter.ViewHolder) viewHolder;
                int postion = viewHolder1.getAdapterPosition();
                final User_heros people = firstAdapter.getCountryPeoples().get(postion);
//                Toast.makeText(HorizontalActivity.this,"Long Click "+people.getName(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(HorizontalActivity.this, MoveActivity.class);
                mBundle.putSerializable("user_people",people);
                intent.putExtras(mBundle);
                startActivity(intent);
            }
        });

        //initCountryWei();
        final HorizontalAdapter secondAdapter = new HorizontalAdapter(Weiuser);
        MultiSnapRecyclerView secondRecyclerView =(MultiSnapRecyclerView) findViewById(R.id.second_recycler_view);
        LinearLayoutManager secondManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        secondRecyclerView.setLayoutManager(secondManager);
        secondRecyclerView.setAdapter(secondAdapter);

        secondRecyclerView.addOnItemTouchListener(new OnRecyclerItemClickListener(secondRecyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder viewHolder) {
                HorizontalAdapter.ViewHolder viewHolder2 = (HorizontalAdapter.ViewHolder) viewHolder;
                int postion = viewHolder2.getAdapterPosition();
                final User_heros people = secondAdapter.getCountryPeoples().get(postion);
//                Toast.makeText(HorizontalActivity.this,"Click "+people.getName(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(HorizontalActivity.this, UserHeroDetail.class);
                mBundle.putSerializable("user_people",people);
                intent.putExtras(mBundle);
                startActivity(intent);
            }

            @Override
            public void onLongClick(RecyclerView.ViewHolder viewHolder) {
                HorizontalAdapter.ViewHolder viewHolder2 = (HorizontalAdapter.ViewHolder) viewHolder;
                int postion = viewHolder2.getAdapterPosition();
                final User_heros people = secondAdapter.getCountryPeoples().get(postion);
//                Toast.makeText(HorizontalActivity.this,"Long Click "+people.getName(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(HorizontalActivity.this, MoveActivity.class);
                mBundle.putSerializable("user_people",people);
                intent.putExtras(mBundle);
                startActivity(intent);
            }
        });


        //initCountryWu();
        final HorizontalAdapter thirdAdapter = new HorizontalAdapter(Wuuser);
        MultiSnapRecyclerView thirdRecyclerView = (MultiSnapRecyclerView)findViewById(R.id.third_recycler_view);
        LinearLayoutManager thirdManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        thirdRecyclerView.setLayoutManager(thirdManager);
        thirdRecyclerView.setAdapter(thirdAdapter);

        thirdRecyclerView.addOnItemTouchListener(new OnRecyclerItemClickListener(thirdRecyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder viewHolder) {
                HorizontalAdapter.ViewHolder viewHolder3 = (HorizontalAdapter.ViewHolder) viewHolder;
                int postion = viewHolder3.getAdapterPosition();
                final User_heros people = thirdAdapter.getCountryPeoples().get(postion);
//                Toast.makeText(HorizontalActivity.this,"Click "+people.getName(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(HorizontalActivity.this, UserHeroDetail.class);
                mBundle.putSerializable("user_people",people);
                intent.putExtras(mBundle);
                startActivity(intent);
            }

            @Override
            public void onLongClick(RecyclerView.ViewHolder viewHolder) {
                HorizontalAdapter.ViewHolder viewHolder3 = (HorizontalAdapter.ViewHolder) viewHolder;
                int postion = viewHolder3.getAdapterPosition();
                final User_heros people = thirdAdapter.getCountryPeoples().get(postion);
//                Toast.makeText(HorizontalActivity.this,"Long Click "+people.getName(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(HorizontalActivity.this, MoveActivity.class);
                mBundle.putSerializable("user_people",people);
                intent.putExtras(mBundle);
                startActivity(intent);

            }
        });

        ImageButton addShu = (ImageButton) findViewById(R.id.add_shu);

        addShu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HorizontalActivity.this,FancyActivity.class);
                startActivity(intent);
            }
        });

        ImageButton addWei = (ImageButton) findViewById(R.id.add_wei);

        addWei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HorizontalActivity.this,FancyActivity.class);
                startActivity(intent);
            }
        });

        ImageButton addWu = (ImageButton) findViewById(R.id.add_wu);
        addWu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HorizontalActivity.this,FancyActivity.class);
                startActivity(intent);
            }
        });
    }


}
