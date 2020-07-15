package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import kr.go.neis.api.School;

public class MainActivity extends AppCompatActivity {

    TextView daytv, foodText;
    Button dinner, breakfast, launch,
            riceBtn, alarmBtn;
    String schoolName, meal1, meal2, meal3 = "저녁"; // 학교 이름, 아침, 점심, 저녁
    int schoolRegion;
    School.Region region;
    int[] date = new int[3];
    String str = "사람 살려";
    String att = "아점저";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // id 매칭
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

        date = getDate();

        // 앞에서 가져온 거 받기
        Intent intent = getIntent();
        schoolName = intent.getStringExtra("schoolName");
        schoolRegion = intent.getIntExtra("schoolRegion", -1);

        
        SchoolMeal bap = new SchoolMeal();
        bap.execute();

        // 현재 연월일을 저장할 배열

        // '아침' 버튼을 누르면 오늘 아침 메뉴를 foodText에 나타내기.
        breakfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 아침 식사
                SchoolMeal meal = new SchoolMeal();
                att = "아침";
                meal.execute();
            }
        });

        // '점심' 버튼을 누르면 오늘 점심 메뉴를 foodText에 나타내기.
        launch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 점심 식사
                SchoolMeal meal = new SchoolMeal();
                att = "아침";
                meal.execute();
            }
        });

        // '저녁' 버튼을 누르면 오늘 저녁 메뉴를 foodText에 나타내기.
        dinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 저녁 식사
                SchoolMeal meal = new SchoolMeal();
                att = "저녁";
                meal.execute();
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
                Intent intent = new Intent(getApplicationContext(), com.example.myproject.AlarmActivity.class);
                startActivity(intent);
                finish();
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
    public static int[] getDate(){
        Date cure = Calendar.getInstance().getTime();
        String squ = new SimpleDateFormat("yyyy MM dd", Locale.getDefault()).format(cure);
        String res[] = squ.split(" ");

        int[] arr = new int[3];
        for(int i=0; i<=2; i++)
            arr[i] = Integer.parseInt(res[i]);
        return arr;
    }


    // Jsoup를 이용한 우리 학교 홈페이지 파싱
    class SchoolMeal extends AsyncTask<Void, Void, Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            // http://www.gsm.hs.kr/xboard/board.php?mode=list&tbnum=8&sCat=0&page=1&keyset=&searchword=&sYear=2020&sMonth=07
            // 여기 사이트
            // http://www.gsm.hs.kr/xboard/board.php?mode=list&tbnum=8&sCat=0&page=1&keyset=&searchword=&sYear="+date[0]+"&sMonth=" + date[1]

            try{
                String url = "http://www.gsm.hs.kr/xboard/board.php?mode=list&tbnum=8&sCat=0&page=1&keyset=&searchword=&sYear=2020&sMonth=07";
                Document doc = Jsoup.connect(url).get();
                str = "";
                Elements meal = doc.select("div.calendar").select("ul.day").select("div.food_list_box");
                Elements dayofweek = doc.select("div.calendar").select("ul.day").select("div.food_list_box").
                        select("div.day_num");
                Elements today = doc.select("li.today");
                //String[] ad = dayofweek.text().split("<span>");

                //select("div");//.select("a[title=아침]").select("span.content");
                // date: 17<p>(월)</p> 이런 식이다. 그래서 <p>를 제거하고 싶다...

                //Document html = Jsoup.parse(date.html());

                /*for(Element elements : dayofweek){
                    String today = ""+date[2] + getDayOfWeek();    // 오늘날짜와 요일을 today에 저장.

                    if(today.equals(elements.text())){ // 급식 날짜와 현재 날짜가 같나요

                        break;
                    }
                }*/
               /* str += today.select("div a[title=아침]").select("span.content").text();
                str += today.select("div a[title=아침]").select("span.content").text();
                str += today.select("div a[title=아침]").select("span.content").text();*/
                str += today.select("a[title=아침]").text();

                for(Element e : today){
                    str += e.text() + "\n";
                }
                }
            catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            foodText.setText(str);
        }
    }

    public static String getDayOfWeek(){
        Calendar cal = Calendar.getInstance();
        String strWeek = null;

        int nWeek = cal.get(Calendar.DAY_OF_WEEK);

        if(nWeek == 1)
            strWeek = "(일)";
        else if(nWeek == 2)
            strWeek = "(월)";
        else if(nWeek == 3)
            strWeek = "(화)";
        else if(nWeek == 4)
            strWeek = "(수)";
        else if(nWeek == 5)
            strWeek = "(목)";
        else if(nWeek == 7)
            strWeek = "(금)";
        else if(nWeek == 8)
            strWeek = "(토)";
        return strWeek;
    }

}