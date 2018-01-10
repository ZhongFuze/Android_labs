package zhong.com.movetest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ClientActivity extends Activity {

    //网址主连接
    private String url = "http://111.231.202.192:8080/Android";
    private EditText number;
    private EditText pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);

        number = (EditText) findViewById(R.id.edit_number);
        pwd = (EditText) findViewById(R.id.edit_pwd);
    }


    public void getRequest(View view) {
        get();
    }

    public void postRequest(View view) {
        post();
    }

    //使用GET方法
    public void get() {
        final String numberStr = number.getText().toString().trim();
        final String pwdStr = pwd.getText().toString().trim();
        //连接网络要使用线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                //GET方法要在路径上写好数据
                Request request = new Request.Builder()
                        .url(url + "/login.jsp?number=" + numberStr + "&pwd=" + pwdStr)
                        .build();
                OkHttpClient client = new OkHttpClient.Builder()
                        //连接超时
                        .connectTimeout(10, TimeUnit.SECONDS)
                        //写入超时
                        .writeTimeout(20, TimeUnit.SECONDS)
                        //读取超时
                        .readTimeout(20, TimeUnit.SECONDS)
                        .build();
                Log.d("MainActivity","GET路径为："+request.url());
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
                        Log.d("MainActivity", "GET方法返回的数据为：" + res);
                        parseJSON(res);
                    }
                });
            }
        }).start();
    }

    //使用POST方法
    public void post() {
        final String numberStr = number.getText().toString().trim();
        final String pwdStr = pwd.getText().toString().trim();
        //连接网络要使用线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient.Builder()
                        //连接超时
                        .connectTimeout(10, TimeUnit.SECONDS)
                        //写入超时
                        .writeTimeout(20, TimeUnit.SECONDS)
                        //读取超时
                        .readTimeout(20, TimeUnit.SECONDS)
                        .build();
                //打包表单数据
                FormBody.Builder formBodyBuild = new FormBody.Builder();
                formBodyBuild.add("number", numberStr);
                formBodyBuild.add("pwd", pwdStr);
                //设置请求头
                Request request = new Request.Builder()
                        .url(url+"/login.jsp")
                        //上传文件的写法
                        /*.post(RequestBody.create(MediaType.parse("text/x-markdown; charset=utf-8"),
                                new File("/mnt/sdcard/a.png")))*/
                        .post(formBodyBuild.build())
                        .build();
                Log.d("MainActivity","POST路径为："+request.url());
                Call call = client.newCall(request);
                request.url();
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        //失败做的一些处理，比如连接失败
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        //获得返回数据
                        String res = response.body().string();
                        Log.d("MainActivity", "POST方法返回的数据为：" + res);
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
                String fileUrl = jsonObject.getString("file");
                final String imageUrl = url + fileUrl;

                //操作UI不能在子线程上操作，要在UI线程上操作
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //登录成功，跳转到另一个Activity
                        Intent intent = new Intent(ClientActivity.this, ImageActivity.class);
                        intent.putExtra("imageUrl", imageUrl);
                        startActivity(intent);

                        Toast.makeText(getApplicationContext(), "登录成功", Toast.LENGTH_SHORT).show();
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
