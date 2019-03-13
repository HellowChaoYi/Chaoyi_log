package client.ChaoYi.Sqlitebase.Dbattribute;

import java.util.ArrayList;

/**
 * Created by WCY on 2019/3/7.
 */

public interface ExecuteSQL {
    public String select(String... text);
    public void insert(ArrayList<?> list);
    public void updata(String text,ArrayList<?> list);
    public void delete(String text);
}
