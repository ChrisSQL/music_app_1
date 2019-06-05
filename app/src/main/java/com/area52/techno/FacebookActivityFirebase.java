package com.area52.techno;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.area52.techno.activities.BaseActivity;
import com.area52.techno.activities.MainActivity;
import com.area52.techno.dj.MyDJActivity;
import com.area52.techno.dj.MyDJActivityBranch;
import com.area52.techno.models.User;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.json.JSONException;
import org.json.JSONObject;

import io.branch.referral.Branch;
import io.branch.referral.BranchError;

/**
 * Demonstrate Firebase Authentication using a Facebook access token.
 */
public class FacebookActivityFirebase extends BaseActivity implements
        View.OnClickListener {

    private static final String TAG = "FacebookLogin";
    private FirebaseFirestore db;
    private DatabaseReference mDatabase;

    private TextView mStatusTextView;
    private TextView mDetailTextView;

    private User userRegistering;

    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]

    private CallbackManager mCallbackManager;

    Branch branch;
    String dj = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook_firebase);

        userRegistering = new User("12345","67890","NA","NA","NA","none");

        // Views
        mStatusTextView = findViewById(R.id.status);
        mDetailTextView = findViewById(R.id.detail);
        findViewById(R.id.button_facebook_signout).setOnClickListener(this);

        // [START initialize_auth]
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        // [END initialize_auth]

        // [START initialize_fblogin]
        // Initialize Facebook Login button
        mCallbackManager = CallbackManager.Factory.create();
        LoginButton loginButton = findViewById(R.id.button_facebook_login);
        loginButton.setReadPermissions("email", "public_profile");
        loginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG, "facebook:onSuccess:" + loginResult);
                getUserInfo(loginResult);
            }

            @Override
            public void onCancel() {
                Log.d(TAG, "facebook:onCancel");
                // [START_EXCLUDE]
                updateUI(null);
                // [END_EXCLUDE]
            }

            @Override
            public void onError(FacebookException error) {
                Log.d(TAG, "facebook:onError", error);
                // [START_EXCLUDE]
                updateUI(null);
                // [END_EXCLUDE]
            }
        });
        // [END initialize_fblogin]

//        addUser();
//        getUser();
    }

    public void addUser() {
        // [START add_alan_turing]
        // Create a new user with a first, middle, and last name
//        Map<String, Object> dj = new HashMap<>();
//        dj.put("name","Darren Connolly");
//        dj.put("DJName","Klass A");
//        dj.put("PhotoUrl","https://scontent-dub4-1.xx.fbcdn.net/v/t1.0-1/p200x200/29214255_1831245023560379_6374899045533483008_n.jpg?_nc_cat=0&oh=44447bbf64f1a5ffa78f5668445fc09a&oe=5C37AD54");
//        dj.put("djLogoImage","https://scontent-dub4-1.xx.fbcdn.net/v/t1.0-9/29598346_1845750375443177_3974877216001194475_n.jpg?_nc_cat=0&oh=ec33225974a9be47d80eaf5ae1270f08&oe=5C31D26E");
//        dj.put("county","Tipperary");
//        dj.put("country","Ireland");
//        dj.put("email","connodx4@gmail.com");
//        dj.put("phoneNumber","0852301560");
//        dj.put("facebookUserID","");
//        dj.put("bio","This is a short bio");
//        dj.put("soundcloudLink","");
//        dj.put("youtubeLink","");
//        dj.put("mixcloudLink","https://www.mixcloud.com/darrenconnolly91/");
//        dj.put("facebookLink","https://www.facebook.com/DarrenConnolly91");
//        dj.put("instagramLink","https://www.instagram.com/dj.klass.a/");
//        dj.put("spotifyLink","");
//        dj.put("bookingEmail","");
//        dj.put("genre", Arrays.asList("Tech Trance","Techno"));
//
//        // Add a new document with a generated ID
//        db.collection("users")
//                .add(dj)
//                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                    @Override
//                    public void onSuccess(DocumentReference documentReference) {
//                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getid());
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.w(TAG, "Error adding document", e);
//                    }
//                });
//        // [END add_alan_turing]
    }

    public void getUser(){

        db.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());

                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    // [START on_start_check_user]
    @Override
    public void onStart() {
        super.onStart();

        // BRANCH
        branch = Branch.getInstance(getApplicationContext());
        // Branch init
        branch.initSession(new Branch.BranchReferralInitListener() {
            @Override
            public void onInitFinished(JSONObject referringParams, BranchError error) {
                if (error == null) {
                    // params are the deep linked params associated with the link that the user clicked -> was re-directed to this app
                    // params will be empty if no data found
                    // ... insert custom logic here ...
                    dj = referringParams.optString("dj", "none");

                    SharedPreferences sharedPreferences = getSharedPreferences("prefs", Context.MODE_PRIVATE);
                    SharedPreferences.Editor edt = sharedPreferences.edit();
                    edt.putString("djReferral", dj);
                    edt.apply();

                    Log.i("BRANCH SDK", referringParams.toString());
                    // Toast.makeText(FacebookActivityFirebase.this, referringParams.toString(), Toast.LENGTH_LONG).show();
                } else {
                    Log.i("BRANCH SDK", error.getMessage());
                }
            }
        }, this.getIntent().getData(), this);


        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        // Check auth on Activity start
        if (mAuth.getCurrentUser() != null) {
            onAuthSuccess(mAuth.getCurrentUser());
        }
        updateUI(currentUser);

    }
    // [END on_start_check_user]

    // [START on_activity_result]
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Pass the activity result back to the Facebook SDK
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }
    // [END on_activity_result]

    // [START auth_with_facebook]
    private void handleFacebookAccessToken(AccessToken token) {
        Log.d(TAG, "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            userRegistering.setuID(user.getUid());
                            mDatabase.child("usersID").child(userRegistering.getuID()).setValue(userRegistering);
                            mDatabase.child("usersEmail").child(encodeUserEmail(userRegistering.getEmail())).setValue(userRegistering);
                            mDatabase.child("usersUsername").child(userRegistering.getName()).setValue(userRegistering);
                            // mDatabase.child("users").child(userId).child("username").setValue(name);
                            // mStatusTextView.setText("Connected");
                            // showProgressDialog();
                            updateUI(user);
//                            new Handler().postDelayed(new Runnable() {
//                                @Override
//                                public void run() {
//
//                                }
//                            }, 20);

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(FacebookActivityFirebase.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // [START_EXCLUDE]
                        //    hideProgressDialog();
                        // [END_EXCLUDE]
                    }
                });
    }
    // [END auth_with_facebook]

    public void signOut() {
        mAuth.signOut();
        LoginManager.getInstance().logOut();

        updateUI(null);
    }

    private void updateUI(FirebaseUser user) {
        //    hideProgressDialog();
        if (user != null) {

            //    mStatusTextView.setText(getString(R.string.facebook_status_fmt, user.getDisplayName()));
            //    mDetailTextView.setText(getString(R.string.firebase_status_fmt, user.getUid()));

            findViewById(R.id.button_facebook_login).setVisibility(View.GONE);
            findViewById(R.id.button_facebook_signout).setVisibility(View.GONE);

            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);

        } else {
            //    mStatusTextView.setText(R.string.signed_out);
            mDetailTextView.setText(null);

            findViewById(R.id.button_facebook_login).setVisibility(View.VISIBLE);
            findViewById(R.id.button_facebook_signout).setVisibility(View.GONE);

        }
    }

    protected void getUserInfo(final LoginResult login_result){

        GraphRequest data_request = GraphRequest.newMeRequest(
                login_result.getAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(
                            JSONObject object,
                            GraphResponse response) {
                        try {

                            String facebook_id = object.getString("id");
                            String f_name = object.getString("name");
                            String email_id = object.getString("email");
                        //    String email_cleaned =
                            String token = login_result.getAccessToken().getToken();
                            String picUrl = "https://graph.facebook.com/me/picture?type=large&method=GET&access_token="+ token;

                            userRegistering.setFbID(facebook_id);
                            userRegistering.setName(f_name);
                            userRegistering.setEmail(email_id);
                            userRegistering.setPhotoUrl(picUrl);
                            userRegistering.setRefDJ(dj);

                            saveFacebookCredentialsInFirebase(login_result.getAccessToken());

                        } catch (JSONException e) {
                            // TODO Auto-generated catch block
                        }
                    }
                });
        Bundle permission_param = new Bundle();
        permission_param.putString("fields", "id,name,email,picture.width(500).height(500)");
        data_request.setParameters(permission_param);
        data_request.executeAsync();
        data_request.executeAsync();
    }

    private void saveFacebookCredentialsInFirebase(AccessToken accessToken){
        AuthCredential credential = FacebookAuthProvider.getCredential(accessToken.getToken());
        mAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(!task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Error logging in", Toast.LENGTH_LONG).show();
                }else{
                //    Toast.makeText(getApplicationContext(),"Logging in...", Toast.LENGTH_LONG).show();

                    handleFacebookAccessToken(accessToken);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.button_facebook_signout) {
            signOut();
        }
    }

    private void onAuthSuccess(FirebaseUser user) {

    }

    static String encodeUserEmail(String userEmail) {
        return userEmail.replace(".", ",");
    }

    static String decodeUserEmail(String userEmail) {
        return userEmail.replace(",", ".");
    }
}
