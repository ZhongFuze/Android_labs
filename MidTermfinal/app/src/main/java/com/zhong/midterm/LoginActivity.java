package com.zhong.midterm;


import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.transition.Explode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.litepal.crud.DataSupport;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//import butterknife.ButterKnife;
//import butterknife.BindViews;
//import butterknife.OnClick;
public class LoginActivity extends AppCompatActivity {

    final public List<Users> usersList = DataSupport.findAll(Users.class);

    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.bt_go)
    Button btGo;
    @BindView(R.id.cv)
    CardView cv;
    @BindView(R.id.fab)
    FloatingActionButton fab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        View v = findViewById(R.id.cv);
        v.getBackground().setAlpha(200);//0~255
        ButterKnife.bind(this);
    }


    @OnClick({R.id.bt_go, R.id.fab})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab:
                getWindow().setExitTransition(null);
                getWindow().setEnterTransition(null);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this,fab,fab.getTransitionName());
                            //ActivityOptions.makeSceneTransitionAnimation(this, fab, fab.getTransitionName());
                    startActivity(new Intent(this, RegisterActivity.class), options.toBundle());
                } else {
                    startActivity(new Intent(this, RegisterActivity.class));
                }
                break;
            case R.id.bt_go:

                String yourname = etUsername.getText().toString();
                String yourpassword = etPassword.getText().toString();

//                Toast.makeText(LoginActivity.this,"user: "+yourname+" "+"password: "+yourpassword,Toast.LENGTH_SHORT).show();

                boolean find_user = false;
                int youruser_id = -1;
                for(int i=0;i<usersList.size();++i){
                    Log.d("Debug",usersList.get(i).getName()+" "+usersList.get(i).getPassword());
                    if(usersList.get(i).getName().equals(yourname)&&usersList.get(i).getPassword().equals(yourpassword)){
                        find_user = true;
                        youruser_id = usersList.get(i).getId();
                        Data.setUser_id(youruser_id);

                        Log.d("Debug","login_user_id: "+Data.getUser_id());

                        break;
                    }
                }
                Bundle mBundle = new Bundle();
                if(find_user){

//                    mBundle.putSerializable("youruser_id",youruser_id);
                    Toast.makeText(LoginActivity.this,"have this user",Toast.LENGTH_SHORT).show();
                    Explode explode = new Explode();
                    explode.setDuration(500);
                    getWindow().setExitTransition(explode);
                    getWindow().setEnterTransition(explode);
                    ActivityOptionsCompat oc2 = ActivityOptionsCompat.makeSceneTransitionAnimation(this);
                    Intent i2 = new Intent(this,HorizontalActivity.class);
                    i2.putExtras(mBundle);
                    startActivity(i2, oc2.toBundle());
                }
                else{
                    Toast.makeText(LoginActivity.this,"please register first",Toast.LENGTH_SHORT).show();

                }

                //
                break;
        }
    }
}
