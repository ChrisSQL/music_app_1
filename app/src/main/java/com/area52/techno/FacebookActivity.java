package com.area52.techno;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.area52.techno.activities.MainActivity;
import com.area52.techno.firebaserecyclerviewtutorial.MainActivityList;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class FacebookActivity extends AppCompatActivity {

    private static final String TAG = "TAG";
    CallbackManager callbackManager;
    private static final String EMAIL = "email";
    LoginButton loginButton;
    TextView fbtext;
    String FbUserId;
    String FbName;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook);
        FacebookSdk.sdkInitialize(getApplicationContext());

        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        FbUserId = preferences.getString("id", "");;

    //    redirect();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                // Redirect to Main
                Intent myIntent = new Intent(FacebookActivity.this, MainActivityList.class);
                FacebookActivity.this.startActivity(myIntent);
            }
        }, 1000);   //5 seconds

    }

    private void redirect() {
        if (!isNetworkAvailable()) {

            Intent myIntent = new Intent(FacebookActivity.this, MainActivityList.class);
            FacebookActivity.this.startActivity(myIntent);

        } else if (isNetworkAvailable()){

            if(FbUserId.length() > 0)
            {
                Intent myIntent = new Intent(FacebookActivity.this, MainActivityList.class);
                FacebookActivity.this.startActivity(myIntent);
            }

            //Toast.makeText(this, FacebookSdk.getApplicationSignature(this), Toast.LENGTH_LONG).show();

            //textfb.setText(FacebookSdk.getApplicationSignature(this));
            callbackManager = CallbackManager.Factory.create();
            loginButton = (LoginButton) findViewById(R.id.login_button);
            loginButton.setReadPermissions(Arrays.asList("email","public_profile","user_friends"));



            // Callback registration
            loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                @Override
                public void onSuccess(LoginResult loginResult) {
                    // App code

                    requestUserProfile(loginResult);

                }

                @Override
                public void onCancel() {
                    // App code
                    Toast.makeText(FacebookActivity.this, "Cancelled", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onError(FacebookException error) {

                }
            });
        }
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void requestUserProfile(LoginResult loginResult){
        GraphRequest.newMeRequest(
                loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject me, GraphResponse response) {
                        if (response.getError() != null) {
                            // handle error
                        } else {
                            try {
                                String email = response.getJSONObject().get("email").toString();
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.putString("email", email);
                                editor.apply();
                                Log.e("Result", email);




                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            String id = me.optString("id");
                            String name = me.optString("name");
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("name", name);
                            editor.putString("id", id);
                            editor.apply();
                            // send email and id to your web server
                            Log.e("Result1", response.getRawResponse());
                            Log.e("Result", me.toString());

                        }
                    }
                }).executeAsync();

//        // checkFacebookAccessToken(loginResult.getAccessToken());
        Intent myIntent = new Intent(FacebookActivity.this, MainActivity.class);
//        //myIntent.putExtra("name", profile.getName()); //Optional parameters
        FacebookActivity.this.startActivity(myIntent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
}
