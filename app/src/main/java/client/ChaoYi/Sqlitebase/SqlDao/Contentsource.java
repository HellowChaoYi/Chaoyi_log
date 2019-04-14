package client.ChaoYi.Sqlitebase.SqlDao;

import android.content.Context;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import client.ChaoYi.Model.Contenttable;
import client.ChaoYi.Sqlitebase.Dbattribute.Deletesql;
import client.ChaoYi.Sqlitebase.Dbattribute.ExecuteSQL;
import client.ChaoYi.Sqlitebase.Dbattribute.Insertsql;
import client.ChaoYi.Sqlitebase.Dbattribute.Selectsql;
import client.ChaoYi.Sqlitebase.Dbattribute.Updatesql;
import client.ChaoYi.Sqlitebase.SqlDbhelper;

/**
 * Created by WCY on 2019/3/17.
 */

public class Contentsource implements ExecuteSQL {
    private static final String TAG = "Contentdao";
    private static Contentsource contentsource ;
    private Context context;
    private SQLiteDatabase database;
    private SqlDbhelper sqlDbhelper;
    private Class<?> modelclass=Contenttable.class;
    public Contentsource(Context context) {
        this.context = context;
        sqlDbhelper = new SqlDbhelper(context);
        database = sqlDbhelper.getReadableDatabase();
    }
    public static Contentsource getContentsource(Context context){
        if (contentsource == null) {
            synchronized (Contentsource.class) {
                contentsource = new Contentsource(context);
            }
        }
        return contentsource;
    }
    @Override
    public List<?> select(Object modelclass) {
        List<?> list = new ArrayList<>();
        try{
            list = Selectsql.Select(database,modelclass,sqlDbhelper.ContentTable);
        }catch (Exception e){
            Log.e(TAG,"error",e);
        }
        return list;
    }

    @Override
    public List<?> selectwhere(Object modelclass) {
        return null;
    }

    @Override
    public Map<String,String> selectwhere(String id, String[] text) {
        Map map = new HashMap();
        try{
            map= Selectsql.Selectwhere(database,modelclass,sqlDbhelper.ContentTable,id,text);
        }catch (Exception e){
            Log.e(TAG,"error",e);
        }
        return map;
    }

    @Override
    public void insert(Map<String, String> map) {
        database.beginTransaction();
        try {
            Insertsql.insert(database,sqlDbhelper.ContentTable,map);
            database.setTransactionSuccessful();
        }catch (SQLiteConstraintException e){
            Log.e(TAG, "保存成功", e);
        }catch (Exception e){
            Log.e(TAG, "sql error", e);
        }finally {
            database.endTransaction();
        }
    }

    @Override
    public void updata(Map<String, String> map, String[] id, String[] text) {
        try {
            Updatesql.update(database,sqlDbhelper.ContentTable,map,id,text);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updata(String text, ArrayList<?> list) {

    }


    @Override
    public boolean delete(String[] id,String[] text) {
        try {
            int i = Deletesql.delete(database,sqlDbhelper.ContentTable);
            if(i==0){
                return false;
            }else {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
