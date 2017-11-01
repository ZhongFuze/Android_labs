package com.mycompany.fruit;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Zhong on 2017/10/27.
 */

public class FruitAdapter extends ArrayAdapter<Fruit>{
    private int resourceId;
    public FruitAdapter(Context context, int textViewResourceId, List<Fruit> objects){
        super(context,textViewResourceId,objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Fruit fruit = getItem(position);
        View view;
        ViewHolder viewHolder;

        if (convertView == null)
        {
            view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.fruitImage = (ImageView) view.findViewById(R.id.fruit_image);
            viewHolder.fruitPrice = (TextView) view.findViewById(R.id.fruit_price);
            viewHolder.fruitName = (TextView) view.findViewById(R.id.fruit_name);
            viewHolder.fruitType = (TextView) view.findViewById(R.id.fruit_type);
            viewHolder.getFruitExtMessage = (TextView) view.findViewById(R.id.fruit_extmessage);

            view.setTag(viewHolder);//将viewholder存在Tag中

        }
        else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.fruitImage.setImageResource(fruit.getImageId());
        viewHolder.fruitPrice.setText(String.valueOf(fruit.getPrice()));
        viewHolder.fruitName.setText(fruit.getName());
        viewHolder.fruitType.setText(fruit.getGoodsType());
        viewHolder.getFruitExtMessage.setText(fruit.getExtMessage());

        return view;
    }

    static class ViewHolder {
        TextView fruitName;
        TextView fruitPrice;
        ImageView fruitImage;
        TextView fruitType;
        TextView getFruitExtMessage;
    }

}


