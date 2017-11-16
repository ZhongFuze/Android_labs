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
import android.view.View;
import android.widget.Toast;
import android.content.IntentFilter;

import java.io.InputStream;

import static android.content.Context.NOTIFICATION_SERVICE;
import static com.mycompany.fruit.R.id.view;

/**
 * Created by Zhong on 2017/11/1.
 */

public class MyBroadCastReceiver extends BroadcastReceiver{


    @Override
    public void onReceive(Context context, Intent intent){

        MainActivity.instance_main.a++;
        if(MainActivity.instance_main.b==0)
        {
            MainActivity.instance_main.initFruit();
        }
        final Fruit random_fruit = (Fruit) intent.getSerializableExtra("random_fruit");

        //Toast.makeText(context,"received in MyBroadcastReceiver",Toast.LENGTH_SHORT).show();
        // 设置通知栏id
        int notifyId = (int) System.currentTimeMillis();

        Intent new_intent = new Intent(context, CarActivity.class);
        Bundle mBundle = new Bundle();
        mBundle.putSerializable("the_random_fruit",random_fruit);
        new_intent.putExtras(mBundle);
        PendingIntent pIntent = PendingIntent.getActivity(context, 0, new_intent, PendingIntent.FLAG_UPDATE_CURRENT);
        //Bitmap bm = BitmapFactory.decodeResource(context.getResources(),random_fruit.getImageId());

        String content_text=random_fruit.getName()+"仅售"+String.valueOf(random_fruit.getPrice())+"!\n";

        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context);

        mBuilder.setContentTitle("新商品热卖")//设置通知栏标题
                .setContentText(content_text) //设置通知栏点击意图
                //  .setNumber(number) //设置通知集合的数量
                .setTicker("你有一条新的消息") //通知首次出现在通知栏，带上升动画效果的
                .setWhen(System.currentTimeMillis())//通知产生的时间，会在通知信息里显示，一般是系统获取到的时间
                .setPriority(Notification.PRIORITY_DEFAULT) //设置该通知优先级
                .setAutoCancel(true)//设置这个标志当用户单击面板就可以让通知将自动取消
                //.setOngoing(false)//ture，设置他为一个正在进行的通知。他们通常是用来表示一个后台任务,用户积极参与(如播放音乐)或以某种方式正在等待,因此占用设备(如一个文件下载,同步操作,主动网络连接)
                .setDefaults(Notification.DEFAULT_VIBRATE)//向通知添加声音、闪灯和振动效果的最简单、最一致的方式是使用当前的用户默认设置，使用defaults属性，可以组合
                //Notification.DEFAULT_ALL  Notification.DEFAULT_SOUND 添加声音 // requires VIBRATE permission
                //.setSmallIcon(R.drawable.full_star)//设置通知小ICON
                .setSmallIcon(random_fruit.getImageId())
                //.setLargeIcon(bm)
                //.setContentIntent(pIntent)
                .setFullScreenIntent(pIntent, false);
        Notification notify = mBuilder.build();
        //mNotificationManager.notify(notifyId,notify);
        mNotificationManager.notify(0,notify);


    }

}
