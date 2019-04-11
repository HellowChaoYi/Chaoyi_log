package client.ChaoYi.Until;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import client.ChaoYi.Model.Contenttable;
import client.ChaoYi.Sqlitebase.Dbattribute.Attribute;

/**
 * Created by Administrator on 2019/2/27.
 */

public class Sys {
    public static void o(String contxt, String res){
        System.out.println(contxt+": "+res);
    }
    public static String getNow_Time(){
        Date now =new Date();
        SimpleDateFormat getformat = new SimpleDateFormat("yyyy/MM/dd");
        return getformat.format(now.getTime());
    }
    public static Map<String,String> modeltomap(Object model)throws Exception{
        Map<String,String> map = new HashMap();
        Field[] fields = model.getClass().getDeclaredFields();
        for(Field field : fields ) {
            field.setAccessible(true);
            if(field.getType()== String.class) {
                map.put(field.getName(), (String) field.get(model));
//                System.out.println("属性名:" + field.getName() + " 属性值:" + field.get(model));
            }
        }
        return map;
    }
    public static void maptomodel(Map map,Object model){
        Iterator<Map.Entry<String, String>> it=map.entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry<String,String> entry=it.next();
            String key=entry.getKey();
            Field[] fields = model.getClass().getDeclaredFields();
            for(Field field : fields ) {
                field.setAccessible(true);
                if(field.getType()== String.class) {
//                    map.put(field.getName(), (String) field.get(model));
                    if(key.equals(field.getName())){

                    }
//                System.out.println("属性名:" + field.getName() + " 属性值:" + field.get(model));
                }
            }
        }
    }
//    public static void testReflect(Object model) throws SecurityException,
//            NoSuchMethodException, IllegalArgumentException,
//            IllegalAccessException, InvocationTargetException, InstantiationException {
//        Class cls = model.getClass();
//        Field[] fields = cls.getDeclaredFields();
//        for(int i=0; i<fields.length; i++) {
//            Field f = fields[i];
//            f.setAccessible(true);
//            System.out.println("属性名:" + f.getName() + " 属性值:" + f.get(model));
//        }
//    }
}
