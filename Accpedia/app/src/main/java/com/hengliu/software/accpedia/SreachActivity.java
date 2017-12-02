package com.hengliu.software.accpedia;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;

import java.util.Random;

public class SreachActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sreach);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_Screach);
        setSupportActionBar(toolbar);
        toolbar.setTitle("精准百科");
        toolbar.setTitleTextColor(Color.WHITE);
        final Button ScreachButton = (Button)findViewById(R.id.ScreachButton_1);
        final EditText ScreachEditText = (EditText)findViewById(R.id.ScreachEditText_1);
        ScreachButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ScreachText = ScreachEditText.getText().toString();
                if(ScreachText.isEmpty())return;
                Intent intent = new Intent(SreachActivity.this,ResultActivity.class);
                intent.putExtra("SreachText",ScreachText);
                startActivity(intent);
                finish();
            }
        });
        //设置背景
        Random random = new Random();
        int e = random.nextInt(2);
        Drawable drawable = null;
        switch (e){
            case 0:{
                 drawable =  getResources().getDrawable(R.drawable.bk3);
                break;
            }
            case 1:{
                 drawable =  getResources().getDrawable(R.drawable.bk4);
                 ScreachEditText.setTextColor(Color.argb(255,230,230,230));
                break;
            }
        }
        RelativeLayout mainBack = (RelativeLayout)findViewById(R.id.mainback);
        mainBack.setBackground(drawable);

    }
}
