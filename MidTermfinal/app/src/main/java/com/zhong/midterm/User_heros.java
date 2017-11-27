package com.zhong.midterm;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**
 * Created by Zhong on 2017/11/23.
 */

public class User_heros extends DataSupport implements Serializable {
    private int id;
    private int user_id;
    private int image;
    private String name;
    private String sex;
    private String birth;
    private String death;
    private String native_;
    private int attack;
    private int defense;
    private int live;
    private int bimage;

    public int getId(){return id;}
    public int getUser_id(){return user_id;}
    public int getImage(){return image;}
    public String getName(){return name;}
    public String getSex(){return sex;}
    public String getBirth(){return birth;}
    public String getDeath(){return death;}
    public String getNative_(){return native_;}
    public int getAttack(){return attack;}
    public int getDefense(){return defense;}
    public int getLive(){return live;}
    public int getBimage(){return bimage;}

    public void setId(int id) { this.id=id;}
    public void setUser_id(int user_id){this.user_id = user_id;}
    public void setImage(int image){this.image = image;}
    public void setName(String name){this.name = name;}
    public void setSex(String sex){this.sex = sex;}
    public void setBirth(String birth){this.birth = birth;}
    public void setDeath(String death){this.death = death;}
    public void setNative_(String native_){this.native_ = native_;}
    public void setAttack(int attack){this.attack = attack;}
    public void setDefense(int defense){this.defense = defense;}
    public void setLive(int live){this.live = live;}
    public void setBimage(int bimage){this.bimage = bimage;}

    public void setAll(int id,int user_id,Hero hero){
        this.id=id;
        this.user_id = user_id;
        this.image = hero.getImage();
        this.name = hero.getName();
        this.sex = hero.getSex();
        this.birth = hero.getBirth();
        this.death = hero.getDeath();
        this.native_ = hero.getNative_();
        this.attack = hero.getAttack();
        this.defense = hero.getDefense();
        this.live = hero.getLive();
        this.bimage = hero.getBimage();
    }
}
