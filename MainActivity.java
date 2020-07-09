package com.example.a0629projecy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    TextView daytv, foodText;
    Button dinner, breakfast, launch,
            riceBtn, alarmBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        daytv = findViewById(R.id.daytv);
        foodText = findViewById(R.id.foodText);
        dinner = findViewById(R.id.dinner);
        breakfast = findViewById(R.id.breakfast);
        launch = findViewById(R.id.launch);
        riceBtn = findViewById(R.id.riceBtn);
        alarmBtn = findViewById(R.id.alarmBtn);

        // 현재 날짜 표현.
        String newDate = RenewCurrentDate();
        daytv.setText(newDate);

        // '아침' 버튼을 누르면 오늘 아침 메뉴를 foodText에 나타내기.
        breakfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 아침 식사
            }
        });

        // '점심' 버튼을 누르면 오늘 점심 메뉴를 foodText에 나타내기.
        launch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 점심 식사
            }
        });

        // '저녁' 버튼을 누르면 오늘 저녁 메뉴를 foodText에 나타내기.
        dinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 저녁 식사
            }
        });

        
        // '급식' 버튼을 누를때
        // 현재 창은 급식 창이니까 실행이 되면 안된다.
        riceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "급식이 이미 실행되고 있습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        // '알람' 버튼을 누를 때
        alarmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 알람창으로 가기
            }
        });
        
    }

    // 현재 날짜를 가져오는 메소드.
    public static String RenewCurrentDate(){
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        // 받아온 날짜를 yyyy년 mm월 dd일 형식으로 표현해준다.
        SimpleDateFormat format = new SimpleDateFormat("yyyy년 MM월 dd일");
        String CurrentDate = format.format(date);
        return CurrentDate;
    }
}