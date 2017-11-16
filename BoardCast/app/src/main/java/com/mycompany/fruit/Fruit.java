package com.mycompany.fruit;

import java.io.Serializable;

/**
 * Created by Zhong on 2017/10/27.
 */

public class Fruit  implements Serializable {
    private String name;
    private double price;
    private int imageId;
    private String goodsType;
    private String ExtMessage;
    private int collect;
    private int pos;


    public Fruit(String name, double price, int imageId,String goodsType,String ExtMessage,int collect){
        this.name = name;
        this.price = price;
        this.imageId = imageId;
        this.goodsType = goodsType;
        this.ExtMessage = ExtMessage;
        this.collect = collect;
    }

    public String getName()
    {
        return name;
    }

    public double getPrice() { return price; }

    public int getImageId()
    {
        return imageId;
    }

    public String getGoodsType() { return goodsType; }

    public String getExtMessage() {return ExtMessage; }

    public int getCollect() { return collect;}

    public void setCollect(int collect)
    {
        this.collect = collect;
    }

    public int getPos(){return pos;};

    public void setPos(int P){ pos = P;}

}