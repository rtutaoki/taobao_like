package com.example.taobao_like.Adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.taobao_like.R;

public class GridViewAdapter extends BaseAdapter {
    GridView gridView;
    int[] pics;
    LayoutInflater inflater;

    public GridViewAdapter(Context context,int[] pics){
        inflater=LayoutInflater.from(context);
        this.pics=pics;
    }

    @Override
    public int getCount() {
        return pics.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView=inflater.inflate(R.layout.item_gridview,null);
        ImageView imageView=convertView.findViewById(R.id.img_icon);
        imageView.setImageResource(pics[position]);

        return convertView;
    }
}
