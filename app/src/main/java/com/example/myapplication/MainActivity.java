package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btGet = findViewById(R.id.button_GET);

        //設定按鈕按下執行副程式
        btGet.setOnClickListener(v -> {
            GET();
        });



    }
    private void GET() {
        TextView tvRes = findViewById(R.id.text_Respond);
        //連線
        OkHttpClient client = new OkHttpClient().newBuilder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
                .build();
        //傳送
        Request request = new Request.Builder()
                .url("https://data.ntpc.gov.tw/api/datasets/28AB4122-60E1-4065-98E5-ABCCB69AACA6/json/preview")
//                .header("Cookie","")
//                .addHeader("","")
                .build();
        //回傳
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

                tvRes.setText(e.getMessage());
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                tvRes.setText("GET回傳：\n" + response.body().string());
            }
        });
    }


}



