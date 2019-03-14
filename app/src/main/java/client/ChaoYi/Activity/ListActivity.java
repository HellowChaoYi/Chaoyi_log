package client.ChaoYi.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import client.ChaoYi.R;
import client.ChaoYi.Ui.CustomTitleBar;
import client.ChaoYi.Ui.until.StatusBarUtil;

public class ListActivity extends AppCompatActivity {
    private CustomTitleBar mcustomTitlebar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        mcustomTitlebar = findViewById(R.id.titlebar);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        CustomTitleBar.LayoutParams lp = new CustomTitleBar.LayoutParams(mcustomTitlebar.getLayoutParams());
        lp.setMargins(0, 10000, 10, 0);

        mcustomTitlebar.setLeftIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ListActivity.this, "左边", Toast.LENGTH_SHORT).show();
            }
        });

    }


}
