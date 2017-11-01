package com.mycompany.fruit;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

//import static com.mycompany.fruit.GoodsActivity.fruitList;

/**
 * Created by Zhong on 2017/10/28.
 */

public class FruitRecycleAdapter extends RecyclerView.Adapter<FruitRecycleAdapter.ViewHolder>{
    private List<Fruit> mFruitList;

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView fruitName;
        Button NameFirst;

        ViewHolder(View view){
            super(view);
            fruitName = (TextView) view.findViewById(R.id.fruit_name);
            NameFirst = (Button) view.findViewById(R.id.name_first);
        }
    }

    public  FruitRecycleAdapter(List<Fruit> fruitList)
    {
        mFruitList = fruitList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.goods_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,int position){
        Fruit fruit = mFruitList.get(position);
        holder.fruitName.setText(fruit.getName());
        String name = fruit.getName();
        String First = String.valueOf(name.charAt(0));
        holder.NameFirst.setText(First);
    }

    @Override
    public int getItemCount(){
        return  mFruitList.size();
    }

    public List<Fruit> getmFruitList()
    {
        return mFruitList;
    }

    public double getSum()
    {
        double sum=0;
        if(!mFruitList.isEmpty())
        {
            for(int i=0;i<mFruitList.size();i++)
            {
                sum+=mFruitList.get(i).getPrice();
            }
        }

        return sum;
    }

}
