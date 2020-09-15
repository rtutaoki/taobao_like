package com.example.taobao_like;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.taobao_like.Adapter.CommodityAdapter;
import com.example.taobao_like.object.Commodity;
import com.example.taobao_like.httphelper.HttpUtil.Http_Commodity;
import com.example.taobao_like.httphelper.JsonHelper;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class SearchCommodityActivity extends AppCompatActivity {
    private ImageView iv_searchcom_back;
    private EditText ed_search_bar;
    private Button bt_search;
    private List<Commodity> commodityList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serch_commodity);

        iv_searchcom_back = findViewById(R.id.iv_search_back);
        ed_search_bar = findViewById(R.id.ed_search_bar);
        bt_search = findViewById(R.id.bt_search);


        iv_searchcom_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        bt_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String commodity_name = ed_search_bar.getText().toString().trim();
                if (TextUtils.isEmpty(commodity_name)) {
                    Toast.makeText(SearchCommodityActivity.this, "输入为空，请输入", Toast.LENGTH_SHORT).show();
                } else {
                    getComByNameLikeWithOkHttp(commodity_name);
                }
            }
        });
    }

    public void getComByNameLikeWithOkHttp(String name_like) {

        Http_Commodity.getComByComName(name_like, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("onFailure",e.getMessage());
            }

            @Override
            public void onResponse(Call call, final Response response) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JsonHelper jsonHelper =new JsonHelper(response);
                            if(jsonHelper.getRet_code() == 0) {
                                Toast.makeText(SearchCommodityActivity.this,"暂无该商品",Toast.LENGTH_SHORT).show();
                            }
                            else {
                                //解析json数据
                                commodityList = jsonHelper.getcommodity(jsonHelper.getData(),jsonHelper.getRet_code());

                                //创建RecyclerView
                                RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_search);
                                LinearLayoutManager layoutManager = new LinearLayoutManager(SearchCommodityActivity.this);
                                recyclerView.setLayoutManager(layoutManager);
                                CommodityAdapter adapter = new CommodityAdapter(commodityList, new CommodityAdapter.onItemClickListener() {
                                    @Override
                                    public void onClick(int postion) {
                                        Intent intent = new Intent(SearchCommodityActivity.this,CommodityActivity.class);
                                        Commodity commodity = commodityList.get(postion);
                                        intent.putExtra("commodity",commodity);
                                        startActivity(intent);
                                    }
                                });
                                recyclerView.setAdapter(adapter);
                                Toast.makeText(SearchCommodityActivity.this,"搜索成功",Toast.LENGTH_SHORT).show();
                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                            Toast.makeText(SearchCommodityActivity.this,"网络通信异常",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}