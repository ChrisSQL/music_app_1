package com.area52.techno;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class MyFirebaseInstanceIdService extends FirebaseInstanceIdService {

    SharedPreferences sharedPreferences1;
    SharedPreferences.Editor editor1;
    private static final String PREF_NAME1 = "prefs_token";
    private static final String KEY_FCM = "devices_token";
    //this method will be called
//when the token is generated
    @Override
    public void onTokenRefresh() {
        sharedPreferences1 = getSharedPreferences(PREF_NAME1, Context.MODE_PRIVATE);
        editor1 = sharedPreferences1.edit();
        super.onTokenRefresh();

        //now we will have the token
        String token = FirebaseInstanceId.getInstance().getToken();
        editor1.putString(KEY_FCM,token.toString());
        editor1.apply();

        //for now we are displaying the token in the log
        //copy it as this method is called only when the new token is generated
        //and usually new token is only generated when the app is reinstalled or the data is cleared
        Log.d("MyRefreshedToken", token);
        String device_token = sharedPreferences1.getString(KEY_FCM, "");

    }

}
