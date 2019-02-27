package client.ChaoYi.Activity;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import client.ChaoYi.R;
import client.ChaoYi.Sqlitebase.SqlDao.Logindatasource;
import client.ChaoYi.Sqlitebase.SqlDbhelper;
import client.ChaoYi.Ui.until.StatusBarUtil;
import client.ChaoYi.Until.GetVercode;
import client.ChaoYi.Until.Sys;

/**
 * 项目练习--company
 */
public class MainActivity extends AppCompatActivity {
    public ImageView image ;
    public TextView name,vercode;
    public EditText passedit;
    public Logindatasource logindatasource;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = (TextView)findViewById(R.id.name);
        vercode = (TextView)findViewById(R.id.vercode);
        passedit = (EditText) findViewById(R.id.pass_edit);
        image = (ImageView)findViewById(R.id.imageView);
        setbackground();
        vercode.setText(GetVercode.out(getApplicationContext()));

        //键盘回车键监听
        passedit.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_UP) {
                    login(passedit.getText().toString());
                    return true;
                }
                return false;
            }
        });
    }
    private void login(String text) {
        logindatasource.select(text);
    }

    public void setbackground(){
        StatusBarUtil.setRootViewFitsSystemWindows(this,true);
        //设置状态栏透明
        StatusBarUtil.setTranslucentStatus(this);
        //一般的手机的状态栏文字和图标都是白色的, 可如果你的应用也是纯白色的, 或导致状态栏文字看不清
        //所以如果你是这种情况,请使用以下代码, 设置状态使用深色文字图标风格, 否则你可以选择性注释掉这个if内容
        if (!StatusBarUtil.setStatusBarDarkTheme(this, true)) {
            //如果不支持设置深色风格 为了兼容总不能让状态栏白白的看不清, 于是设置一个状态栏颜色为半透明,
            //这样半透明+白=灰, 状态栏的文字能看得清
            StatusBarUtil.setStatusBarColor(this,0x55000000);
        }
    }

}
