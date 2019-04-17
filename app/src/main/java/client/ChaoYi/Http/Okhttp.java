package client.ChaoYi.Http;

import net.sf.json.JSON;

import java.io.IOException;
import java.util.List;

import client.ChaoYi.Model.Contenttable;
import client.ChaoYi.Until.Jsonuntil;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Administrator on 2019/4/17.
 */

public class Okhttp {
    public String url="http://192.168.2.210:8080/webtest/Test";
    public void postdata(List<Contenttable> data){
//        String result = Jsonuntil.listtojson(data);
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");//"类型,字节码"
        //字符串
        String value = "{\"username\":\"lisi\",\"nickname\":\"李四\"}";
        //1.创建OkHttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient();
        //2.通过RequestBody.create 创建requestBody对象
        RequestBody requestBody =RequestBody.create(mediaType, value);
        //3.创建Request对象，设置URL地址，将RequestBody作为post方法的参数传入
        Request request = new Request.Builder().url(url).post(requestBody).build();
        //4.创建一个call对象,参数就是Request请求对象
        Call call = okHttpClient.newCall(request);
        //5.请求加入调度,重写回调方法
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
            }
        });
    }
}
