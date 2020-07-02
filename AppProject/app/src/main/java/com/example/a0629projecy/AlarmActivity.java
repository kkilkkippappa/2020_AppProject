package com.example.a0629projecy;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AlarmActivity extends AppCompatActivity {
    
    Button riceBtn2, alarmBtn2;
    ListView lv;
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        
        lv = findViewById(R.id.alarmLv);
        riceBtn2 = findViewById(R.id.riceBtn2);
        alarmBtn2 = findViewById(R.id.alarmBtn2);
        
        riceBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });
        
        alarmBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AlarmActivity.this, "알람이 이미 실행중입니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
