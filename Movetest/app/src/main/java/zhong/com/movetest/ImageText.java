package zhong.com.movetest;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Zhong on 2018/1/5.
 */

public class ImageText extends LinearLayout {

    private ImageView mImgView = null;
    private TextView mTextView = null;
    private TextView mSay=null;
    private Context mContext;


    public ImageText(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        LayoutInflater.from(context).inflate(R.layout.image_text, this, true);
        mContext = context;
        mImgView = (ImageView)findViewById(R.id.img);
        mTextView = (TextView)findViewById(R.id.text);
        mSay = (TextView) findViewById(R.id.say);

    }

    /*设置图片接口*/
    public void setImageResource(int resId){
        mImgView.setImageResource(resId);
    }

    /*设置文字接口*/
    public void setText(String str){
        mTextView.setText(str);
    }

    public void setmSay(String saytext)
    {
        mSay.setText(saytext);
        mSay.setVisibility(View.VISIBLE);
    }

    public void setmSayhidden()
    {
        mSay.setVisibility(View.INVISIBLE);
    }


    /*设置文字大小*/
//    public void setTextSize(float size){
//        mTextView.setTextSize(size);
//    }

//    /*设置触摸接口*/
//    public void setOnTouch(OnTouchListener listen){
//        mImgView.setOnTouchListener(listen);
//        //mTextView.setOnTouchListener(listen);
//    }
}
