package client.ChaoYi.Sqlitebase.Dbattribute;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by WCY on 2019/3/7.
 */

public class Insertsql {
    public static void insert(SQLiteDatabase database, String Tablename,Map<String, String> map) throws  SQLException{
        ContentValues contentValues = new ContentValues();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            contentValues.put(entry.getKey(), entry.getValue());
//                contentValues.put("password", "123456");
        }
        database.insertOrThrow(Tablename, null, contentValues);
    }
}
