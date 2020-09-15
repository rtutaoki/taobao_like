package com.example.taobao_like.Fragment_main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.taobao_like.Adapter.GridViewAdapter;
import com.example.taobao_like.MainActivity;
import com.example.taobao_like.R;
import com.example.taobao_like.Adapter.ViewPagerAdapter;
import com.example.taobao_like.SearchCommodityActivity;

import java.util.ArrayList;
import java.util.List;

public class Fragment_sy extends Fragment {
    ViewPager viewPager;
    GridView gridView;
    GridViewAdapter gridViewAdapter;
    List<View> list;
    LayoutInflater inflater;
    EditText editText;

    int[] pics1=new int[]{R.drawable.menu_guide_1,R.drawable.menu_guide_2,
            R.drawable.menu_guide_3,R.drawable.menu_guide_4,R.drawable.menu_guide_5,
            R.drawable.menu_guide_6,R.drawable.menu_guide_7,R.drawable.menu_guide_8};
    int[] pics2=new int[]{R.drawable.menu_viewpager_1,R.drawable.menu_viewpager_2,R.drawable.menu_viewpager_3,R.drawable.menu_viewpager_4,R.drawable.menu_viewpager_5};


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_shouye,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        加载轮播图布局
        inflater=getLayoutInflater();
        list=new ArrayList<View>();
        for(int i=0;i<pics2.length;i++){
            View view=inflater.inflate(R.layout.item_viewpager,null);
            ImageView imageView=view.findViewById(R.id.viewpager_iv);
            imageView.setImageResource(pics2[i]);
            list.add(view);
        }
        viewPager=getActivity().findViewById(R.id.shouye_viewPager);
        viewPager.setAdapter(new ViewPagerAdapter(list));
        viewPager.setOnTouchListener(new View.OnTouchListener() {
            float startX,startY,endX,endY;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        startX=event.getX();
                        startY=event.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        endX=event.getX();
                        endY=event.getY();
//                        最后一页左滑动，进入第一页；第一页右滑动，进入最后一页；点击轮播图，进入商品列表
                        if(viewPager.getCurrentItem()==(list.size()-1) && startX-endX>0){
                            viewPager.setCurrentItem(0,false);
                        }
                        else if(viewPager.getCurrentItem()==0 && startX-endX<0){
                            viewPager.setCurrentItem(list.size()-1,false);
                        }
                        else if(startX-endX==0 || startY-endY==0){

                        }
                        break;
                }
                return false;
            }
        });

//        加载九宫格布局
        gridView=getActivity().findViewById(R.id.shouye_gridView);
        gridViewAdapter=new GridViewAdapter(getContext(),pics1);
        gridView.setAdapter(gridViewAdapter);

        editText = getActivity().findViewById(R.id.shouye_ed1);
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchCommodityActivity.class);
                startActivity(intent);
            }
        });
    }
}
