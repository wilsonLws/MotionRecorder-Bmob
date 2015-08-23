package com.jluzh.wilsonleung.motionrecorder;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2015/8/18.
 */
public class User extends BmobObject {
   public String username;
   public String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
