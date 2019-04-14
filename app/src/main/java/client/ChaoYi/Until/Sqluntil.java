package client.ChaoYi.Until;

/**
 * Created by WCY on 2019/4/14.
 */

public class Sqluntil {
    public static String setWhere(String[] id){
        StringBuffer stringbuffer = new StringBuffer();
        for(int i =0;i<id.length;i++){
            stringbuffer.append(id[i]+" =? and ");
        }
        stringbuffer.replace(stringbuffer.length()-3, stringbuffer.length(), "");
        return stringbuffer.toString();
    }
}
