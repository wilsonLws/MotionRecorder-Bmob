package com.jluzh.wilsonleung.motionrecorder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2015/7/26.
 */
public class MySpinnerAdapter extends BaseAdapter{
    private List<Item> dataList;
    private Context context;

    public MySpinnerAdapter(Context context,List<Item> dataList) {
        this.context=context;
        this.dataList = dataList;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        convertView=layoutInflater.inflate(R.layout.layout_item_spinner,null);
        if(convertView!=null)
        {
            TextView textView=(TextView)convertView.findViewById(R.id.textView6);
            ImageView imageView= (ImageView) convertView.findViewById(R.id.imageView);
            textView.setText(dataList.get(position).getItmesname());
            imageView.setImageResource(dataList.get(position).getImageId());
        }
        return convertView;
    }
}
