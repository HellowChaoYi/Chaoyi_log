package client.ChaoYi.Sqlitebase.Dbattribute;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import client.ChaoYi.Model.Contenttable;
import client.ChaoYi.Model.Logintable;
import client.ChaoYi.Until.Sys;

/**
 * Created by Administrator on 2019/4/10.
 */

public class Selectsql {
    public static void Select(SQLiteDatabase database, Class<?> modelclass, String tablename)throws SQLException{
        Map hashmap = new HashMap();
        Cursor cursor= database.query(tablename, null, null, null, null, null, null);
        while(cursor.moveToNext()){
            Field[] fields =modelclass.getDeclaredFields();
            for(Field field : fields ) {
                if (field.getType() == String.class) {
//                    Sys.o("TEST",field.getName());
                    String key = field.getName();
                    hashmap.put(key,cursor.getString(cursor.getColumnIndex(key)));
                }
            }
        }
    }
    public static Map<String,String> Selectwhere(SQLiteDatabase database, Class<?> modelclass, String tablename, String id, String[] text) throws SQLException{
//        List list = new ArrayList();
        Map hashmap = new HashMap();
        Cursor cursor= database.query(tablename, null, id + "= ?", text, null, null, null);
        while(cursor.moveToNext()){
            Field[] fields =modelclass.getDeclaredFields();
            for(Field field : fields ) {
                if (field.getType() == String.class) {
//                    Sys.o("TEST",field.getName());
                    String key = field.getName();
                    hashmap.put(key,cursor.getString(cursor.getColumnIndex(key)));
                }
            }
        }
        return hashmap;
    }
}
