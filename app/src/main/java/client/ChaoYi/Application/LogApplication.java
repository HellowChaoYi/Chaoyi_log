package client.ChaoYi.Application;

import android.app.Application;
import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import net.sf.json.JSON;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import client.ChaoYi.Model.Contenttable;
import client.ChaoYi.Sqlitebase.SqlDao.Contentsource;
import client.ChaoYi.Sqlitebase.SqlDao.Logindatasource;
import client.ChaoYi.Sqlitebase.SqlDbhelper;
import client.ChaoYi.Until.Jsonuntil;
import client.ChaoYi.Until.Sys;

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
        Logindatasource.getLogindatasource(getApplicationContext()).insert(map);
    }


}
