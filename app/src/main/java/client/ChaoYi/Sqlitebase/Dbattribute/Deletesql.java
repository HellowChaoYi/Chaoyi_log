package client.ChaoYi.Sqlitebase.Dbattribute;

import android.database.sqlite.SQLiteDatabase;

import client.ChaoYi.Until.Sqluntil;

/**
 * Created by WCY on 2019/4/14.
 */

public class Deletesql {
    public static int delete(SQLiteDatabase database,String Tablename)throws Exception{
        int i =database.delete(Tablename, null, null);
        return i;
    }
    public static int deletewhere(SQLiteDatabase database,String Tablename,String[] setWhere ,String[] wherearg)throws Exception{
        int i =database.delete(Tablename, Sqluntil.setWhere(setWhere), wherearg);
        return i;
    }
}
