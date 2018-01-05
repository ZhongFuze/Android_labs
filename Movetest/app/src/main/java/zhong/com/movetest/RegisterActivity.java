package zhong.com.movetest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RegisterActivity extends Activity {


    private TextView hasname;
    private TextView guess;


    private String url = "http://111.231.202.192:8080/Android";
    private EditText number;
    private EditText pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        hasname = (TextView) findViewById(R.id.msg1);
        guess = (TextView) findViewById(R.id.msg2);

        number = (EditText) findViewById(R.id.reg_name);
        pwd = (EditText) findViewById(R.id.reg_pass);

        hasname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(RegisterActivity.this, "注册", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setClass(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        guess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RegisterActivity.this, "只想看看", Toast.LENGTH_SHORT).show();

            }
        });
    }


    public void getRequestReg(View view) {
        getreg();
    }

    //使用GET方法
    public void getreg() {
        final String numberStr = number.getText().toString().trim();
        final String pwdStr = pwd.getText().toString().trim();
        final String aimStr = "reg";
        //连接网络要使用线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                //GET方法要在路径上写好数据
                Request request = new Request.Builder()
                        .url(url + "/login.jsp?number=" + numberStr + "&pwd=" + pwdStr +"&aim=" + aimStr)
                        .build();
                OkHttpClient client = new OkHttpClient.Builder()
                        //连接超时
                        .connectTimeout(10, TimeUnit.SECONDS)
                        //写入超时
                        .writeTimeout(20, TimeUnit.SECONDS)
                        //读取超时
                        .readTimeout(20, TimeUnit.SECONDS)
                        .build();
                Log.d("Reg","GET路径为："+request.url());
                Call call = client.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        //失败做的一些处理
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        //获得返回数据
                        String res = response.body().string();
                        Log.d("Reg", "GET方法返回的数据为：" + res);
                        parseJSON(res);
                    }
                });
            }
        }).start();
    }


    //解析JSON数据
    public void parseJSON(String jsonStr) {
        try {
            JSONObject jsonObject = new JSONObject(jsonStr);
            String resulet = jsonObject.getString("result");
            //判断是否成功
            if (resulet.equals("success")) {
//                String fileUrl = jsonObject.getString("file");
//                final String imageUrl = url + fileUrl;

                //操作UI不能在子线程上操作，要在UI线程上操作
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //登录成功，跳转到另一个Activity
                        Toast.makeText(getApplicationContext(), "登录成功", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
//                        intent.putExtra("imageUrl", imageUrl);
                        startActivity(intent);


                    }
                });
            } else {
                //登录失败
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "账号或密码错误", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
