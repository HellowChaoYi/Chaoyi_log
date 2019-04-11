package client.ChaoYi.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import client.ChaoYi.Model.Contenttable;
import client.ChaoYi.Model.Logintable;
import client.ChaoYi.R;
import client.ChaoYi.Sqlitebase.SqlDao.Contentsource;
import client.ChaoYi.Sqlitebase.SqlDao.Logindatasource;
import client.ChaoYi.Ui.until.StatusBarUtil;
import client.ChaoYi.Until.Sys;

public class SettextActivity extends AppCompatActivity {
    public static String TAG = "SettextActivity";
    public TextView data_time,user_name;
    public EditText content,title;
    public ImageView insert_image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settext);
        StatusBarUtil.setStatusBarDarkTheme(this,true);

        initview();
    }

    private void initview() {
        data_time = (TextView)findViewById(R.id.content_time);
        user_name = (TextView)findViewById(R.id.content_name);
        content = (EditText)findViewById(R.id.content_edit);
        title = (EditText)findViewById(R.id.title);
        insert_image = (ImageView)findViewById(R.id.insert_image);
        data_time.setText(Sys.getNow_Time());
        insert_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String,String> map = new HashMap();
                Contenttable contenttable = new Contenttable();
                contenttable.setCt_title(title.getText().toString());
                contenttable.setCt_content(content.getText().toString());
                contenttable.setCt_name(user_name.getText().toString());
                try {
                    map = Sys.modeltomap(contenttable);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Contentsource.getContentsource(getApplicationContext()).insert(map);
                startintent();
            }
        });
        initdata();
    }

    private void startintent() {
        Intent intent = new Intent(SettextActivity.this,ListActivity.class);
        startActivity(intent);
        finish();
    }

    private void initdata() {

    }

}
