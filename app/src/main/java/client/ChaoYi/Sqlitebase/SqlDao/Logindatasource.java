package client.ChaoYi.Sqlitebase.SqlDao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import client.ChaoYi.Model.Logintable;
import client.ChaoYi.Sqlitebase.Dbattribute.ExecuteSQL;
import client.ChaoYi.Sqlitebase.Dbattribute.insertsql;
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
    public List<?> selectwhere(String id, String[] text) {
        List<Logintable> arraylist = new ArrayList<>();
        Cursor cursor= database.query(sqlDbhelper.LoginTable, null, id+" = ?", text, null, null, null);
        while(cursor.moveToNext()){
            Logintable logintable = new Logintable();
            logintable.setUsername(cursor.getString(cursor.getColumnIndex("username")));
            logintable.setPassword(cursor.getString(cursor.getColumnIndex("password")));
            arraylist.add(logintable);
        }
        return arraylist;

    }

    @Override
    public void insert(Map<String, String> map) {
//        database= null;
//        database = sqlDbhelper.getWritableDatabase();
//        try {
            database.beginTransaction();
        try {
            insertsql.insert(map,database,sqlDbhelper.LoginTable);
        } catch (Exception e) {
            Log.e(TAG, "sql error", e);
        }
        database.setTransactionSuccessful();
        database.endTransaction();
//            return true;
//        }catch (SQLiteConstraintException e){
//            Log.e(TAG, "保存成功", e);
//        }catch (Exception e){
//            Log.e(TAG, "sql error", e);
//        }finally {
//            if (database != null) {
//                database.endTransaction();
//                database.close();
//            }
//        }
//        return false;
    }

    @Override
    public void updata(String text, ArrayList<?> list) {

    }

    @Override
    public boolean delete(String text) {
        return true;
    }
}
