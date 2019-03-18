package client.ChaoYi.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import client.ChaoYi.R;
import client.ChaoYi.Ui.until.StatusBarUtil;

public class SettextActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settext);

        StatusBarUtil.setStatusBarDarkTheme(this, true);
    }
}
