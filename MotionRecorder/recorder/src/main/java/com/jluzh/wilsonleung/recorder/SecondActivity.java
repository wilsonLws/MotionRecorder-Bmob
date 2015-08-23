package com.jluzh.wilsonleung.recorder;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by Administrator on 2015/8/18.
 */
public class SecondActivity extends Activity {
    private RecyclerView recyclerView;
    private List<SportItem> items2;
    private MyAdapter myAdapter;
    TextView textView;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_second);
        textView= (TextView) findViewById(R.id.textView_username);
        recyclerView= (RecyclerView) findViewById(R.id.recycleview);

        Bmob.initialize(this, "0d0a4ecaec60e6e498994a604592a40b");

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(SecondActivity.this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.addItemDecoration(new DividerItemDecoration(SecondActivity.this, DividerItemDecoration.VERTICAL_LIST));

//        listView= (ListView) findViewById(R.id.listview);
//
        addData();


        myAdapter=new MyAdapter(SecondActivity.this,items2);
        recyclerView.setAdapter(myAdapter);
        Toast.makeText(SecondActivity.this,"OK!!!!!",Toast.LENGTH_LONG).show();



    }

    private void addData() {

        SharedPreferences sharedPreferences=getSharedPreferences("username",MODE_PRIVATE);
        final String username=sharedPreferences.getString("name","");
        textView.setText(username);

        items2=new ArrayList<SportItem>();
        BmobQuery<SportItem> query4=new BmobQuery<SportItem>();
        query4.addWhereEqualTo("username","04120417");
        query4.setLimit(100);
        query4.findObjects(SecondActivity.this, new FindListener<SportItem>() {
            @Override
            public void onSuccess(List<SportItem> list) {

                AlertDialog.Builder builder=new AlertDialog.Builder(SecondActivity.this);
                String str="";
                for (SportItem item:list) {
                        items2.add(item);

                }
                for(int i=0;i<items2.size();i++)
                {
                    str+=items2.get(i).getUsername().toString()
                    +items2.get(i).getRecordtime().toString()
                    +items2.get(i).getDistance();
                }
                builder.setMessage(str);
                builder.create().show();
            }

            @Override
            public void onError(int i, String s) {
                Toast.makeText(SecondActivity.this,"Error!!!!",Toast.LENGTH_LONG).show();
            }
        });
    }
}
