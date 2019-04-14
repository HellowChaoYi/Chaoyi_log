package client.ChaoYi.Until;

import android.content.Context;
import android.content.res.AssetManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/4/9.
 */

public class Jsonuntil {
    public static String getJson(Context context, String fileName) {

        StringBuilder stringBuilder = new StringBuilder();
        try {
            AssetManager assetManager = context.getAssets();
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public static Map<String, String> jsontomap(String json){
        Map<String, String >map =new HashMap<>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            Iterator<String> keys = jsonObject.keys();
            String key ;
            while (keys.hasNext()) {
                key =keys.next();
                map.put(key,(String)jsonObject.get(key));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return map;
    }

    public static List maptolist(Map<String, String> map){
        List list = new ArrayList();
        Iterator iter = map.entrySet().iterator(); // 获得map的Iterator
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            list.add(entry.getKey());
            list.add(entry.getValue());
        }
        return list;
    }
}