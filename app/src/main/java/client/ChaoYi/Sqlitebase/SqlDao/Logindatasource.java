package client.ChaoYi.Sqlitebase.SqlDao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import client.ChaoYi.Model.Logintable;
import client.ChaoYi.Sqlitebase.Dbattribute.ExecuteSQL;
import client.ChaoYi.Sqlitebase.Dbattribute.Selectsql;
import client.ChaoYi.Sqlitebase.Dbattribute.Insertsql;
import client.ChaoYi.Sqlitebase.SqlDbhelper;

/**
 * Created by Administrator on 2019/2/25.
 */

public class Logindatasource implements ExecuteSQL {
    private static final String TAG = "Logindao";
    private Context context;
    private static Logindatasource logindatasource;
    private SQLiteDatabase database;
    private SqlDbhelper sqlDbhelper;
    private Class<?> modelclass=Logintable.class;
    public Logindatasource(Context context) {
        this.context = context;
        sqlDbhelper = new SqlDbhelper(context);
        database = sqlDbhelper.getWritableDatabase();
    }
    public static Logindatasource getLogindatasource(Context context){
        if (logindatasource == null) {
            synchronized (Logindatasource.class) {
                logindatasource = new Logindatasource(context);
            }
        }
        return logindatasource;
    }
    @Override
    public List<?> select() {
        return null;
    }

    @Override
    public Map selectwhere(String id, String[] text) {
        Map map = new HashMap();
        try{
            map=Selectsql.Selectwhere(database,modelclass,sqlDbhelper.LoginTable,id,text);
        }catch (Exception e){
            Log.e(TAG,"error",e);
        }
        return map;
    }

    @Override
    public void insert(Map<String, String> map) {
        database.beginTransaction();
        try {
            Insertsql.insert(map,database,sqlDbhelper.LoginTable);
        } catch (Exception e) {
            Log.e(TAG, "sql error", e);
        }
        database.setTransactionSuccessful();
        database.endTransaction();
    }

    @Override
    public void updata(String text, ArrayList<?> list) {

    }

    @Override
    public boolean delete(String text) {
        return true;
    }
}
