package com.example.xwh12.qq_login.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.xwh12.qq_login.R;
import com.example.xwh12.qq_login.Util.ConstValue;
import com.tencent.connect.share.QQShare;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONObject;

public class ShareActivity extends AppCompatActivity {
    private Button mShareButton;
    private Tencent mTencent;
    private ConstValue mConstValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        findView();
        init();
        setListener();
    }
    private void findView(){
        mShareButton=(Button)findViewById(R.id.share_button);
    }
    private void init(){
        mConstValue=new ConstValue();
        mTencent = Tencent.createInstance(mConstValue.APP_ID, getApplicationContext());

    }
    private void setListener(){
        mShareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareTQ(v);
            }
        });
    }
//  分享功能
    private void shareTQ(View view){
        final Bundle params = new Bundle();
        params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
        params.putString(QQShare.SHARE_TO_QQ_TITLE, mConstValue.ShareName);
        params.putString(QQShare.SHARE_TO_QQ_SUMMARY, mConstValue.ShareContext);
        params.putString(QQShare.SHARE_TO_QQ_TARGET_URL,mConstValue.ShareUrl);
        params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, mConstValue.ShareImageUrl);
        mTencent.shareToQQ(this, params , new BaseUiListener());
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (null != mTencent)
            mTencent.onActivityResult(requestCode, resultCode, data);
    }
    //   私有回调
    private class BaseUiListener implements IUiListener {
        public void onComplete(Object response) {
            Toast.makeText(getApplicationContext(),"发送成功",Toast.LENGTH_SHORT);

        }

        @Override
        public void onError(UiError e) {
            Toast.makeText(getApplicationContext(), "发送失败", Toast.LENGTH_SHORT);

        }

        @Override
        public void onCancel() {
            Toast.makeText(getApplicationContext(), "取消发送", Toast.LENGTH_SHORT);

        }
    }


}
