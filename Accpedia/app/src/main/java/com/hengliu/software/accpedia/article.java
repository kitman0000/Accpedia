package com.hengliu.software.accpedia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.EventLog;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class article extends AppCompatActivity {

    WebView webPage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        String URL = getIntent().getStringExtra("url");
        final String ScreachText = getIntent().getStringExtra("SreachText");
        final EditText ScreachEditText = (EditText)findViewById(R.id.ScreachEditText2);
        ScreachEditText.setText(ScreachText);
        ScreachEditText.setText(ScreachText);
        //再次搜索按钮
        Button button = (Button)findViewById(R.id.button2);
        final Intent intent = new Intent(article.this,ResultActivity.class);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ScreachText = ScreachEditText.getText().toString();
                if(ScreachText.isEmpty())return;
                intent.putExtra("SreachText",ScreachText);
                startActivity(intent);
            }
        });
        //返回按钮
        Button BackButton = (Button)findViewById(R.id.backButton);
        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        webPage = (WebView) findViewById(R.id.webpage);
        webPage.getSettings().setJavaScriptEnabled(true);
        webPage.getSettings().setSupportZoom(true);
        webPage.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webPage.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        Boolean ifFlow = intent.getBooleanExtra("flow",false);
        webPage.getSettings().setLoadWithOverviewMode(!ifFlow);
        webPage.setWebViewClient(new WebViewClient());
        webPage.loadUrl(URL);
        //动态设置标题栏
        webPage.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                ontouch(event);
                return false;
            }
        });

    }
    float startY = 0;
    float endY = 0;
    private void ontouch(MotionEvent event)
    {
        //Log.i("actionEvent",String.valueOf(event.getAction()));
        LinearLayout titleView = (LinearLayout) findViewById(R.id.title);
        if(event.getAction() == MotionEvent.ACTION_DOWN)
        {
            startY = event.getRawY();
            Log.i("startY:",String.valueOf(startY));
        }
        else if (event.getAction() == MotionEvent.ACTION_CANCEL)
        {
            endY = event.getRawY();
            Log.i("endY",String.valueOf(endY));
            if(endY - startY<10)
            {
                titleView.setVisibility(View.GONE);
                Log.i("标题栏","关闭");
            }
            else if(endY-startY >=0)
            {
                titleView.setVisibility(View.VISIBLE);
                Log.i("标题栏","展开");
            }
        }

    }

}
