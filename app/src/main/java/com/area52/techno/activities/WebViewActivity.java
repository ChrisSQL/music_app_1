package com.area52.techno.activities;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.area52.techno.R;

public class WebViewActivity extends AppCompatActivity {

    WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        Bundle extras = getIntent().getExtras();
        String id = extras.getString("id");

        mWebView = (WebView) findViewById(R.id.videoView);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setPluginState(WebSettings.PluginState.ON);
        mWebView.loadUrl("https://www.youtube.com/embed/" + id + "?rel=0&autoplay=1");

        mWebView.setWebChromeClient(new WebChromeClient());

    }
}
