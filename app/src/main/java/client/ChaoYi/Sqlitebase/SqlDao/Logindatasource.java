package client.ChaoYi.Sqlitebase.SqlDao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

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
    private SQLiteDatabase database;
    private SqlDbhelper sqlDbhelper;
    public Logindatasource(Context context) {
        this.context = context;
        sqlDbhelper = new SqlDbhelper(context);

    }


    @Override
    public String select(String... text) {

        return null;
    }

    @Override
    public void insert(ArrayList<?> list) {
        database= null;
        database = sqlDbhelper.getWritableDatabase();
        try {
            database.beginTransaction();
            ContentValues contentValues = new ContentValues();
            contentValues.put("username", "Mr.Wei");
            contentValues.put("password", "123456");
            database.insertOrThrow(sqlDbhelper.LoginTable, null, contentValues);
            database.setTransactionSuccessful();
//            return true;
        }catch (SQLiteConstraintException e){
            Log.e(TAG, "保存成功", e);
        }catch (Exception e){
            Log.e(TAG, "sql error", e);
        }finally {
            if (database != null) {
                database.endTransaction();
                database.close();
            }
        }
//        return false;
    }

    @Override
    public void updata(String text, ArrayList<?> list) {

    }

    @Override
    public void delete(String text) {

    }
}
