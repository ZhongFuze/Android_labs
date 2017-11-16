package com.mycompany.fruit;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by Zhong on 2017/11/1.
 */

public class DynamicReceiver extends BroadcastReceiver {
//    private static final String DYNAMICACTION = ""
    Bitmap bm;
    @Override
    public void onReceive(Context context, Intent intent){
        //Toast.makeText(context,"received in DynamicReceiver",Toast.LENGTH_SHORT).show();

        final Fruit the_car_random_fruit = (Fruit) intent.getSerializableExtra("the_car_random_fruit");//改动！！！！！！！！！

        int notifyId = (int) System.currentTimeMillis();

        Intent the_new_intent = new Intent(context, DetailActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(context, 1, the_new_intent, PendingIntent.FLAG_UPDATE_CURRENT);

        String content_text=the_car_random_fruit.getName()+"已添加到购物车~\n";

        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context);
        //Bitmap bm = BitmapFactory.decodeResource(context.getResources(),the_car_random_fruit.getImageId());
        mBuilder.setContentTitle("马上下单")//设置通知栏标题
                .setContentText(content_text) //设置通知栏点击意图
                //  .setNumber(number) //设置通知集合的数量
                .setTicker("你有一条新的消息") //通知首次出现在通知栏，带上升动画效果的
                .setWhen(System.currentTimeMillis())//通知产生的时间，会在通知信息里显示，一般是系统获取到的时间
                .setPriority(Notification.PRIORITY_DEFAULT) //设置该通知优先级
                .setAutoCancel(true)//设置这个标志当用户单击面板就可以让通知将自动取消
                //.setOngoing(false)//ture，设置他为一个正在进行的通知。他们通常是用来表示一个后台任务,用户积极参与(如播放音乐)或以某种方式正在等待,因此占用设备(如一个文件下载,同步操作,主动网络连接)
                .setDefaults(Notification.DEFAULT_VIBRATE)//向通知添加声音、闪灯和振动效果的最简单、最一致的方式是使用当前的用户默认设置，使用defaults属性，可以组合
                //Notification.DEFAULT_ALL  Notification.DEFAULT_SOUND 添加声音 // requires VIBRATE permission
                .setSmallIcon(the_car_random_fruit.getImageId())//设置通知小ICON
                //.setSmallIcon(the_car_random_fruit.getImageId())
                //.setLargeIcon(bm)
                //.setContentIntent(pIntent)
                .setFullScreenIntent(pIntent, false);
        Notification notify = mBuilder.build();
        mNotificationManager.notify(1,notify);
    }
}
