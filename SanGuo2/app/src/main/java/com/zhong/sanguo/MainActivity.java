package com.zhong.sanguo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    private MYDatabaseHelper dbHelp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelp = new MYDatabaseHelper(this,"BookStore.db",null,1);
        Button createDatabase = (Button) findViewById(R.id.create_database);
        createDatabase.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                dbHelp.getWritableDatabase();
            }
        });
    }
}
