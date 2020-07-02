package com.example.a0629projecy;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class FirstActivity extends AppCompatActivity {

    EditText cityName, schoolName;
    Button okBtn2, okBtn1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        cityName = findViewById(R.id.cityName);
        schoolName = findViewById(R.id.schoolName);
        okBtn1 = findViewById(R.id.okBtn1);
        okBtn2 = findViewById(R.id.okBtn2);

        cityName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(getApplicationContext());
            }
        });

        // 학교 입력이 다 되었으면 다음 화면(급식메뉴)로 가기.
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }
}
