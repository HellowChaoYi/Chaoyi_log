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
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "Mysql.db";
    private Class<Logintable> logintable ;
    public SqlDbhelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        AutoStringsql(db, logintable);
    }

    private void AutoStringsql(SQLiteDatabase db, Class<?> modelclass) {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append("create table if not exists ");
        stringbuffer.append(modelclass.getSimpleName()+"(");
        Field[] fields =modelclass.getDeclaredFields();
        for(Field field : fields ) {
            Attribute attribute = field.getAnnotation(Attribute.class);
            if(field.getType()== String.class) {
                stringbuffer.append(field.getName()+attribute.value()+" varchar,");

                System.out.println(field.getName());
            }else if (field.getType() == Integer.class){
                stringbuffer.append(field.getName()+attribute.value()+" Integer,");
                System.out.println(field.getName());
            }
        }
        stringbuffer.setCharAt(stringbuffer.length()-1, ')');
        db.execSQL(stringbuffer.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Autoonupsql(db,logintable);
        onCreate(db);
    }

    private void Autoonupsql(SQLiteDatabase db, Class<?> modelclass){
        String sql = "DROP TABLE IF EXISTS " + modelclass.getSimpleName();
        db.execSQL(sql);
    }
}
