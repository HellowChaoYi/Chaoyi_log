package client.ChaoYi.Model;

/**
 * Created by WCY on 2019/3/16.
 */

public class Test {
    private String type;
    //图片资源 ID
    private int imgId;

    public Test(String type, int imgId) {
        this.type = type;
        this.imgId = imgId;
    }

    public String getType() {
        return type;
    }

    public int getImgId() {
        return imgId;
    }

}
