package com.example.taobao_like.Fragment_main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.taobao_like.R;

public class Fragment_gwc extends Fragment {
    TextView textView1,textView2,textView3;
    LinearLayout linearLayout1,linearLayout2,linearLayout3;
    TextView[] textViews = null;
    LinearLayout[] linearLayouts = null;
    FragmentManager fm;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_gouwuche,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fm = getFragmentManager();
        FragmentTransaction ft =fm.beginTransaction();
        Fragment fragment = new MyFragment();
        ft.add(R.id.gouwuche_framelayout,fragment);
        ft.commitAllowingStateLoss();
        textView1 = getActivity().findViewById(R.id.gouwuche_tv1);
        textView2 = getActivity().findViewById(R.id.gouwuche_tv2);
        textView3 = getActivity().findViewById(R.id.gouwuche_tv3);
        linearLayout1 = getActivity().findViewById(R.id.gouwuche_linear1);
        linearLayout2 = getActivity().findViewById(R.id.gouwuche_linear2);
        linearLayout3 = getActivity().findViewById(R.id.gouwuche_linear3);
        textViews = new TextView[]{textView1,textView2,textView3};
        linearLayouts = new LinearLayout[]{linearLayout1,linearLayout2,linearLayout3};

        for(int i=0;i<textViews.length;i++){
            final int index=i;
            textViews[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    changeBackgroundColor(index);
                    changeById(index);
                }
            });
        }
    }
//改变被点击tag背景颜色
    private void changeBackgroundColor(int index){
        for(int i=0;i<textViews.length;i++){
            if(i==index){
                linearLayouts[i].setBackgroundColor(getResources().getColor(R.color.black6));
            }else{
                linearLayouts[i].setBackgroundColor(getResources().getColor(R.color.blackC));
            }
        }
    }

    public static class MyFragment extends Fragment {
        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            return inflater.inflate(R.layout.gouwuche, container, false);
        }

    }
//    不同tag显示不同的fragment页面内容
    private void changeById(int id) {
        int pics[] = new int[]{R.drawable.qbbb, R.drawable.jjbb, R.drawable.kcjz};
        String[] strings1 = new String[]{"购物车快饿扁了T.T", "眼光真好，目前还没有降价的宝贝", "看起来宝贝库存很丰满"};
        String[] strings2 = new String[]{"主人快给我挑点宝贝吧", "别等降价了，赶紧出手吧", "早买早享受"};

        TextView textView1 = getActivity().findViewById(R.id.gouwuche_textview1);
        TextView textView2 = getActivity().findViewById(R.id.gouwuche_textview2);
        ImageView imageView = getActivity().findViewById(R.id.gouwuche_iv1);
        Button button = getActivity().findViewById(R.id.gouwuche_btn);
        textView1.setText(strings1[id]);
        textView2.setText(strings2[id]);
        imageView.setImageResource(pics[id]);
        if (id != 0) {
            button.setVisibility(View.GONE);
        } else {
            button.setVisibility(View.VISIBLE);
        }
    }
}
