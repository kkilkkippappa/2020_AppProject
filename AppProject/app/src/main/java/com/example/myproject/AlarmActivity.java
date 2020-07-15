package com.example.myproject;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AlarmActivity extends AppCompatActivity {

    Button riceBtn2, alarmBtn2;
    ListView lv;
    AlarmManager alarmManager;
    int selNum = 0; //lv의 인덱스값.
    int hour, min;
    long interval = 1000 * 60 * 60 * 24;    //하루의 시간을 밀리초로 나타냄.


    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        lv = findViewById(R.id.alarmLv);
        riceBtn2 = findViewById(R.id.riceBtn2);
        alarmBtn2 = findViewById(R.id.alarmBtn2);


        // 받아온 날짜를 yyyy년 mm월 dd일 형식으로 표현해준다.
        long now = System.currentTimeMillis();
        final Date date = new Date(now);
        final SimpleDateFormat Month = new SimpleDateFormat("MM", Locale.getDefault());
        final SimpleDateFormat Year = new SimpleDateFormat("yyyy", Locale.getDefault());

        // 알람 시간 설정.
        final Calendar calendar = Calendar.getInstance();
        final long currentTime = calendar.getTimeInMillis();  // 캘린더를 부를 때 생성된 시간 -> 알람 부를 때 생성된 시간.
        long Calandertime = System.currentTimeMillis(); // 현재 시간

        // 만약 내가 설정한 시간이 현재 시간보다 작다면 알람이 울린다.
        if(currentTime > Calandertime){
            Calandertime += interval;   // 하루를 더 더해준다.
        }

        final ArrayList<String> dataSet = new ArrayList<>();    // lv에 넣을 데이터 배열.

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, dataSet);

        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
        // 리스트를 길게 누르는 경우 -> 삭제
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                dataSet.remove(i);
                adapter.notifyDataSetChanged();
                return true;
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });


        riceBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        // 알람 추가버튼 -> lv 추가.
        alarmBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AlarmActivity.this, "추가?", Toast.LENGTH_SHORT).show();
                AlertDialog.Builder dlg = new AlertDialog.Builder(AlarmActivity.this);
                final TimePicker timePicker = new TimePicker(getApplicationContext());
                dlg.setView(timePicker);
                dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        hour = timePicker.getHour();    //현재 시간
                        min = timePicker.getMinute();   // 현재 분
                        dataSet.add("알람"+(selNum+1) + " " + hour + "시 " + min + "분");
                        selNum++;
                        long AlarmTime = calendar.getTimeInMillis();
                        adapter.notifyDataSetChanged();
                        Toast.makeText(AlarmActivity.this, "" + selNum, Toast.LENGTH_SHORT).show();
                        //Toast.makeText(AlarmActivity.this, ""+Calendar.YEAR + " " + Integer.parseInt(Year.format(currentTime)), Toast.LENGTH_SHORT)
                          //      .show();    // 테스트용
                        regist(getApplicationContext());
                    }
                });
                dlg.setNegativeButton("취소", null);
                dlg.show();

            }
        });
    }

    public void regist(Context context){
        Log.e("TAG", "알람 가요가요");
        Intent intent = new Intent(context, AlarmManager.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);

        Calendar currentCalendar = Calendar.getInstance();
        currentCalendar.setTimeInMillis(System.currentTimeMillis());
        currentCalendar.set(Calendar.HOUR_OF_DAY, hour);
        currentCalendar.set(Calendar.MINUTE, min);
        currentCalendar.set(Calendar.SECOND, 0);
        currentCalendar.set(Calendar.MILLISECOND, 0);


        // 지정한 시간에 매일 알림을 준다.
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, currentCalendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
    }
    // 알람 취소
    public void unregist(){
        Intent intent = new Intent(this, AlarmManager.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        alarmManager.cancel(pendingIntent);
    }


}