package client.ChaoYi.Sqlitebase.Dbattribute;

import android.content.ContentValues;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;
import java.util.Map;

import client.ChaoYi.Until.Sqluntil;

/**
 * Created by WCY on 2019/4/14.
 */

public class Updatesql {
    public static void update(SQLiteDatabase database, String Tablename, Map<String, String> map, String[] id, String[] text) throws Exception{
        ContentValues contentValues = new ContentValues();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            contentValues.put(entry.getKey(), entry.getValue());
        }
        database.update(Tablename, contentValues, Sqluntil.setWhere(id),text);
    }
    public static void update(SQLiteDatabase database, List<?> list, String Tablename, String[] id, String[] text)throws Exception{

    }
}
