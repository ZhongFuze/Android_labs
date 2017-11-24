package com.zhong.midterm;

/**
 * Created by user on 2017/11/24.
 */
import android.content.Context;
import android.widget.Toast;
public class Util {
    private static Toast toast;

    public static void showToast(Context context, String msg){
        if(toast == null){
            toast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
        }

        toast.setText(msg);
        toast.show();
    }
}
