package client.ChaoYi.Application;

import android.app.Application;

import client.ChaoYi.Sqlitebase.SqlDao.Logindatasource;
import client.ChaoYi.Sqlitebase.SqlDbhelper;

/**
 * Created by WCY on 2019/2/19.
 */

public class LogApplication extends Application {
    private LogApplication mapplication;
    private SqlDbhelper sqldbhelper;
    private Logindatasource logindatasource;
    @Override
    public void onCreate() {
        super.onCreate();
        sqldbhelper = new SqlDbhelper(getApplicationContext());
        logindatasource = new Logindatasource(sqldbhelper);
    }
}
