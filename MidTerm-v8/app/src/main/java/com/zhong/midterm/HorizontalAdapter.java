package com.zhong.midterm;

/**
 * Created by Zhong on 2017/11/19.
 */
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class HorizontalAdapter extends RecyclerView.Adapter<HorizontalAdapter.ViewHolder> {

    //private List<Hero> heros = new ArrayList<>();
    private List<User_heros> user_heroses = new ArrayList<>();

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView peopleImageId;
        TextView peopleName;

        ViewHolder(View view){
            super(view);
            peopleImageId = (ImageView) view.findViewById(R.id.people_image_id);
            peopleName = (TextView) view.findViewById(R.id.people_name);
        }

    }

    public HorizontalAdapter(List<User_heros> user_heroses){
        //this.heros = heros;
        this.user_heroses = user_heroses;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_horizontal, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(HorizontalAdapter.ViewHolder holder, int position){
        User_heros people = user_heroses.get(position);
        holder.peopleImageId.setImageResource(people.getImage());
        holder.peopleName.setText(people.getName());
    }

    @Override
    public int getItemCount() {
        return user_heroses.size();
    }

    public List<User_heros> getCountryPeoples()
    {
        return user_heroses;
    }

}