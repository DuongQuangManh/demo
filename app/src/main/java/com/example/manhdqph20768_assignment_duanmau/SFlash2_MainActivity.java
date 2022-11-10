package com.example.manhdqph20768_assignment_duanmau;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

public class SFlash2_MainActivity extends AppCompatActivity {
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sflash2_main);
        tv = findViewById(R.id.tv_gioithieuapp);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SFlash2_MainActivity.this,Login_MainActivity.class);
                startActivity(intent);

            }
        },2000);
    }
}