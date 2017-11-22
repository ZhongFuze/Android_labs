package com.zhong.midterm;

import java.io.Serializable;

/**
 * Created by Zhong on 2017/11/19.
 */

public class CountryPeople implements Serializable {
    private int imageId;
    private String name;
    private String gender;
    private String BornDate;
    private String native_place;
    private String dependence;

    //int imageId, String name, String gender, String BornDate, String native_place, String dependence
    public CountryPeople(int imageId, String name, String gender, String BornDate, String native_place, String dependence){
        this.imageId = imageId;
        this.name = name;
        this.gender = gender;
        this.BornDate = BornDate;
        this.native_place = native_place;
        this.dependence = dependence;
    }

    public String getName()
    {
        return name;
    }

    public int getImageId()
    {
        return imageId;
    }
    public String getGender()
    {
        return gender;
    }

    public String getBornDate()
    {
        return BornDate;
    }

    public String getNative_place()
    {
        return native_place;
    }

    public String getDependence()
    {
        return dependence;
    }
}
