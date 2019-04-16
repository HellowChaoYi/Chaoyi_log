package client.ChaoYi.Activity;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import client.ChaoYi.Model.Contenttable;
import client.ChaoYi.R;
import client.ChaoYi.Sqlitebase.SqlDao.Logindatasource;

import client.ChaoYi.Ui.AlertDialog;
import client.ChaoYi.Ui.until.StatusBarUtil;
import client.ChaoYi.Until.GetVercode;
import client.ChaoYi.Until.Sys;

/**
 * 项目练习--company
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    public ImageView image ;
    public TextView name,vercode;
    public EditText passedit;
    private long mExitTime = 0;
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
//                    Log.i(TAG,passedit.getText().toString());
                    login(new String[]{passedit.getText().toString()});
                    return true;
                }
                return false;
            }
        });


    }
//    public List<String > getClassName(String packageName){
//        List<String >classNameList=new ArrayList<String >();
//        try {
//
//            DexFile df = new DexFile(this.getPackageCodePath());//通过DexFile查找当前的APK中可执行文件
//            Enumeration<String> enumeration = df.entries();//获取df中的元素  这里包含了所有可执行的类名 该类名包含了包名+类名的方式
//            while (enumeration.hasMoreElements()) {//遍历
//                String className = (String) enumeration.nextElement();
//
//                if (className.contains(packageName)) {//在当前所有可执行的类里面查找包含有该包名的所有类
//                    classNameList.add(className);
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return  classNameList;
//    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN&&event.getRepeatCount() == 0) {
            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    public void exit() {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
            mExitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }
    private void login(String[] text) {
        Log.i(TAG,"login");
        Logindatasource logindatasource = Logindatasource.getLogindatasource(getApplicationContext());
        Map map = logindatasource.selectwhere("password",text);
        if(!map.isEmpty()){
            Intent intent = new Intent(MainActivity.this,ListActivity.class);
            startActivity(intent);
            finish();
        }else{
            Toast.makeText(getApplicationContext(),"登录错误，请检查密码", Toast.LENGTH_SHORT).show();
        }
    }

    public void setbackground(){
        StatusBarUtil.setRootViewFitsSystemWindows(this,true);
        //设置状态栏透明
        StatusBarUtil.setTranslucentStatus(this);
        //一般的手机的状态栏文字和图标都是白色的, 可如果你的应用也是纯白色的, 或导致状态栏文字看不清
        //所以如果你是这种情况,请使用以下代码, 设置状态使用深色文字图标风格, 否则你可以选择性注释掉这个if内容
//        if (!StatusBarUtil.setStatusBarDarkTheme(this, true)) {
//            //如果不支持设置深色风格 为了兼容总不能让状态栏白白的看不清, 于是设置一个状态栏颜色为半透明,
//            //这样半透明+白=灰, 状态栏的文字能看得清
//            StatusBarUtil.setStatusBarColor(this,0x55000000);
//        }else{
        StatusBarUtil.setStatusBarDarkTheme(this, false);

    }

}
