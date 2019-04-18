package client.ChaoYi.Sqlitebase.Dbattribute;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import client.ChaoYi.Until.Sqluntil;
import client.ChaoYi.Until.Sys;

/**
 * Created by Administrator on 2019/4/10.
 */

public class Selectsql {
    public static List<?> Select(SQLiteDatabase database, Object modelclass, String tablename) throws SecurityException,
            NoSuchMethodException, IllegalArgumentException,
            IllegalAccessException, InvocationTargetException, InstantiationException{
        List<Object> list = new ArrayList();
        Object obj;
        Cursor cursor= database.query(tablename, null, null, null, null, null, null);
        while(cursor.moveToNext()) {
            obj = modelclass.getClass().newInstance();
            Field[] fields = modelclass.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (field.getType() == String.class) {
                    String key = field.getName();
                    field.setAccessible(true);
                    try {
                        field.set(obj, cursor.getString(cursor.getColumnIndex(key)));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else if (field.getType() == Integer.class) {
                    String key = field.getName();
                    field.setAccessible(true);
                    try {
                        field.set(obj, cursor.getInt(cursor.getColumnIndex(key)));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            list.add(obj);
        }
        return list;
    }
    public static List<?> Selectwhere(SQLiteDatabase database, Object modelclass, String tablename,String[] id, String[] text) throws SecurityException,
            NoSuchMethodException, IllegalArgumentException,
            IllegalAccessException, InvocationTargetException, InstantiationException{
        List<Object> list = new ArrayList();
        Object obj;
        String selection = Sqluntil.setWhere(id);
        Cursor cursor= database.query(tablename, null, selection, text, null, null, null);
        while(cursor.moveToNext()) {
            obj = modelclass.getClass().newInstance();
            Field[] fields = modelclass.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (field.getType() == String.class) {
                    String key = field.getName();
                    field.setAccessible(true);
                    try {
                        field.set(obj, cursor.getString(cursor.getColumnIndex(key)));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else  if (field.getType() == Integer.class) {
                    String key = field.getName();
                    field.setAccessible(true);
                    try {
                        field.set(obj, cursor.getInt(cursor.getColumnIndex(key)));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            list.add(obj);
        }
        return list;
    }



    public static Map<String,String> Selectwhere(SQLiteDatabase database, Class<?> modelclass, String tablename, String id, String[] text) throws SQLException{
        Map hashmap = new HashMap();
        Cursor cursor= database.query(tablename, null, id + "= ?", text, null, null, null);
        while(cursor.moveToNext()){
            Field[] fields =modelclass.getDeclaredFields();
            for(Field field : fields ) {
                if (field.getType() == String.class) {
                    String key = field.getName();
                    hashmap.put(key,cursor.getString(cursor.getColumnIndex(key)));
                }
            }
        }
        return hashmap;
    }
}
