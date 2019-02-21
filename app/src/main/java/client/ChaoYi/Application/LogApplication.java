package client.ChaoYi.Application;

import android.app.Application;
import android.content.Context;

import client.ChaoYi.Sqlitebase.SqlDbhelper;

/**
 * Created by WCY on 2019/2/19.
 */

public class LogApplication extends Application {
    private LogApplication mapplication;
    private SqlDbhelper sqldbhelper;
    @Override
    public void onCreate() {
        super.onCreate();
//        mapplication = this;
//        sqldbhelper = SqlDbhelper.getInstance(getApplicationContext());

    }
}
