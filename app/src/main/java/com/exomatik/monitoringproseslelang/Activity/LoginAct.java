package com.exomatik.monitoringproseslelang.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.exomatik.monitoringproseslelang.R;

public class LoginAct extends AppCompatActivity {
    private Button btn_sign_in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_login);

        init();
        onClick();
    }

    private void init() {
        btn_sign_in = (Button) findViewById(R.id.btn_sign_in);
    }

    private void onClick() {
        btn_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginAct.this, MainAct.class));
                finish();
            }
        });
    }
}