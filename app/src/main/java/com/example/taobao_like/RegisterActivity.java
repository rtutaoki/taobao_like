package com.example.taobao_like;

import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.Toast;

import com.example.taobao_like.object.User;
import com.example.taobao_like.httphelper.HttpUtil.Http_User;
import com.example.taobao_like.httphelper.JsonHelper;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    private String realCode;
    private Button mBtRegisteractivityRegister;
    private RelativeLayout mRlRegisteractivityTop;
    private ImageView mIvRegisteractivityBack;
    private LinearLayout mLlRegisteractivityBody;
    private EditText mEtRegisteractivityUsername;
    private EditText mEtRegisteractivityPassword1;
    private EditText mEtRegisteractivityPassword2;
    private EditText mEtRegisteractivityAddress;
    private EditText mEtRegisteractivityPhonecodes;
    private ImageView mIvRegisteractivityShowcode;
    private RelativeLayout mRlRegisteractivityBottom;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();

        //将验证码用图片的形式显示出来
        mIvRegisteractivityShowcode.setImageBitmap(Code.getInstance().createBitmap());
        realCode = Code.getInstance().getCode().toLowerCase();
    }

    private void initView(){
        mBtRegisteractivityRegister = findViewById(R.id.bt_registeractivity_register);
        mRlRegisteractivityTop = findViewById(R.id.rl_registeractivity_top);
        mIvRegisteractivityBack = findViewById(R.id.iv_registeractivity_back);
        mLlRegisteractivityBody = findViewById(R.id.ll_registeractivity_body);
        mEtRegisteractivityUsername = findViewById(R.id.et_registeractivity_username);
        mEtRegisteractivityPassword1 = findViewById(R.id.et_registeractivity_password1);
        mEtRegisteractivityPassword2 = findViewById(R.id.et_registeractivity_password2);
        mEtRegisteractivityAddress = findViewById(R.id.et_registeractivity_address);
        mEtRegisteractivityPhonecodes = findViewById(R.id.et_registeractivity_phoneCodes);
        mIvRegisteractivityShowcode = findViewById(R.id.iv_registeractivity_showCode);

        mIvRegisteractivityBack.setOnClickListener(this);
        mIvRegisteractivityShowcode.setOnClickListener(this);
        mBtRegisteractivityRegister.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_registeractivity_back: //返回登录页面
                Intent intent1 = new Intent(this, LoginActivity.class);
                startActivity(intent1);
                finish();
                break;
            case R.id.iv_registeractivity_showCode:    //改变随机验证码的生成
                mIvRegisteractivityShowcode.setImageBitmap(Code.getInstance().createBitmap());
                realCode = Code.getInstance().getCode().toLowerCase();
                break;
            case R.id.bt_registeractivity_register:    //注册按钮
                //获取用户输入的用户名、密码、验证码
                String username = mEtRegisteractivityUsername.getText().toString().trim();
                String password1 = mEtRegisteractivityPassword1.getText().toString().trim();
                String password2 = mEtRegisteractivityPassword2.getText().toString().trim();
                String phoneCode = mEtRegisteractivityPhonecodes.getText().toString().toLowerCase();
                String address = mEtRegisteractivityAddress.getText().toString().trim();
                //注册验证
                if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password1) && !TextUtils.isEmpty(phoneCode) && !TextUtils.isEmpty(password2)) {
                    if (phoneCode.equals(realCode)) {
                        if(password1.equals(password2))
                        {
                            registerWithOkHttp(username,password1,address);
                        }
                        else
                        {
                            Toast.makeText(this,  "密码再次输入错误！，注册失败", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(this, "验证码错误,注册失败", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(this, "未完善信息，注册失败", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
    public void registerWithOkHttp(String user_name,String user_password,String user_address) {
        Http_User.register(user_name, user_password, user_address, new Callback() {
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
                            if(jsonHelper.getRet_code() == 1) {
                                Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                                finish();
                            }
                            else {
                                Toast.makeText(RegisterActivity.this,"已存在该用户",Toast.LENGTH_SHORT).show();
                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                            Toast.makeText(RegisterActivity.this,"网络通信异常",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

}