package zhong.com.movetest;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class ButtonActivity extends Activity {

    private ExpandableMenuOverlay menuOverlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo);

        menuOverlay = (ExpandableMenuOverlay) findViewById(R.id.button_menu);

        menuOverlay.setOnMenuButtonClickListener(new ExpandableButtonMenu.OnMenuButtonClick() {
            @Override
            public void onClick(ExpandableButtonMenu.MenuButton action) {
                switch (action) {
                    case MID:
                        Toast.makeText(ButtonActivity.this, "Mid pressed and dismissing...", Toast.LENGTH_SHORT).show();
                        menuOverlay.getButtonMenu().toggle();
                        break;
                    case LEFT:
                        Toast.makeText(ButtonActivity.this, "Left pressed", Toast.LENGTH_SHORT).show();
                        break;
                    case RIGHT:
                        Toast.makeText(ButtonActivity.this, "Right pressed", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

    }
}
