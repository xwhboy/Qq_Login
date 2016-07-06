package com.example.xwh12.qq_login.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.xwh12.qq_login.Util.ConstValue;
import com.example.xwh12.qq_login.R;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    private Tencent mTencent;
    private IUiListener qqListener;
    private ConstValue mConstValue;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findView();
        init();
        setListener();
    }

    private void findView() {
        loginButton = (Button) findViewById(R.id.login_button);
    }

    private void init() {
//        实体化
        mTencent = Tencent.createInstance(mConstValue.APP_ID, getApplicationContext());
        qqListener = new BaseUiListener();
        mConstValue = new ConstValue();

    }

    private void login() {
        mTencent = Tencent.createInstance(mConstValue.APP_ID, this.getApplicationContext());
        if (!mTencent.isSessionValid()) {
            mTencent.login(this, mConstValue.SCOPE, qqListener);
        }
    }

    private void setListener() {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Tencent.onActivityResultData(requestCode, resultCode, data, qqListener);
    }
//   私有回调
    private class BaseUiListener implements IUiListener {

        public void onComplete(Object response) {
            Log.v("Tag", "回调成功");
            JSONObject responseJsonobject = (JSONObject) response;
//      user openid
            final String openid = responseJsonobject.optString("openid");
            final String access_token = responseJsonobject.optString("access_token");
            final String expires_in = responseJsonobject.optString("expires_in");
            Log.i("回调返回", response.toString());
            Intent mIntent=new Intent();
            mIntent.setClass(getApplicationContext(),ShareActivity.class);
            startActivity(mIntent);

        }

        @Override
        public void onError(UiError e) {
            Log.v("Tag", "回调失败");
        }

        @Override
        public void onCancel() {
            Log.v("Tag", "回调取消");
        }
    }

}