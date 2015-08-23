package com.jluzh.wilsonleung.recycleviewforsqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;


/**
 * Created by Administrator on 2015/7/20.
 */
public class MySQLiteOpenHelper extends SQLiteOpenHelper{
    public Context mContext;


    public static final String createTable = "create table record (" +
            "objectId autoinc primary , "+
            "username text,"+
            "distance text, " +
            "step text, " +
            "time text , " +
            "recordtime verchar(50))";

    //抽象类 必须定义显示的构造函数 重写方法
    public MySQLiteOpenHelper(Context context, String name, CursorFactory factory,
                              int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase arg0) {
        // TODO Auto-generated method stub
        arg0.execSQL(createTable);
        Toast.makeText(mContext, "Created!!!!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        // TODO Auto-generated method stub
        arg0.execSQL("drop table if exists record");
        onCreate(arg0);
        Toast.makeText(mContext, "Upgraged", Toast.LENGTH_SHORT).show();
    }
    public Cursor query(String sql, String[] args) {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, args);
        return cursor;
    }
}
