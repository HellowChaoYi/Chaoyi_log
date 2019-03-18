package client.ChaoYi.Activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import client.ChaoYi.Activity.Adapter.ListAdapter;
import client.ChaoYi.Model.Contenttable;
import client.ChaoYi.R;
import client.ChaoYi.Sqlitebase.SqlDao.Contentsource;
import client.ChaoYi.Ui.CustomTitleBar;
import client.ChaoYi.Ui.until.StatusBarUtil;
import client.ChaoYi.Until.Sys;

public class ListActivity extends AppCompatActivity {
    private CustomTitleBar mcustomTitlebar;
    private List<Fragment> mFragments;

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
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(layoutManager);
        ListAdapter listAdapter = new ListAdapter(contentlist);
        recyclerView.setAdapter(listAdapter);
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
            }
        });
        mcustomTitlebar.setRightIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListActivity.this,SettextActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * 获取本地数据
     */
    private void initCats() {

        contentlist.clear();

        Contentsource contentsource = new Contentsource(getApplicationContext());
        contentlist = (List<Contenttable>) contentsource.select("1",null);
        Sys.o("listactivity",String.valueOf(contentlist.size()));

        initcontent();
    }

}