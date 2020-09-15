package com.example.taobao_like.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.taobao_like.R;
import com.example.taobao_like.object.Commodity;

import java.util.List;

public class CommodityAdapter extends RecyclerView.Adapter<CommodityAdapter.ViewHolder> {
    private List<Commodity> mCommodityList;
    private onItemClickListener mListener;
    public interface onItemClickListener{
         void onClick(int postion);
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView iv_com;
        TextView tv_com_id;
        TextView tv_com_name;
        TextView tv_com_username;
        TextView tv_com_stock;
        TextView tv_com_price;
        public ViewHolder(View view) {
            super(view);
            iv_com = view.findViewById(R.id.iv_com);
            tv_com_id = view.findViewById(R.id.tv_com_id);
            tv_com_name = view.findViewById(R.id.tv_com_name);
            tv_com_username = view.findViewById(R.id.tv_com_username);
            tv_com_stock = view.findViewById(R.id.tv_com_stock);
            tv_com_price = view.findViewById(R.id.tv_com_price);
        }

    }
    public CommodityAdapter(List<Commodity> commodityList, onItemClickListener mListener) {
        mCommodityList = commodityList;
        this.mListener = mListener;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.commodity_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Commodity commodity = mCommodityList.get(position);
        holder.iv_com.setImageResource(R.drawable.com_example);
        holder.tv_com_id.setText(String.valueOf(commodity.getCommodity_id()));
        holder.tv_com_name.setText(commodity.getCommodity_name());
        holder.tv_com_username.setText(commodity.getCommodity_user_name());
        holder.tv_com_stock.setText(String.valueOf(commodity.getCommodity_stock()));
        holder.tv_com_price.setText(commodity.getCommodity_price());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClick(position);
            }
        });
    }
    @Override
    public int getItemCount() {
        return mCommodityList.size();
    }
}
