package com.area52.techno.youtube;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.area52.techno.R;
import com.area52.techno.activities.PlatformsActivity;
import com.area52.techno.festivals.ADE;
import com.area52.techno.festivals.AwakeningsActivity;
import com.area52.techno.festivals.FuturActivity;
import com.area52.techno.festivals.LuminosityActivity;
import com.area52.techno.festivals.PrintworksActivity;
import com.area52.techno.festivals.TimeWarpActivity;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.youtube.YouTube;

/**
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public class YouTubeActivityPlaylist extends AppCompatActivity {


    private YouTube mYoutubeDataApi;
    private final GsonFactory mJsonFactory = new GsonFactory();
    private final HttpTransport mTransport = AndroidHttp.newCompatibleTransport();
    String[] PlaylistArray;
    String playlist, festival;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.youtube_activity);

        intent = getIntent();
        playlist = intent.getStringExtra("playlist");
        PlaylistArray = new String[]{playlist};
        festival = intent.getStringExtra("festival");

        Toast.makeText(this, festival, Toast.LENGTH_SHORT).show();

        Toolbar toolbar = findViewById(R.id.toolbarEvents);
        setSupportActionBar(toolbar);

        
        if(!isConnected()){
            Toast.makeText(YouTubeActivityPlaylist.this,"No Internet Connection Detected",Toast.LENGTH_LONG).show();
        }
        
        if (ApiKey.YOUTUBE_API_KEY.startsWith("YOUR_API_KEY")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setMessage("Edit ApiKey.java and replace \"YOUR_API_KEY\" with your Applications Browser API Key")
                        .setTitle("Missing API Key")
                        .setNeutralButton("Ok, I got it!", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                            }
                        });

            AlertDialog dialog = builder.create();
            dialog.show();

        } else if (savedInstanceState == null) {
            mYoutubeDataApi = new YouTube.Builder(mTransport, mJsonFactory, null)
                    .setApplicationName(getResources().getString(R.string.app_name))
                    .build();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, YouTubeRecyclerViewFragment.newInstance(mYoutubeDataApi, PlaylistArray))
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
     //   getMenuInflater().inflate(R.menu.menu_search, menu);
        return true;
    }
    
    public boolean isConnected() {
        ConnectivityManager cm =
            (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_recyclerview) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, YouTubeRecyclerViewFragment.newInstance(mYoutubeDataApi, PlaylistArray))
                    .commit();
            return true;
        }
        return super.onOptionsItemSelected(item);
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

        switch (festival) {
            case "Awakenings": {
                Intent i = new Intent(YouTubeActivityPlaylist.this, AwakeningsActivity.class);
                i.putExtra("festival", festival);
                YouTubeActivityPlaylist.this.startActivity(i);
                break;
            }
            case "TimeWarp": {
                Intent i = new Intent(YouTubeActivityPlaylist.this, TimeWarpActivity.class);
                i.putExtra("festival", festival);
                YouTubeActivityPlaylist.this.startActivity(i);
                break;
            }
            case "Futur": {
                Intent i = new Intent(YouTubeActivityPlaylist.this, FuturActivity.class);
                i.putExtra("festival", festival);
                YouTubeActivityPlaylist.this.startActivity(i);
                break;
            }
            case "Printworks": {
                Intent i = new Intent(YouTubeActivityPlaylist.this, PrintworksActivity.class);
                i.putExtra("festival", festival);
                YouTubeActivityPlaylist.this.startActivity(i);
                break;
            }
            case "ADE": {
                Intent i = new Intent(YouTubeActivityPlaylist.this, ADE.class);
                i.putExtra("festival", festival);
                YouTubeActivityPlaylist.this.startActivity(i);
                break;
            }
            case "Lumo": {
                Intent i = new Intent(YouTubeActivityPlaylist.this, LuminosityActivity.class);
                i.putExtra("festival", festival);
                YouTubeActivityPlaylist.this.startActivity(i);
                break;
            }
            default:
                Toast.makeText(YouTubeActivityPlaylist.this, "Festival Not Added Yet.", Toast.LENGTH_SHORT).show();
                break;
        }

    }
}
