package com.jluzh.wilsonleung.recorder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;


/**
 * Created by Administrator on 2015/8/16.
 */
public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private MyViewHolder viewHolder;
    private LayoutInflater inflater;
    private Context context;
    private  List<SportItem> mData;
    private View view;
    public MyAdapter(Context context,List<SportItem> mData) {
        this.context=context;
        this.mData=mData;
        inflater=LayoutInflater.from(context);

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int pos) {
        view=inflater.inflate(R.layout.layout_item,viewGroup,false);
        viewHolder=new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int i) {
        SportItem item=mData.get(i);
        viewHolder.textView_distance.setText(item.getDistance()+"米");
        viewHolder.textView_step.setText(item.getStep()+"步");
        viewHolder.textView_time.setText(item.getTime());
        viewHolder.textView_recordtime.setText(item.getRecordtime());

//        viewHolder.textView_distance.setText("1");
//        viewHolder.textView_step.setText(" 2");
//        viewHolder.textView_time.setText("3");
//        viewHolder.textView_recordtime.setText("4");
    }


    @Override
    public int getItemCount() {
        return mData.size();
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
