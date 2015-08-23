package com.jluzh.wilsonleung.recycleviewforsqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    private MySQLiteOpenHelper mySQLiteOpenHelper;
    private  MyAdapter myAdapter;
    private  RecyclerView recyclerView;
    private Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mySQLiteOpenHelper=new MySQLiteOpenHelper(this,"SportData.db",null,1);

        SQLiteDatabase database=mySQLiteOpenHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("username","04120417");
        values.put("distance","200");
        values.put("step","200");
        values.put("time","0:23:25");
        values.put("recordtime","2014/05/11");
        database.insert("SportData", null, values);
        values.clear();

        values.put("username","04120417");
        values.put("distance","250");
        values.put("step","100");
        values.put("time", "0:56:25");
        values.put("recordtime", "2014/05/12");
        database.insert("SportData", null, values);
        values.clear();

        recyclerView = (RecyclerView) findViewById(R.id.recycleview);

        Cursor cursor = mySQLiteOpenHelper.query("select * from record"
                , null);

        myAdapter = new MyAdapter(this,cursor);
        recyclerView.setAdapter(myAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));


    }


}
