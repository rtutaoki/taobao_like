package com.example.taobao_like;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.taobao_like.object.Commodity;

public class CommodityActivity extends AppCompatActivity {
    ImageView iv_com_search_back;
    TextView tv_com_price,tv_com_name,tv_com_info,tv_com_username,tv_com_stock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commodity);

        tv_com_price = findViewById(R.id.tv_com_price);
        tv_com_name = findViewById(R.id.tv_com_name);
        tv_com_info = findViewById(R.id.tv_com_info);
        tv_com_username = findViewById(R.id.tv_com_username);
        tv_com_stock = findViewById(R.id.tv_com_stock);

        iv_com_search_back = findViewById(R.id.iv_com_search_back);
        iv_com_search_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent intent = this.getIntent();
        Commodity commodity=(Commodity)intent.getSerializableExtra("commodity");
        tv_com_price.setText(commodity.getCommodity_price());
        tv_com_name.setText(commodity.getCommodity_name());
        tv_com_info.setText(commodity.getCommodity_info());
        tv_com_username.setText(commodity.getCommodity_user_name());
        tv_com_stock.setText(String.valueOf(commodity.getCommodity_stock()));
    }
}