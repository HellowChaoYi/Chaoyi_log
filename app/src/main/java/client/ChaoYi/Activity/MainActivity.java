package client.ChaoYi.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.lang.reflect.Field;

import client.ChaoYi.Model.Logintable;
import client.ChaoYi.R;
import client.ChaoYi.Sqlitebase.Dbattribute.Attribute;
import client.ChaoYi.Sqlitebase.SqlDbhelper;

/**
 * 项目练习--company
 */
public class MainActivity extends AppCompatActivity {
    public SqlDbhelper sqlDbhelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
