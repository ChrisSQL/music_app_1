package com.area52.techno.activities;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.area52.techno.R;
import com.area52.techno.youtube.YouTubeActivityPlaylist;

public class EventsActivity extends AppCompatActivity {

    private TextView mTextMessage;
    //private WebView mWebview ;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {

                case R.id.navigation_poster:
                    startActivity(new Intent(EventsActivity.this, EventsActivity.class));
                    return true;
                case R.id.navigation_tickets:
                    Intent intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://www.eventbrite.ie/e/tnt-presents-mark-greene-klass-a-and-scan-tickets-46947291613#tickets"));
                    startActivity(intent);
                    return true;
                case R.id.navigation_music:
//                    mTextMessage.setText("Music");

                    Intent i=new Intent(EventsActivity.this,YouTubeActivityPlaylist.class);
                    i.putExtra("playlist", "PLl5zfufKY1Z2lbY9SN6XFLgOPzzJQmO9J");
                    EventsActivity.this.startActivity(i);

                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        // webviewSetup();



    }

    private void webviewSetup() {
//        mWebview  = new WebView(this);
//        mWebview.getSettings().setJavaScriptEnabled(true); // enable javascript
//        final Activity activity = this;
//        mWebview.setWebViewClient(new WebViewClient() {
//            @SuppressWarnings("deprecation")
//            @Override
//            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
//                Toast.makeText(activity, description, Toast.LENGTH_SHORT).show();
//            }
//            @TargetApi(android.os.Build.VERSION_CODES.M)
//            @Override
//            public void onReceivedError(WebView view, WebResourceRequest req, WebResourceError rerr) {
//                // Redirect to deprecated method, so you can use it in all SDK versions
//                onReceivedError(view, rerr.getErrorCode(), rerr.getDescription().toString(), req.getUrl().toString());
//            }
//        });
//
//        mWebview .loadUrl("https://www.facebook.com/events/179268299404305/");
//        setContentView(mWebview );
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (Integer.parseInt(android.os.Build.VERSION.SDK) > 5
                && keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            Log.d("CDA", "onKeyDown Called");
            onBackPressed();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(EventsActivity.this, MainActivity.class));
    }

}
