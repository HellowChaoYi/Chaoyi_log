package client.ChaoYi.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import client.ChaoYi.R;
import client.ChaoYi.Ui.until.StatusBarUtil;
import client.ChaoYi.Until.Sys;

public class SettextActivity extends AppCompatActivity {
    public TextView title,content,user_name;
    public ImageView insert_image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settext);
        StatusBarUtil.setStatusBarDarkTheme(this,true);

        initview();
    }

    private void initview() {
        title = (TextView)findViewById(R.id.title);
        content = (TextView)findViewById(R.id.content_edit);
        user_name = (TextView)findViewById(R.id.content_name);
        insert_image = (ImageView)findViewById(R.id.insert_image);

        insert_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title_name = title.getText().toString();
                String content_ = content.getText().toString();
                String user_name_ = user_name.getText().toString();
            }
        });

        initdata();
    }

    private void initdata() {

    }

}
