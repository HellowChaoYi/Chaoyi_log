package client.ChaoYi.Activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import client.ChaoYi.Activity.Adapter.ListAdapter;
import client.ChaoYi.Model.Contenttable;
import client.ChaoYi.Model.Test;
import client.ChaoYi.R;
import client.ChaoYi.Sqlitebase.SqlDao.Contentsource;
import client.ChaoYi.Ui.CustomTitleBar;
import client.ChaoYi.Ui.until.StatusBarUtil;
import client.ChaoYi.Until.Sys;

public class ListActivity extends AppCompatActivity {
    private CustomTitleBar mcustomTitlebar;
    private List<Fragment> mFragments;

//    private Test[] cats = {new Test("布偶猫", R.mipmap.touxiang),
//            new Test("异国短毛猫", R.mipmap.touxiang),
//            new Test("波斯猫", R.mipmap.touxiang)};
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
        initcontent();
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
    }

    private void initCats() {

        contentlist.clear();

        Contentsource contentsource = new Contentsource(getApplicationContext());
        contentlist = (List<Contenttable>) contentsource.select("1",null);
        Sys.o("listactivity",String.valueOf(contentlist.size()));
//        for (int i = 0; i < 100; i++) {//随机添加 100 只猫的信息
//            Random random = new Random();
//            int index = random.nextInt(cats.length);
//            catList.add(cats[index]);
//        }
    }

}
