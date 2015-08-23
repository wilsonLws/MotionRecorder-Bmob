package com.jluzh.wilsonleung.recorder;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2015/8/19.
 */
public class SportItem extends BmobObject{
    String username;
    String distance;
    String step;
    String time;
    String recordtime;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRecordtime() {
        return recordtime;
    }

    public void setRecordtime(String recordtime) {
        this.recordtime = recordtime;
    }
}
