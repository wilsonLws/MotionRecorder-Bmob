package com.jluzh.wilsonleung.recorder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2015/8/20.
 */
public class MyRecordAdapter extends BaseAdapter{
    private MyViewHolder viewHolder;
    private LayoutInflater inflater;
    private Context context;
    private List<SportItem> mData;


    public MyRecordAdapter(Context context, List<SportItem> mData) {
        this.context=context;
        inflater=LayoutInflater.from(context);
        this.mData=mData;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        if( convertView== null){
            convertView= inflater.inflate(R.layout.layout_item, null);
            viewHolder.textView_distance= (TextView) convertView.findViewById(R.id.record_distance);
            viewHolder.textView_step= (TextView) convertView.findViewById(R.id.record_step);
            viewHolder.textView_time= (TextView) convertView.findViewById(R.id.record_time);
            viewHolder.textView_recordtime= (TextView) convertView.findViewById(R.id.record_recordtime);
            convertView.setTag( viewHolder);
        } else{
            viewHolder=(ViewHolder) convertView.getTag();
        }

        SportItem item=mData.get(position);
        viewHolder.textView_distance.setText(item.distance+"米");
        viewHolder.textView_step.setText(item.step+"步");
        viewHolder.textView_time.setText(item.time);
        viewHolder.textView_recordtime.setText(item.recordtime);

        return convertView ;


    }
    class ViewHolder{

        TextView  textView_distance;
        TextView textView_step;
        TextView textView_time;
        TextView textView_recordtime;

    }
}
