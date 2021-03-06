package client.ChaoYi.Activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import client.ChaoYi.Activity.Adapter.ListAdapter;
import client.ChaoYi.Activity.Adapter.ListViewHolder;
import client.ChaoYi.Activity.Adapter.OnItemClickListener;
import client.ChaoYi.Activity.Adapter.OnRecyclerItemClickListener;
import client.ChaoYi.Http.Okhttp;
import client.ChaoYi.Model.Contenttable;
import client.ChaoYi.Model.Logintable;
import client.ChaoYi.R;
import client.ChaoYi.Sqlitebase.Dbattribute.Selectsql;
import client.ChaoYi.Sqlitebase.SqlDao.Contentsource;
import client.ChaoYi.Ui.AlertDialog;
import client.ChaoYi.Ui.CommomDialog;
import client.ChaoYi.Ui.CustomTitleBar;
import client.ChaoYi.Ui.until.StatusBarUtil;
import client.ChaoYi.Until.Jsonuntil;
import client.ChaoYi.Until.Sys;

public class ListActivity extends AppCompatActivity {
    private static final String TAG ="ListActivity" ;
    private CustomTitleBar mcustomTitlebar;
    private List<Fragment> mFragments;
    private ListAdapter listAdapter;
    private RecyclerView recyclerView;
    public List<Contenttable> contentlist = new ArrayList<>();
    private long mExitTime = 0;
    private String title_name;
    public Context context = ListActivity.this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
//        mFragments = new ArrayList<>(1);
//        mFragments.add(ListFragment.newInstance("今日","a"));

        StatusBarUtil.setStatusBarColor(this,R.color.black);
        int height = StatusBarUtil.getStatusBarHeight(getApplicationContext());

        initview(height);
//        initCats();
        updateWeather();
     }
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
    /**
     * 加载内容
     */
    private void initcontent() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(layoutManager);
        mHandler.sendEmptyMessage(1);
        inilist();
    }

    private void inilist() {
        listAdapter = new ListAdapter(contentlist);
        recyclerView.setAdapter(listAdapter);
        recyclerView.addOnItemTouchListener(new OnRecyclerItemClickListener(recyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder viewHolder) {
                ListViewHolder viewHolder_1 = (ListViewHolder) viewHolder;
                title_name = viewHolder_1.title_name.getText().toString();
                Intent init = new Intent(ListActivity.this,SettextActivity.class);
                init.putExtra("ct_title",title_name);
                startActivity(init);
            }

            @Override
            public void onLongClick(RecyclerView.ViewHolder viewHolder) {
                ListViewHolder viewHolder_1 = (ListViewHolder) viewHolder;
                title_name = viewHolder_1.title_name.getText().toString();
                new CommomDialog(ListActivity.this, R.style.dialog, Stringvalue(R.string.delete), new CommomDialog.OnCloseListener() {
                    @Override
                    public void onClick(Dialog dialog, boolean confirm) {
                        if(confirm){
                            Contentsource contentsource = Contentsource.getContentsource(getApplicationContext());
                            contentsource.delete(
                                    new String[]{"ct_title"},
                                    new String[]{title_name});
                            updateWeather();
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


    /**
     *  加载标题栏
     * @param height
     */
    private void initview(int height) {
        mcustomTitlebar = findViewById(R.id.titlebar);

        mcustomTitlebar.setlocation(ListActivity.this,0,height,0,0);

        mcustomTitlebar.setLeftIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        mcustomTitlebar.setRightIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListActivity.this,SettextActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    /**
     * 获取本地数据
     */
    private void initCats() {
        contentlist.clear();
        contentlist = (List<Contenttable>) Contentsource.getContentsource(getApplicationContext()).select(new Contenttable());
//        Sys.o(TAG,String.valueOf(contentlist.size()));
//        for(int i=0;i<contentlist.size();i++){
//            Sys.o(TAG,contentlist.get(i).getCt_content());
//        }
//        Iterator<Map.Entry<String, String>> it=mmap.entrySet().iterator();
//        while(it.hasNext()) {
//            Map.Entry<String,String> entry=it.next();
//            String key=entry.getKey();
//            String value=entry.getValue();
//            contenttable.setCt_name(value);
//            System.out.println(key+" "+value);
//        }


        initcontent();
    }
    private void updateWeather() {

        new Thread(new Runnable(){
            @Override
            public void run() {
                //耗时操作，完成之后发送消息给Handler，完成UI更新；
                mHandler.sendEmptyMessage(0);

            }
        }).start();
    }
    @SuppressLint("HandlerLeak")
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    initCats();
                    break;
                case 1:
                    post_data();
                    break;
                default:
                    break;

            }
        }

    };


    private void post_data() {
        Okhttp oh = new Okhttp();
        oh.postdata(contentlist);
//        Sys.o(TAG,model.getClass().toString());
//        Field[] fields = model.getClass().getDeclaredFields();
//        for (Field field : fields) {
//            if (field.getType() == String.class) {
//                String key = field.getName();
//                field.setAccessible(true);
//                Sys.o(TAG,key);
//            }
//        }
//        for (int i=0;i<contentlist.size();i++){
//            Sys.o(TAG,contentlist.get(i).getClass().toString());
//        }
    }
}
