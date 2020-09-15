package com.example.taobao_like.Fragment_main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.taobao_like.LoginActivity;
import com.example.taobao_like.R;
import com.example.taobao_like.object.User;

public class Fragment_wdtb extends Fragment {
    TextView textView;
    User user;

    public Fragment_wdtb(User user){
        this.user = user;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_wodetb,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        textView=getActivity().findViewById(R.id.wdtb_username);
//        点击登录，跳转登录界面
        if(user == null ){
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), LoginActivity.class);
                    startActivity(intent);
                }
            });
        }
        else {
            textView.setText("您好！ "+user.getName());
        }
    }

}
