package com.jluzh.wilsonleung.motionrecorder;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by Administrator on 2015/7/20.
 */
public class ReadyActivity extends Activity {
    private Spinner spinner;
    private Button btn_start;
    private TextView textView_spinner;
    private String selectedItem;
    private SimpleAdapter simpleAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_readyactivity);
        ExitApplication.getInstance().addActivity(this);
        spinner= (Spinner) findViewById(R.id.spinner);
        textView_spinner= (TextView) findViewById(R.id.textView6);
        btn_start= (Button) findViewById(R.id.button_start);

        handleData();

        final List<Item> dataList=new ArrayList<Item>();
        dataList.add(new Item("跑步",R.drawable.run));
        dataList.add(new Item("骑行", R.drawable.ride));

        MySpinnerAdapter adapter=new MySpinnerAdapter(this,dataList);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedItem=dataList.get(position).getItmesname();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (selectedItem) {
                    case "跑步":
                        Intent i = new Intent(ReadyActivity.this, RunningActivity.class);
                        startActivity(i);
                        break;
                    case "骑行":
                    case "登山":
                        Toast.makeText(ReadyActivity.this, "本项目尚未开发，请重新选择！",
                                Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }

    private void handleData() {
        MySQLiteOpenHelper helper=new MySQLiteOpenHelper(this);
        SQLiteDatabase db=helper.getWritableDatabase();
        db.execSQL("delete from record");

        SharedPreferences sharedPreferences=getSharedPreferences("username_record",MODE_PRIVATE);
        String username=sharedPreferences.getString("name","");

        BmobQuery<SportData> query5=new BmobQuery<SportData>();
        query5.addWhereEqualTo("username", username.trim());
        query5.setLimit(100);
        query5.order("-createdAt");
        query5.findObjects(this, new FindListener<SportData>() {
            @Override
            public void onSuccess(List<SportData> list) {

                for (SportData item:list) {
                    if(item!=null) {
                        MySQLiteOpenHelper helper=new MySQLiteOpenHelper(ReadyActivity.this);
                        SQLiteDatabase db=helper.getWritableDatabase();
                        ContentValues values=new ContentValues();
                        values.put("username",item.getUsername());
                        values.put("distance",item.getDistance());
                        values.put("step",item.getStep());
                        values.put("time",item.getTime());
                        values.put("recordtime",item.getRecordtime());
                        values.put("curdate",System.currentTimeMillis());
                        db.insert("record", null, values);
                        values.clear();
                    }
                }

            }

            @Override
            public void onError(int i, String s) {
                Toast.makeText(ReadyActivity.this,"Error!!!!",Toast.LENGTH_LONG).show();
            }
        });

    }

}
