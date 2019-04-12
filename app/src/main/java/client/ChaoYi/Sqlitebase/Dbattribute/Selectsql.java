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

/**
 * Created by Administrator on 2019/4/10.
 */

public class Selectsql {
    public static List<?> Select(SQLiteDatabase database, Class<?> modelclass, String tablename) throws SecurityException,
            NoSuchMethodException, IllegalArgumentException,
            IllegalAccessException, InvocationTargetException, InstantiationException{

        List<Class<?>> list = new ArrayList();
        Cursor cursor= database.query(tablename, null, null, null, null, null, null);
        while(cursor.moveToNext()) {
            Field[] fields = modelclass.getDeclaredFields();
            for (Field field : fields) {
                if (field.getType() == String.class) {
//                    Sys.o("TEST",field.getName());
                    String key = field.getName();
                    String name = key.replaceFirst(key.substring(0, 1), key.substring(0, 1)
                            .toUpperCase());
                    field.setAccessible(true);
                    try {
                        field.set(modelclass, cursor.getString(cursor.getColumnIndex(key)));//field.getType().getConstructor(field.getType()).newInstance("kou")
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    // 调用getter方法获取属性值

                }
            }
            list.add(modelclass);
        }

        return list;
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
