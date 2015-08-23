package com.jluzh.wilsonleung.motionrecorder;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXTextObject;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import java.sql.Date;
import java.text.SimpleDateFormat;

import cn.bmob.v3.listener.SaveListener;

/**
 * Created by Administrator on 2015/7/21.
 */
public class FinalActivity extends Activity{
    private TextView mtextview_show;
    private Button mButton_share;
    private Button mButton_sportagain;
    private String distance,time,step,speed;
    private MySQLiteOpenHelper sqlHelper;
    private static final String APP_ID="wx1ade14290d9c389b";
    private IWXAPI api;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_final);
        ExitApplication.getInstance().addActivity(this);

        mButton_share=(Button)findViewById(R.id.button_share);
        mButton_sportagain=(Button)findViewById(R.id.button_sportagain);
        mtextview_show=(TextView)findViewById(R.id.textView_show);


        Intent intent=getIntent();
        Bundle data=intent.getExtras();
        String distance=data.getString("distance");
        String time=data.getString("time");
        String step=data.getString("step");
        String speed=data.getString("speed");

        SharedPreferences sharedPreferences= getSharedPreferences("username_record",
                Activity.MODE_PRIVATE);
        String username =sharedPreferences.getString("name", "");

        mtextview_show.setText("你一共跑了"+distance+"米,跑了"+step+"步，用了"+time+
                ",平均速度为："+speed.toString()+"米/秒");


        sqlHelper = new MySQLiteOpenHelper(this);
        sqlHelper.getWritableDatabase();
        SQLiteDatabase db = sqlHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

		/*Intent intent=getIntent();
		Bundle data=intent.getExtras();
		String distance=data.getString("distance");
		String step=data.getString("step");*/

        SimpleDateFormat formatter =new SimpleDateFormat("yyyy/MM/dd");
        Date  curDate=new Date(System.currentTimeMillis());//获取当前时间
        String date=formatter.format(curDate);

        values.put("curdate", System.currentTimeMillis());
        values.put("time", time);
        values.put("distance", distance+"米");
        values.put("step", step+"步");
        values.put("recordtime",date);
        db.insert("record", null, values);

        SharedPreferences sharedPreferences_write=getSharedPreferences("username_record",MODE_PRIVATE);
        String username_write=sharedPreferences_write.getString("name","");

        SportData sportData=new SportData();
        sportData.setUsername(username_write.trim());
        sportData.setDistance(distance);
        sportData.setStep(step);
        sportData.setTime(time);
        sportData.setRecordtime(date);
        sportData.save(this, new SaveListener() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onFailure(int i, String s) {
                Toast.makeText(FinalActivity.this,"Error!!!!!",Toast.LENGTH_LONG).show();
            }
        });


        mButton_sportagain.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(FinalActivity.this, ReadyActivity.class);
                startActivity(intent);
            }
        });


        api= WXAPIFactory.createWXAPI(this, APP_ID);
        api.registerApp(APP_ID);

        final Builder share=new AlertDialog.Builder(this);
        mButton_share.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                showdialog_share();
            }
        });

    }
    public static void showdialog_menu(Context context)
    {
//         Builder menu=new AlertDialog.Builder(context);
//        menu.setItems(
//                new String[]{"设置","退出"}
//                , new OnClickListener()
//                {
//
//                    public void onClick(DialogInterface dialog,int which)
//                    {
//                        switch(which)
//                        {
//                            case 0:
//                                break;
//                            case 1:
//                                ExitApplication.getInstance().exit();
//                                break;
//                        }
//
//                    }
//                }
//        );
//        menu.create().show();

        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setTitle("提示");
        builder.setMessage("确定退出吗？");
        builder.setPositiveButton("确定", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ExitApplication.getInstance().exit();
            }
        });
        builder.setNegativeButton("取消", null);
        AlertDialog alertDialog=builder.create();
        alertDialog.show();

    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        showdialog_menu(FinalActivity.this);
        return super.onKeyDown(keyCode, event);}
    private void showdialog_share(){


        final AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("微信分享");
        builder.setMessage("确定分享到微信朋友圈吗？");
        builder.setPositiveButton("分享", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String text = mtextview_show.getText().toString();
                if (text == null || text.length() == 0) {
                    return;
                }
                WXTextObject textObject = new WXTextObject();
                textObject.text = text;
                WXMediaMessage msg = new WXMediaMessage();
                msg.mediaObject = textObject;
                msg.description = text;
                SendMessageToWX.Req req = new SendMessageToWX.Req();
                req.message = msg;
                req.transaction = buildTransaction("text");
                req.scene = SendMessageToWX.Req.WXSceneTimeline;
                api.sendReq(req);
                if (api.sendReq(req)) {
                    Toast.makeText(FinalActivity.this, "正在分享.....", Toast.LENGTH_LONG).show();
                }
            }
        });
        builder.setNegativeButton("取消", null);
        AlertDialog alertDialog=builder.create();
        alertDialog.show();

    }
    public String buildTransaction(final String type)
    {
        return  (type==null)? String.valueOf(System.currentTimeMillis()):type+System.currentTimeMillis();
    }


}
