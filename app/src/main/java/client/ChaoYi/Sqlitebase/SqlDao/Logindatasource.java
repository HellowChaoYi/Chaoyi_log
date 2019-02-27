package client.ChaoYi.Sqlitebase.SqlDao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import client.ChaoYi.Model.Logintable;
import client.ChaoYi.Sqlitebase.SqlDbhelper;

/**
 * Created by Administrator on 2019/2/25.
 */

public class Logindatasource {
    private static final String TAG = "Logindao";
    private Context context;
    private SQLiteDatabase database;
    private SqlDbhelper sqlDbhelper;
    public Logindatasource(Context context) {
        this.context = context;
        sqlDbhelper = new SqlDbhelper(context);
    }
    public boolean insert(){
        database= null;
        try {
            database = sqlDbhelper.getWritableDatabase();
            database.beginTransaction();
            ContentValues contentValues = new ContentValues();
            contentValues.put("username", "Mr.wei");
            contentValues.put("password", "123456");

            database.insertOrThrow(sqlDbhelper.LoginTable, null, contentValues);
            database.setTransactionSuccessful();
            return true;
        }catch (SQLiteConstraintException e){
            Toast.makeText(context, "保存成功", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Log.e(TAG, "", e);
        }finally {
            if (database != null) {
                database.endTransaction();
                database.close();
            }
        }
        return false;
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
