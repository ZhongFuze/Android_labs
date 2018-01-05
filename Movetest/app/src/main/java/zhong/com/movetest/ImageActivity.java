package zhong.com.movetest;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ImageActivity extends Activity {

    private String url;
    private ImageView imageView;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    //设置图片
                    imageView.setImageBitmap((Bitmap) msg.obj);
                    break;
                case 2:
                    //一些提示
                    Toast.makeText(getApplicationContext(), (String) msg.obj, Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        imageView = (ImageView) findViewById(R.id.imageview);

        //获取上一个Activity传来的图片路径
        Intent intent = getIntent();
        url = intent.getStringExtra("imageUrl");
        Log.d("Main2Activity", "获得传来的数据" + url);
    }

    public void showImage(View view) {
        showImage(url);
    }

    public void saveImage(View view) {
        if (ContextCompat.checkSelfPermission(ImageActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(ImageActivity.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        } else {
            saveImage(url);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    saveImage(url);
                } else {
                    Toast.makeText(getApplicationContext(), "你拒绝授权", Toast.LENGTH_SHORT).show();
                }
        }
    }

    private void showImage(final String ImageUrl) {
        //连接网络要使用线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient.Builder()
                            //使用缓存
                            .cache(new Cache(getExternalCacheDir().getAbsoluteFile(), 1024 * 1024 * 2))
                            .build();
                    Request request = new Request.Builder()
                            .url(ImageUrl)
                            .build();
                    Log.d("Main2Activity","图片路径为："+request.url());
                    Response response = client.newCall(request).execute();
                    //获取输入流
                    InputStream inputStream = response.body().byteStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    //使用Hander线程修改UI
                    Message msg = new Message();
                    msg.what = 1;
                    msg.obj = bitmap;
                    handler.sendMessage(msg);

                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void saveImage(final String imageUrl) {
        //连接网络要使用线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient.Builder()
                            //使用缓存
                            .cache(new Cache(getExternalCacheDir().getAbsoluteFile(), 1024 * 1024 * 2))
                            .build();
                    Request request = new Request.Builder()
                            .url(imageUrl)
                            .build();
                    Log.d("Main2Activity","图片路径为："+request.url());
                    Response response = client.newCall(request).execute();
                    //获取字节数据
                    byte[] bytes = response.body().bytes();
                    //获取文件名
                    String[] urls = url.split("/");
                    String filename = urls[urls.length - 1];
                    //保存文件
                    File file = new File(Environment.getExternalStorageDirectory(), filename);
                    FileOutputStream fos = new FileOutputStream(file);
                    fos.write(bytes);
                    fos.flush();
                    fos.close();
                    //弹出Toast
                    Message msg = new Message();
                    msg.what = 2;
                    msg.obj = "保存成功";
                    handler.sendMessage(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("Main2Activity", "图片保存失败");
                    Message msg = new Message();
                    msg.what = 2;
                    msg.obj = "图片保存失败";
                    handler.sendMessage(msg);
                }
            }
        }).start();
    }
}
