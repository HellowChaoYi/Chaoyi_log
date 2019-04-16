package client.ChaoYi.Activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import client.ChaoYi.Model.Contenttable;
import client.ChaoYi.Model.Logintable;
import client.ChaoYi.R;
import client.ChaoYi.Sqlitebase.SqlDao.Contentsource;
import client.ChaoYi.Sqlitebase.SqlDao.Logindatasource;
import client.ChaoYi.Ui.CommomDialog;
import client.ChaoYi.Ui.until.StatusBarUtil;
import client.ChaoYi.Until.Sys;

public class SettextActivity extends AppCompatActivity {
    public static String TAG = "SettextActivity";
    public TextView data_time,user_name;
    public EditText content,title;
    public ImageView insert_image;
    public String update="0";
    public long mExitTime = 0;
    public Context context = SettextActivity.this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settext);
        StatusBarUtil.setStatusBarDarkTheme(this,true);

        initview();
    }

    private void initview() {
        Intent init =getIntent();
        String ct_title = init.getStringExtra("ct_title");
        data_time = (TextView)findViewById(R.id.content_time);
        user_name = (TextView)findViewById(R.id.content_name);
        Sys.o(TAG,user_name.getText().toString());
        content = (EditText)findViewById(R.id.content_edit);
        title = (EditText)findViewById(R.id.title);
        insert_image = (ImageView)findViewById(R.id.insert_image);
        data_time.setText(Sys.getNow_Time());
        //0: 默认插入 1：默认修改

        initdata(ct_title);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN&&event.getRepeatCount() == 0) {
            startintent();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    private void initdata(String ct_title) {
        if(ct_title!=null){
           Contentsource contentsource = Contentsource.getContentsource(getApplicationContext());
           Map<String,String> map = contentsource.selectwhere("ct_title",new String[]{ct_title});
           for(Map.Entry<String, String> entry:map.entrySet()){
                Sys.o(TAG,entry.getValue());
                if(entry.getKey().equals("ct_title")){
                    title.setText(entry.getValue());
                }
                if(entry.getKey().equals("ct_content")){
                    content.setText(entry.getValue());
                }
           }
        }
        if (!title.getText().toString().equals("")) {
            update = "1";
        }
        initsetOnclick();
    }

    private void initsetOnclick() {
        Sys.o(TAG,update);
        insert_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String,String> map = new HashMap();
                Contenttable contenttable = new Contenttable();
                contenttable.setCt_title(title.getText().toString());
                contenttable.setCt_content(content.getText().toString());
                contenttable.setCt_name(user_name.getText().toString());
                contenttable.setCt_data(data_time.getText().toString());
                try {
                    map = Sys.modeltomap(contenttable);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Contentsource contentsource = Contentsource.getContentsource(getApplicationContext());
                if(update=="1"){
                    contentsource.updata(
                            map,
                            new String[]{"ct_title"},
                            new String[]{title.getText().toString()});

                }else{
                    contentsource.insert(map);
                }
                new CommomDialog(SettextActivity.this, R.style.dialog, Stringvalue(R.string.save), new CommomDialog.OnCloseListener() {
                    @Override
                    public void onClick(Dialog dialog, boolean confirm) {
                        if(confirm){
                            startintent();
                            dialog.dismiss();
                        }
                    }

                }).setTitle(Stringvalue(R.string.tishi)).show();

            }
        });
    }

    private String Stringvalue(int Rstring) {
        return (String)context.getResources().getText(Rstring);
    }


    private void startintent() {
        Intent intent = new Intent(SettextActivity.this,ListActivity.class);
        startActivity(intent);
        finish();
    }
}
