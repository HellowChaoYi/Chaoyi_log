package client.ChaoYi.Application;

import android.app.Application;

import java.util.ArrayList;
import java.util.Map;

import client.ChaoYi.Model.Logintable;
import client.ChaoYi.Sqlitebase.SqlDao.Logindatasource;
import client.ChaoYi.Until.Jsonuntil;

/**
 * Created by WCY on 2019/2/19.
 */

public class LogApplication extends Application {
    public static String TAG = "LogApplication";
    @Override
    public void onCreate() {
        super.onCreate();
        ArrayList list = new ArrayList();
//        Log.i(TAG, Jsonuntil.getJson(getApplicationContext(), "login.json"));
        String json =Jsonuntil.getJson(getApplicationContext(),"login.json");
        Map<String, String> map = Jsonuntil.jsontomap(json);
//        Logindatasource.getLogindatasource(getApplicationContext()).insert(map);
    }
}
