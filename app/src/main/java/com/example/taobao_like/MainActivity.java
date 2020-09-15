package com.example.taobao_like;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.taobao_like.Fragment_main.Fragment_gwc;
import com.example.taobao_like.Fragment_main.Fragment_sy;
import com.example.taobao_like.Fragment_main.Fragment_wdtb;
import com.example.taobao_like.Fragment_main.Fragment_wt;
import com.example.taobao_like.Fragment_main.Fragment_xx;
import com.example.taobao_like.object.User;

public class MainActivity extends AppCompatActivity {
    LinearLayout linearLayout1,linearLayout2,linearLayout3,linearLayout4,linearLayout5;
    LinearLayout[] linearLayouts;
    int[] imageviews = new int[]{R.id.iv_tab1,R.id.iv_tab2,R.id.iv_tab3,R.id.iv_tab4,R.id.iv_tab5};
    int[] textviews = new int[]{R.id.tv_tab1,R.id.tv_tab2,R.id.tv_tab3,R.id.tv_tab4,R.id.tv_tab5};
    int[] srcs1 = new int[]{R.drawable.tab_shouye1,R.drawable.tab_weitao1,R.drawable.tab_xiaoxi1,R.drawable.tab_gouwuche1,R.drawable.tab_wodetb1};
    int[] srcs2 = new int[]{R.drawable.tab_shouye2,R.drawable.tab_weitao2,R.drawable.tab_xiaoxi2,R.drawable.tab_gouwuche2,R.drawable.tab_wodetb2};
    FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linearLayout1 = findViewById(R.id.tab_shouye);
        linearLayout2 = findViewById(R.id.tab_weitao);
        linearLayout3 = findViewById(R.id.tab_xiaoxi);
        linearLayout4 = findViewById(R.id.tab_gouwuche);
        linearLayout5 = findViewById(R.id.tab_wodetb);
        linearLayouts=new LinearLayout[]{linearLayout1,linearLayout2,linearLayout3,linearLayout4,linearLayout5};
        fm=getSupportFragmentManager();
//      监听每个tab的点击事件，切换对应的Fragment
        for(int i=0;i<linearLayouts.length;i++){
            final int tab=i;
            linearLayouts[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showTab(tab);
                }
            });
        }
        showTab(0);
    }
//点击切换Fragment，显示当前Fragment，隐藏入栈其余已加载的Fragment，并修改tab背景颜色
    private void showTab(int index){
        Fragment fragment=null;
        fragment=fm.findFragmentByTag("tab"+index);
        if(fragment==null || !fragment.isAdded()){
            fragment=addFragment(index);
        }
        hideFragment(fragment);
        showFragment(fragment);
        changeTabColor(index);
    }
//创建Fragment页面，并赋予相对应tag值，利于取出
    private Fragment addFragment(int index){
        FragmentTransaction ft =fm.beginTransaction();
        Fragment fragment = null;
        switch (index){
            case 0:
                fragment = new Fragment_sy();
                break;
            case 1:
                fragment = new Fragment_wt();
                break;
            case 2:
                fragment = new Fragment_xx();
                break;
            case 3:
                fragment = new Fragment_gwc();
                break;
            case 4:
                Intent intent = getIntent();
                User user = (User) intent.getSerializableExtra("user");
                fragment = new Fragment_wdtb(user);
                break;
        }
        ft.add(R.id.framelayout,fragment,"tab"+index);
        ft.commitAllowingStateLoss();
        return fragment;
    }

    private void hideFragment(Fragment fragment){
        for(int i=0;i<linearLayouts.length;i++){
            Fragment f = fm.findFragmentByTag("tab"+i);
            if(f!=null && f.isAdded() && f!=fragment){
                FragmentTransaction ft = fm.beginTransaction();
                ft.hide(f);
                ft.commitAllowingStateLoss();
                f.setUserVisibleHint(false);
            }
        }
    }
//显示当前Fragment
    private void showFragment(Fragment fragment){
        FragmentTransaction ft = fm.beginTransaction();
        ft.show(fragment);
        ft.commitAllowingStateLoss();
        fragment.setUserVisibleHint(true);
    }
//设置当前页面对应tab颜色突出
    private void changeTabColor(int index){
        for(int i=0;i<linearLayouts.length;i++){
            ImageView imageView = findViewById(imageviews[i]);
            TextView textView = findViewById(textviews[i]);
            if(i==index){
                imageView.setImageResource(srcs2[i]);
                textView.setTextColor(getResources().getColor(R.color.red));
            }
            else{
                imageView.setImageResource(srcs1[i]);
                textView.setTextColor(getResources().getColor(R.color.black));
            }
        }
    }
}
