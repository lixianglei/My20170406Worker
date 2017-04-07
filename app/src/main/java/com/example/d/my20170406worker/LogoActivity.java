package com.example.d.my20170406worker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

/**
 * Created by d on 2017/4/6.
 */

public class LogoActivity extends Activity {
    private final int SPLASH_DISPLAY_LENGHT=3000;// 延迟3秒

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(LogoActivity.this,MainActivity.class);
                LogoActivity.this.startActivity(intent);
                LogoActivity.this.finish();
            }},SPLASH_DISPLAY_LENGHT);
    }
}
