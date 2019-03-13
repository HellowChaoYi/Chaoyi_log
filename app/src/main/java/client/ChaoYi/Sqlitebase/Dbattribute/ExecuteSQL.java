package client.ChaoYi.Sqlitebase.Dbattribute;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WCY on 2019/3/7.
 */

public interface ExecuteSQL {
    public List<?> select(String id,String[] text);
    public void insert(ArrayList<?> list);
    public void updata(String text,ArrayList<?> list);
    public boolean delete(String text);
}
