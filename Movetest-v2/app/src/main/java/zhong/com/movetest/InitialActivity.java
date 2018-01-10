package zhong.com.movetest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class InitialActivity extends Activity {

//    private ImageText imageText = null;
    private Button reg;
    private Button log;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);

        reg = (Button) findViewById(R.id.reg);
        log = (Button) findViewById(R.id.log);

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(InitialActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(InitialActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });


    }
}
