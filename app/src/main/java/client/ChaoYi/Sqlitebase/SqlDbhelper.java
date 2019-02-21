package client.ChaoYi.Sqlitebase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.lang.reflect.Field;

import client.ChaoYi.Model.Logintable;
import client.ChaoYi.Sqlitebase.Dbattribute.Attribute;

/**
 * Created by WCY on 2019/2/19.
 */

public class SqlDbhelper extends SQLiteOpenHelper{
    private volatile static SqlDbhelper sqlDbhelper;
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "Mysql.db";

//    private Class<Logintable> logintable ;
    public SqlDbhelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        AutoStringsql(db, Logintable.class);
    }

    private void AutoStringsql(SQLiteDatabase db, Class<?> modelclass) {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append("create table if not exists ");
        stringbuffer.append(Logintable.class.getSimpleName()+"(");
        Field[] fields =Logintable.class.getDeclaredFields();
        for(Field field : fields ) {
            Attribute attribute = field.getAnnotation(Attribute.class);
            if(field.getType()== String.class) {
                stringbuffer.append(field.getName()+attribute.value()+" varchar,");
                System.out.println(attribute.value());
            }else if (field.getType() == Integer.class){
                stringbuffer.append(field.getName()+attribute.value()+" Integer,");
            }
        }
        stringbuffer.setCharAt(stringbuffer.length()-1, ')');
        System.out.println(stringbuffer.toString());
        db.execSQL(stringbuffer.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Autoonupsql(db,Logintable.class);
        onCreate(db);
    }

    private void Autoonupsql(SQLiteDatabase db, Class<?> modelclass){
        String sql = "DROP TABLE IF EXISTS " + modelclass.getSimpleName();
        db.execSQL(sql);
    }

//    public static synchronized SqlDbhelper getInstance(Context applicationContext) {
//        if (sqlDbhelper == null) {
//            synchronized (SqlDbhelper.class) {
//                if (sqlDbhelper == null) {
//                    sqlDbhelper = new SqlDbhelper(applicationContext);
//                }
//            }
//        }
//
//        return sqlDbhelper;
//    }
}
