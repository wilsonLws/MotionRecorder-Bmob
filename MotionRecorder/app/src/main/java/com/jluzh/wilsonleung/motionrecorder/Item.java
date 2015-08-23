package com.jluzh.wilsonleung.motionrecorder;

/**
 * Created by Administrator on 2015/7/26.
 */
public class Item {
    private String itmesname;
    private int imageId;

    public Item(String itmesname, int imageId) {
        this.itmesname = itmesname;
        this.imageId = imageId;
    }

    public String getItmesname() {
        return itmesname;
    }

    public void setItmesname(String itmesname) {
        this.itmesname = itmesname;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
