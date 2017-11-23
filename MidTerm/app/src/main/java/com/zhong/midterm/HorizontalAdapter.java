package com.zhong.midterm;

/**
 * Created by Zhong on 2017/11/19.
 */
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class HorizontalAdapter extends RecyclerView.Adapter<HorizontalAdapter.ViewHolder> {

    //private String[] titles;

    private List<CountryPeople> countryPeople = new ArrayList<>();

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView peopleImageId;
        TextView peopleName;
//        TextView peopleGender;
//        TextView peopleBornDate;
//        TextView peopleNative_place;
//        TextView peopleDependence;

        ViewHolder(View view){
            super(view);
            peopleImageId = (ImageView) view.findViewById(R.id.people_image_id);
            peopleName = (TextView) view.findViewById(R.id.people_name);
//            peopleGender = (TextView) view.findViewById(R.id.people_gender);
//            peopleBornDate = (TextView) view.findViewById(R.id.people_born_date);
//            peopleNative_place = (TextView) view.findViewById(R.id.people_native_place);
//            peopleDependence = (TextView) view.findViewById(R.id.people_dependence);
        }

    }
//    public HorizontalAdapter(String[] titles) {
//        this.titles = titles;
//    }

    public HorizontalAdapter(List<CountryPeople> countryPeople){
        this.countryPeople = countryPeople;
    }


    public void setCountryPeople(List<CountryPeople> countryPeople)
    {
        this.countryPeople = countryPeople;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_horizontal, parent, false);
        return new ViewHolder(view);
    }

//    @Override
//    public void onBindViewHolder(HorizontalAdapter.ViewHolder holder, int position) {
//        String title = titles[position];
//        holder.title.setText(title);
//    }

    @Override
    public void onBindViewHolder(HorizontalAdapter.ViewHolder holder, int position){
        CountryPeople people = countryPeople.get(position);
        holder.peopleImageId.setImageResource(people.getImageId());
        holder.peopleName.setText(people.getName());
//        holder.peopleGender.setText(people.getGender());
//        holder.peopleBornDate.setText(people.getBornDate());
//        holder.peopleNative_place.setText(people.getNative_place());
//        holder.peopleDependence.setText(people.getDependence());
    }

    @Override
    public int getItemCount() {
        return countryPeople.size();
    }

    public List<CountryPeople> getCountryPeoples()
    {
        return countryPeople;
    }

//    class ViewHolder extends RecyclerView.ViewHolder {
//        private TextView title;
//
//        ViewHolder(final View itemView) {
//            super(itemView);
//            this.title = (TextView) itemView.findViewById(R.id.title);
//        }
//    }
}