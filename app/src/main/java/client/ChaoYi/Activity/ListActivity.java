package client.ChaoYi.Activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import client.ChaoYi.Activity.Adapter.ListAdapter;
import client.ChaoYi.Activity.Adapter.OnItemClickListener;
import client.ChaoYi.Model.Contenttable;
import client.ChaoYi.Model.Logintable;
import client.ChaoYi.R;
import client.ChaoYi.Sqlitebase.Dbattribute.Selectsql;
import client.ChaoYi.Sqlitebase.SqlDao.Contentsource;
import client.ChaoYi.Ui.CustomTitleBar;
import client.ChaoYi.Ui.until.StatusBarUtil;
import client.ChaoYi.Until.Sys;

public class ListActivity extends AppCompatActivity {
    private static final String TAG ="ListActivity" ;
    private CustomTitleBar mcustomTitlebar;
    private List<Fragment> mFragments;
    private ListAdapter listAdapter;
    private RecyclerView recyclerView;
    public List<Contenttable> contentlist = new ArrayList<>();


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
//        mFragments = new ArrayList<>(1);
//        mFragments.add(ListFragment.newInstance("今日","a"));

        StatusBarUtil.setStatusBarColor(this,R.color.black);
        int height = StatusBarUtil.getStatusBarHeight(getApplicationContext());
        initview(height);
        initCats();

     }

    /**
     * 加载内容
     */
    private void initcontent() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(layoutManager);
        inilist();
    }

    private void inilist() {
        listAdapter = new ListAdapter(contentlist);
        recyclerView.setAdapter(listAdapter);
        listAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                Intent init = new Intent(ListActivity.this,SettextActivity.class);
                startActivity(init);
            }
        });
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
        List<Contenttable> mmasp = (List<Contenttable>) Contentsource.getContentsource(getApplicationContext()).select(Contenttable.class);
//        for (int i =0;i<mmasp.size();i++){
//            Sys.o(TAG,mmasp.get(i).getCt_content());
//        }
        Sys.o(TAG,String.valueOf(mmasp.size()));

//        Iterator<Map.Entry<String, String>> it=mmap.entrySet().iterator();
//        while(it.hasNext()) {
//            Map.Entry<String,String> entry=it.next();
//            String key=entry.getKey();
//            String value=entry.getValue();
//            contenttable.setCt_name(value);
//            System.out.println(key+" "+value);
//        }

//        Contentsource contentsource = new Contentsource(getApplicationContext());
//        contentlist = (List<Contenttable>) contentsource.selectwhere(Logintable.class, "1",null);
//        Sys.o("listactivity",String.valueOf(contentlist.size()));
        initcontent();
    }

}
