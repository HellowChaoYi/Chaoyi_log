package client.ChaoYi.Model;

import client.ChaoYi.Sqlitebase.Dbattribute.Attribute;

/**
 * Created by WCY on 2019/2/19.
 */

public class Logintable {
    @Attribute("primary key autoincrement")
    public String username;
    @Attribute("not null")
    public String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {

        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
