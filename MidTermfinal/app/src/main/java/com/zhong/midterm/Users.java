package com.zhong.midterm;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**
 * Created by user on 2017/11/21.
 */

public class Users extends DataSupport implements Serializable {
    private int id;
    private String name;
    private String password;
    private int image;
    private int point;

    public int getId() {return id;}
    public String getName(){return name;}
    public String getPassword(){return password;}
    public int getImage(){return image;}
    public int getPoint(){return point;}

    public void setId(int id){this.id = id;}
    public void setName(String name){this.name = name;}
    public void setPassword(String password){this.password = password;}
    public void setImage(int image){this.image = image;}
    public void setPoint(int point_add){point += point_add;}
    public void setAll(int id,String name,String password,int image,int point){
        this.id = id;
        this.name = name;
        this.password = password;
        this.image = image;
        this.point = point;
    }

}
