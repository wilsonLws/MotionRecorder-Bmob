package com.jluzh.wilsonleung.recycleviewforsqlite;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * Created by Administrator on 2015/8/16.
 */
public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private MyViewHolder viewHolder;
    private LayoutInflater inflater;
    private Context context;
    private Cursor cursor;
    public MyAdapter(Context context,Cursor cursor) {
        this.context=context;
        this.cursor=cursor;
        inflater=LayoutInflater.from(context);

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int pos) {
       View view=inflater.inflate(R.layout.layout_item,viewGroup,false);
        MyViewHolder viewHolder=new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int i) {

        cursor.moveToPosition(i);
        viewHolder.textView_distance.setText(cursor.getString(2)+"米");
        viewHolder.textView_step.setText(cursor.getString(3)+"步");
        viewHolder.textView_time.setText(cursor.getString(4));
        viewHolder.textView_recordtime.setText(cursor.getString(5));


//                viewHolder.textView_distance.setText("1");
//        viewHolder.textView_step.setText(" 2");
//        viewHolder.textView_time.setText("3");
//        viewHolder.textView_recordtime.setText("4");

    }


    @Override
    public int getItemCount() {
        return cursor.getCount();
    }
}

class MyViewHolder extends RecyclerView.ViewHolder
{
     TextView  textView_distance;
     TextView textView_step;
     TextView textView_time;
     TextView textView_recordtime;

    public MyViewHolder(View itemView) {
        super(itemView);
        textView_distance= (TextView) itemView.findViewById(R.id.record_distance);
        textView_step= (TextView) itemView.findViewById(R.id.record_step);
        textView_time= (TextView) itemView.findViewById(R.id.record_time);
        textView_recordtime= (TextView) itemView.findViewById(R.id.record_recordtime);
    }
}
