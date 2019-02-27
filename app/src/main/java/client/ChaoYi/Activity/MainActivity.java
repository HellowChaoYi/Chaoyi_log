package client.ChaoYi.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import client.ChaoYi.Application.LogApplication;
import client.ChaoYi.Model.Logintable;
import client.ChaoYi.R;
import client.ChaoYi.Sqlitebase.SqlDao.Logindatasource;
import client.ChaoYi.Sqlitebase.SqlDbhelper;

/**
 * 项目练习--company
 */
public class MainActivity extends AppCompatActivity {
    public SqlDbhelper sqlDbhelper;
    public Button insert,select;
    public TextView textView;
    private SqlDbhelper sqldbhelper;
    private Logindatasource logindatasource;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        logindatasource = new Logindatasource(this);

        insert = (Button)findViewById(R.id.insert);
        select = (Button)findViewById(R.id.select);
        textView = (TextView)findViewById(R.id.text);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LogApplication.getDataSource().insert();
                textView.setText("success");
            }
        });
    }
}
