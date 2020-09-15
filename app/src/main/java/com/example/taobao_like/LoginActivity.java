package com.example.taobao_like;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.taobao_like.object.User;
import com.example.taobao_like.httphelper.HttpUtil.Http_User;
import com.example.taobao_like.httphelper.JsonHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView mTvLoginactivityRegister;
    private RelativeLayout mRlLoginactivityTop;
    private EditText mEtLoginactivityUsername;
    private EditText mEtLoginactivityPassword;
    private LinearLayout mLlLoginactivityTwo;
    private Button mBtLoginactivityLogin;
    private ImageView iv_loginactivity_back;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
    }

    private void initView() {
        // 初始化控件
        mBtLoginactivityLogin = findViewById(R.id.bt_loginactivity_login);
        mTvLoginactivityRegister = findViewById(R.id.bt_loginactivity_register);
        mRlLoginactivityTop = findViewById(R.id.rl_loginactivity_top);
        mEtLoginactivityUsername = findViewById(R.id.et_loginactivity_username);
        mEtLoginactivityPassword = findViewById(R.id.et_loginactivity_password);
        mLlLoginactivityTwo = findViewById(R.id.ll_loginactivity_two);
        iv_loginactivity_back = findViewById(R.id.iv_loginactivity_back);

        // 设置点击事件监听器
        mBtLoginactivityLogin.setOnClickListener(this);
        mTvLoginactivityRegister.setOnClickListener(this);
        iv_loginactivity_back.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            // 跳转到注册界面
            case R.id.bt_loginactivity_register:
                startActivity(new Intent(this, RegisterActivity.class));
                finish();
                break;

            case R.id.bt_loginactivity_login:
                String name = mEtLoginactivityUsername.getText().toString().trim();
                String password = mEtLoginactivityPassword.getText().toString().trim();
                Log.i("name",name);
                loginWithOkHttp(name,password);
                break;

            case R.id.iv_loginactivity_back:
                finish();
        }
    }

    public void loginWithOkHttp(String user_name,String password){
        Http_User.login(user_name, password, new Callback() {
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
                                Toast.makeText(LoginActivity.this,"用户不存在",Toast.LENGTH_SHORT).show();
                            }
                            else if(jsonHelper.getRet_code() == 1){
                                Toast.makeText(LoginActivity.this,"密码错误",Toast.LENGTH_SHORT).show();
                            }
                            else {
                                //解析json数据
                                user = jsonHelper.getuser(jsonHelper.getData());
                                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                                intent.putExtra("user",user);
                                Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                                startActivity(intent);
                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                            Toast.makeText(LoginActivity.this,"网络通信异常",Toast.LENGTH_SHORT).show();
                    }
                    }
                });
            }
        });
    }
}