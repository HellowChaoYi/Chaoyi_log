package client.ChaoYi.Sqlitebase.Dbattribute;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by WCY on 2019/3/7.
 */

public interface ExecuteSQL {
    public List<?> select();
    public List<?> selectwhere(String id,String[] text);
    public void insert(Map<String, String> list);
    public void updata(String text,ArrayList<?> list);
    public boolean delete(String text);
}
