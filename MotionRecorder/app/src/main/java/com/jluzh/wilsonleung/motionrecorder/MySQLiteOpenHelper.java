package com.jluzh.wilsonleung.motionrecorder;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;


/**
 * Created by Administrator on 2015/7/20.
 */
public class MySQLiteOpenHelper extends SQLiteOpenHelper{
    public Context mContext;

    //创建学生表(学号,姓名,电话,身高) 主键学号
    public static final String createTable = "create table record (" +
            "curdate text primary key, "+
            "username text,"+
            "distance text, " +
            "step text, " +
            "time text , " +
            "recordtime verchar(50))";

    //抽象类 必须定义显示的构造函数 重写方法
    public MySQLiteOpenHelper(Context context) {
        super(context, "Database_Record.db", null, 2);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase arg0) {
        // TODO Auto-generated method stub
        arg0.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        // TODO Auto-generated method stub
        arg0.execSQL("drop table if exists Student");
        onCreate(arg0);
        Toast.makeText(mContext, "Upgraged", Toast.LENGTH_SHORT).show();
    }
}
