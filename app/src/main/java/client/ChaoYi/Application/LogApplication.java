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
    private LogApplication mapplication;
    private SqlDbhelper sqldbhelper;
    private static Logindatasource logindatasource;
    private static Contentsource contentsource;
    @Override
    public void onCreate() {
        super.onCreate();
        sqldbhelper = new SqlDbhelper(getApplicationContext());
        logindatasource = new Logindatasource(getApplicationContext());
        contentsource = new Contentsource(getApplicationContext());
        ArrayList list = new ArrayList();
        logindatasource.insert(list);
        contentsource.insert(list);
    }
    public static Logindatasource getDataSource() {
        return logindatasource;
    }
}
