package client.ChaoYi.Sqlitebase.SqlDao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import client.ChaoYi.Model.Logintable;
import client.ChaoYi.Sqlitebase.Dbattribute.ExecuteSQL;
import client.ChaoYi.Sqlitebase.Dbattribute.Selectsql;
import client.ChaoYi.Sqlitebase.Dbattribute.insertsql;
import client.ChaoYi.Sqlitebase.SqlDbhelper;

/**
 * Created by Administrator on 2019/2/25.
 */

public class Logindatasource<T> implements ExecuteSQL {
    private static final String TAG = "Logindao";
    private Context context;
    private static Logindatasource logindatasource;
    private SQLiteDatabase database;
    private SqlDbhelper sqlDbhelper;
    Class<T> modelclass;
    private Logintable logintable = new Logintable();
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
    public List<?> selectwhere(Class<Logintable> logintableClass, String id, String[] text) {

        try{
            List<?> list=Selectsql.Selectwhere(database,logintableClass,sqlDbhelper.LoginTable,id,text);
        }catch (Exception e){
            Log.e(TAG,"error",e);
        }

        return null;

    }

    @Override
    public void insert(Map<String, String> map) {
        database.beginTransaction();
        try {
            insertsql.insert(map,database,sqlDbhelper.LoginTable);
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
