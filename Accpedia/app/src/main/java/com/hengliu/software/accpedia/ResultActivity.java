package com.hengliu.software.accpedia;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        final String ScreachText = getIntent().getStringExtra("SreachText");
        final EditText ScreachEditText = (EditText)findViewById(R.id.ScreachEditText_2);
        Log.i("搜索",ScreachText);
        ScreachEditText.setText(ScreachText);
        LinearLayout wiki = (LinearLayout) findViewById(R.id.wikiLayout);
        LinearLayout baidu = (LinearLayout) findViewById(R.id.baiduLayout);
        LinearLayout hudong = (LinearLayout) findViewById(R.id.HudongLayout);
        LinearLayout sll = (LinearLayout) findViewById(R.id.SLLLayout);
        LinearLayout bing = (LinearLayout) findViewById(R.id.BingLayout);

        final CheckBox FlowCheckBox = (CheckBox)findViewById(R.id.flowCheckBox);
        final SharedPreferences sp =getSharedPreferences("Accpedia",MODE_PRIVATE);
        Boolean ifFlow = sp.getBoolean("flow",false);

        FlowCheckBox.setChecked(ifFlow);
        FlowCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor editor = sp.edit();
                editor.putBoolean("flow",FlowCheckBox.isChecked());
                editor.apply();
            }
        });
        wiki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent article = new Intent(ResultActivity.this, com.hengliu.software.accpedia.article.class);
                article.putExtra("url","https://zh.m.wikipedia.org/wiki/"+ScreachText);
                article.putExtra("SreachText",ScreachText);
                article.putExtra("flow",FlowCheckBox.isChecked());
                startActivity(article);
            }
        });
        baidu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent article = new Intent(ResultActivity.this, com.hengliu.software.accpedia.article.class);
                article.putExtra("url","https://baike.baidu.com/item/"+ScreachText);
                article.putExtra("SreachText",ScreachText);
                article.putExtra("flow",FlowCheckBox.isChecked());
                startActivity(article);
            }
        });
        hudong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent article = new Intent(ResultActivity.this, com.hengliu.software.accpedia.article.class);
                article.putExtra("url","http://www.baike.com/wiki/"+ScreachText);
                article.putExtra("SreachText",ScreachText);
                article.putExtra("flow",FlowCheckBox.isChecked());
                startActivity(article);
            }
        });
        sll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent article = new Intent(ResultActivity.this, com.hengliu.software.accpedia.article.class);
                article.putExtra("url","https://baike.so.com/doc/search?word="+ScreachText);
                article.putExtra("SreachText",ScreachText);
                article.putExtra("flow",FlowCheckBox.isChecked());
                startActivity(article);
            }
        });
        bing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent article = new Intent(ResultActivity.this, com.hengliu.software.accpedia.article.class);
                article.putExtra("url","https://www.bing.com/knows/search?q="+ScreachText+"&mkt=zh-cn");
                article.putExtra("SreachText",ScreachText);
                article.putExtra("flow",FlowCheckBox.isChecked());
                startActivity(article);
            }
        });


        Button button = (Button)findViewById(R.id.button_r);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String reScreachText = ScreachEditText.getText().toString();
                if(reScreachText.isEmpty())return;
                Intent intent = new Intent(ResultActivity.this,ResultActivity.class);
                intent.putExtra("SreachText",reScreachText);
                startActivity(intent);
                finish();
            }
        });

    }
}
