package client.ChaoYi.Sqlitebase.Dbattribute;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import client.ChaoYi.Model.Contenttable;

/**
 * Created by WCY on 2019/3/7.
 */

public interface ExecuteSQL {
    public List<?> select(Object modelclass);
    public List<?> selectwhere(Object modelclass);
    public Map selectwhere(String id, String[] text);
    public void insert(Map<String, String> list);
    public void updata(Map<String,String> map,String[] id, String[] text);
    public void updata(String text,ArrayList<?> list);
    public boolean delete();
    public boolean delete(String[] id,String[] text);
}
