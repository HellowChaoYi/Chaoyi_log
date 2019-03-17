package client.ChaoYi.Sqlitebase.SqlDao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import client.ChaoYi.Model.Contenttable;
import client.ChaoYi.Model.Logintable;
import client.ChaoYi.Sqlitebase.Dbattribute.ExecuteSQL;
import client.ChaoYi.Sqlitebase.SqlDbhelper;

/**
 * Created by WCY on 2019/3/17.
 */

public class Contentsource implements ExecuteSQL {
    private static final String TAG = "Contentdao";
    private Context context;
    private SQLiteDatabase database;
    private SqlDbhelper sqlDbhelper;
    public Contentsource(Context context) {
        this.context = context;
        sqlDbhelper = new SqlDbhelper(context);
    }
    @Override
    public List<?> select(String id, String[] text) {
        List<Contenttable> content = new ArrayList<>();
        SQLiteDatabase db = sqlDbhelper.getReadableDatabase();
        Cursor cursor= db.query(sqlDbhelper.ContentTable, null, null, text, null, null, null);
        while(cursor.moveToNext()){
            Contenttable contenttable = new Contenttable();
            contenttable.setCt_name(cursor.getString(cursor.getColumnIndex("ct_name")));

            content.add(contenttable);
        }
        return content;
    }

    @Override
    public void insert(ArrayList<?> list) {
        database= null;
        database = sqlDbhelper.getWritableDatabase();
        try {
            database.beginTransaction();
            ContentValues contentValues = new ContentValues();
            contentValues.put("ct_content", "123456");
            contentValues.put("ct_name", "Mr.Wei");
            database.insertOrThrow(sqlDbhelper.ContentTable, null, contentValues);
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
    }

    @Override
    public void updata(String text, ArrayList<?> list) {

    }

    @Override
    public boolean delete(String text) {
        return false;
    }
}
