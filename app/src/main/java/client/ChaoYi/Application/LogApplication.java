package client.ChaoYi.Application;

import android.app.Application;

import java.util.ArrayList;

import client.ChaoYi.Model.Contenttable;
import client.ChaoYi.Sqlitebase.SqlDao.Contentsource;
import client.ChaoYi.Sqlitebase.SqlDao.Logindatasource;
import client.ChaoYi.Sqlitebase.SqlDbhelper;

/**
 * Created by WCY on 2019/2/19.
 */

public class LogApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ArrayList list = new ArrayList();
        Logindatasource.getLogindatasource(getApplicationContext()).insert(list);
    }
}
