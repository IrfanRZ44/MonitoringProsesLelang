package com.exomatik.monitoringproseslelang.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.exomatik.monitoringproseslelang.R;
import com.victor.loading.newton.NewtonCradleLoading;

public class SplashAct extends AppCompatActivity {
    private NewtonCradleLoading loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_splash);

        init();
        cekFirebase();
    }

    private void init() {
        loadingBar = (NewtonCradleLoading) findViewById(R.id.loadingBar);
        loadingBar.start();
    }

    private void cekFirebase() {
        new Handler().postDelayed(new Runnable() {
            public void run() {
                Intent homeIntent = new Intent(SplashAct.this, LoginAct.class);
                startActivity(homeIntent);
                finish();
            }
        }, 2000L);
    }
}
