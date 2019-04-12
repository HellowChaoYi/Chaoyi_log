package client.ChaoYi.Sqlitebase.SqlDao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import client.ChaoYi.Model.Contenttable;
import client.ChaoYi.Model.Logintable;
import client.ChaoYi.Sqlitebase.Dbattribute.ExecuteSQL;
import client.ChaoYi.Sqlitebase.Dbattribute.Insertsql;
import client.ChaoYi.Sqlitebase.Dbattribute.Selectsql;
import client.ChaoYi.Sqlitebase.SqlDbhelper;
import client.ChaoYi.Until.Sys;

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
    public List<?> select(Class<?> modelclass_1) {
        List<?> list = new ArrayList<>();
        try{
            list = Selectsql.Select(database,modelclass_1,sqlDbhelper.ContentTable);
        }catch (Exception e){
            Log.e(TAG,"error",e);
        }
        return list;
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

//        List<Contenttable> content = new ArrayList<>();
//        List<?> list = Selectsql.Selectwhere(database,contenttable,sqlDbhelper.ContentTable,id,text);
//        Cursor cursor= database.query(sqlDbhelper.ContentTable, null, null, text, null, null, null);
//        while(cursor.moveToNext()){
//            Contenttable contenttable = new Contenttable();
//            contenttable.setCt_name(cursor.getString(cursor.getColumnIndex("ct_name")));
//            content.add(contenttable);
//        }
//        return null;

    }

    @Override
    public void insert(Map<String, String> map) {
        database.beginTransaction();
        try {
            Insertsql.insert(map,database,sqlDbhelper.ContentTable);
        }catch (SQLiteConstraintException e){
            Log.e(TAG, "保存成功", e);
        }catch (Exception e){
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
        return false;
    }
}
