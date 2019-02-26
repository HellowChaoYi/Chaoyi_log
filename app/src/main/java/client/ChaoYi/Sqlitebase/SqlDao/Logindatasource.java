package client.ChaoYi.Sqlitebase.SqlDao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import client.ChaoYi.Model.Logintable;
import client.ChaoYi.Sqlitebase.SqlDbhelper;

/**
 * Created by Administrator on 2019/2/25.
 */

public class Logindatasource {
    private static final String TAG = "Logindao";
    private SQLiteDatabase database;
    private SqlDbhelper sqlDbhelper;
    public Logindatasource(SqlDbhelper sqldbhelper) {
        sqldbhelper.getWritableDatabase();
    }
    public boolean insert(){
//        ContentValues values = new ContentValues();
        return true;
    }
    public boolean select(String password){
        Cursor cursor = database.query(sqlDbhelper.LoginTable, null, "password = ?", new String[]{password}, null, null, null);
        String user_name= "";
        if (cursor != null && cursor.getCount() > 0 && cursor.moveToFirst()) {
            user_name = cursor.getString(cursor.getColumnIndex(Logintable.username));
        }
        if(user_name!=null){
            return true;
        }else{
            database.close();
            cursor.close();
            return false;
        }
    }
}
