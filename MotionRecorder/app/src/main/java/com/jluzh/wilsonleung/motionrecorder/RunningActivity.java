package com.jluzh.wilsonleung.motionrecorder;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.TabHost;

/**
 * Created by Administrator on 2015/7/21.
 */
public class RunningActivity extends TabActivity{

    private TabHost tabHost;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ExitApplication.getInstance().addActivity(this);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        tabHost = getTabHost();


        tabHost.addTab(tabHost.newTabSpec("state")
                .setIndicator("状态")
                .setContent(new Intent(this, StatusActivity.class)));
        tabHost.addTab(tabHost.newTabSpec("map")
                .setIndicator("地图")
                .setContent(new Intent(this, MapActivity.class)));
        tabHost.addTab(tabHost.newTabSpec("record")
                .setIndicator("记录")
                .setContent(new Intent(this, RecordActivity.class)));



        tabHost.setCurrentTab(0); //从零开始

    }
}
