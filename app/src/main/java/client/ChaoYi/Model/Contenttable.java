package client.ChaoYi.Model;

import client.ChaoYi.Sqlitebase.Dbattribute.Attribute;

/**
 * Created by WCY on 2019/3/17.
 */

public class Contenttable {
    @Attribute(" primary key")
    public String ct_id;
    @Attribute("")
    public String ct_data;
    @Attribute("")
    public String ct_content;
    @Attribute("")
    public String ct_title;
    @Attribute("")
    public String ct_name;

    public String getCt_id() {
        return ct_id;
    }

    public void setCt_id(String ct_id) {
        this.ct_id = ct_id;
    }

    public String getCt_data() {
        return ct_data;
    }

    public void setCt_data(String ct_data) {
        this.ct_data = ct_data;
    }

    public String getCt_content() {
        return ct_content;
    }

    public void setCt_content(String ct_content) {
        this.ct_content = ct_content;
    }

    public String getCt_name() {
        return ct_name;
    }

    public void setCt_name(String ct_name) {
        this.ct_name = ct_name;
    }
}