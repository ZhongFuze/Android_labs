package zhong.com.movetest;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MainActivity extends Activity {

    private ImageText imageText = null;
    private ImageView test;
    private ImageView up;
    private ImageView down;
    private ImageView left;
    private ImageView right;

    private ImageView mingde;
    private ImageView shensi;
    private ImageView gezhi;
    private ImageView zhishan;

    private TextView receive;
    private Button connectbutton;

    public static final int WALK_UP = 1;
    public static final int WALK_LEFT = 2;
    public static final int WALK_RIGHT = 3;
    public static final int WALK_DOWN = 4;

    private float pos_x;
    private float pos_y;

    /****************socket连接*****************/
    private static final String SERVERIP = "111.231.202.192";
    private static final int SERVERPORT = 10000;
    private Thread mThread = null;
    private Socket mSocket = null;
    private Button mButton_In = null;
    private BufferedReader mBufferedReader	= null;
    private PrintWriter mPrintWriter = null;
    private  String mStrMSG = "";
    /*****************************************/


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        test = (ImageView)findViewById(R.id.test);
        up = (ImageView)findViewById(R.id.up);
        down = (ImageView)findViewById(R.id.down);
        left = (ImageView)findViewById(R.id.left);
        right = (ImageView)findViewById(R.id.right);
        receive = (TextView)findViewById(R.id.receive);
        connectbutton = (Button)findViewById(R.id.connect);

        shensi=(ImageView) findViewById(R.id.shensi);
        mingde=(ImageView) findViewById(R.id.mingde);
        gezhi=(ImageView) findViewById(R.id.gezhi);
        zhishan=(ImageView) findViewById(R.id.zhishan);



        pos_x = test.getX();
        pos_y = test.getY();

        /*******socket*********/
        connectbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new connect().start();
            }
        });
        /**********************/

        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message msg = new Message();
                        msg.what = WALK_UP;
                        walk_handler.sendMessage(msg);
                    }
                }).start();
            }
        });

        imageText = (ImageText) findViewById(R.id.buttontext1);
        imageText.setImageResource(R.drawable.sun);
        imageText.setText("haha");
        imageText.setmSay("xixixixi");
        //imageText.setmSayhidden();
        imageText.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
//                Toast.makeText(InitialActivity.this, "您好", Toast.LENGTH_SHORT).show();

            }
        });

        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message msg = new Message();
                        msg.what = WALK_DOWN;
                        walk_handler.sendMessage(msg);
                    }
                }).start();
            }
        });

        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message msg = new Message();
                        msg.what = WALK_LEFT;
                        walk_handler.sendMessage(msg);
                    }
                }).start();
            }
        });

        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message msg = new Message();
                        msg.what = WALK_RIGHT;
                        walk_handler.sendMessage(msg);
                    }
                }).start();
            }
        });


        mingde.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "明德园生活区",
                        Toast.LENGTH_SHORT).show();
            }
        });

        gezhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "格致园生活区",
                        Toast.LENGTH_SHORT).show();
            }
        });

        zhishan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "至善园生活区",
                        Toast.LENGTH_SHORT).show();
            }
        });

        shensi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "慎思园生活区",
                        Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    protected void onResume() {
        /**
         * 设置为横屏
         */
        if(getRequestedOrientation()!=ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        super.onResume();
    }

    private Handler walk_handler = new Handler(){
      public void handleMessage(Message msg){
          switch (msg.what){
              case WALK_UP:{
                  pos_y = pos_y-50;
                  imageText.setY(pos_y);
                  break;
              }
              case WALK_LEFT:{
                  pos_x = pos_x-50;
                  imageText.setX(pos_x);
                  break;
              }
              case WALK_DOWN:{
                  pos_y = pos_y+50;
                  imageText.setY(pos_y);
                  break;
              }
              case WALK_RIGHT:{
                  pos_x = pos_x+50;
                  imageText.setX(pos_x);
                  break;
              }
              default: break;
          }
          try{
              String str = String.valueOf(pos_x)+","+String.valueOf(pos_y)+"\n";
              mPrintWriter.print(str);
              mPrintWriter.flush();
          }catch (Exception e){
              Log.e("Debug",e.toString());
          }
          mThread = new Thread(mRunnable);
          mThread.start();
      }
    };

    /***********socket****************/
    class connect extends Thread{
        public connect(){}
        @Override
        public void run(){
            try
            {
                mSocket = new Socket(SERVERIP, SERVERPORT);
                mBufferedReader = new BufferedReader(new InputStreamReader(mSocket.getInputStream()));
                mPrintWriter=new PrintWriter(mSocket.getOutputStream(), true);
            }
            catch (Exception e)
            {
                // TODO: handle exception
                Log.e("Debug", e.toString());
            }
        }
    }

    private Runnable mRunnable = new Runnable()
    {
        public void run()
        {
            while (true)
            {
                try
                {
                    if ( (mStrMSG = mBufferedReader.readLine()) != null )
                    {
                        mStrMSG+="\n";
                        mHandler.sendMessage(mHandler.obtainMessage());
                    }
                }
                catch (Exception e)
                {
                    Log.e("Debug", e.toString());
                }
            }
        }
    };

    Handler	mHandler= new Handler()
    {
        public void handleMessage(Message msg)
        {
            super.handleMessage(msg);
            try
            {
                receive.setText(mStrMSG);
            }
            catch (Exception e)
            {
                Log.e("Debug", e.toString());
            }
        }
    };

    /*********************************/
}
