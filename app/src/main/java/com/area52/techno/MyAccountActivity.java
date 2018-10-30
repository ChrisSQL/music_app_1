package com.area52.techno;

           // Get user details identified by userID
//        // Required
//        String name;
//        String PhotoUrl;
//        String djLogoImage;
//        String county;
//        String country;
//        String email;
//        String phoneNumber;

//        // Optional
//        String facebookUserID;
//        String bio;
//        String soundcloudLink;
//        String youtubeLink;
//        String mixcloudLink;
//        String facebookLink;
//        String instagramLink;
//        String spotifyLink;
//        String bookingEmail;
//        String genre;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.area52.techno.activities.MainActivity;
import com.area52.techno.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.List;

import static junit.framework.Assert.fail;

public class MyAccountActivity extends AppCompatActivity {

    SharedPreferences preferences;
    TextView FBName, ProfileName;
    String photoUrl, profileURL, userID, facebookUserId;
    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]
    private FirebaseFirestore db;
    FirebaseUser user;
    FirebaseDatabase database;
    DatabaseReference myRef ;
    List<User> list;
    ImageView profilePicture;
    LinearLayout profileBackground;
    Bundle extras;
    Query userQuery;
    User userProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        // Get userID
        declareVariables();
        // Choose User
        chooseUSer();

    }

    private void chooseUSer() {

        if(userID == null){
            Toast.makeText(this, "UserID : Null", Toast.LENGTH_SHORT).show();
            Intent myIntent = new Intent(MyAccountActivity.this, FacebookActivityFirebase.class);
        }else if (userID.equals("NA")){
            Toast.makeText(this, "UserID : NA", Toast.LENGTH_SHORT).show();
            Intent myIntent = new Intent(MyAccountActivity.this, FacebookActivityFirebase.class);
        }else {
            // Toast.makeText(this, "UserID : " + userID, Toast.LENGTH_SHORT).show();
            populateUser(userID);
        }
    }

    private void populateUser(String userIDIn) {



//        String uid = mAuth.getCurrentUser().getUid();
//        myRef.child("students").child(uid).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                try {
//                    for(DataSnapshot snap :  dataSnapshot.getChildren())
//                        User obj = snap.getValue(User.class);
//                    String password =obj.getPassword();
//                    //here compare your local password inserted in your editText with the one pulled from firebase
//
//
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }

        Query myTopPostsQuery = myRef.child(userIDIn);

        myTopPostsQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                User user = dataSnapshot.getValue(User.class);

                updateGUI(user);

//                //get country key
//                String userPhoto = user.getPhotoUrl();
//                String userEmail = user.getEmail();
//                String userName = user.getName();
//                String userFBID = user.getFbID();
//                String userGUid = user.getuID();
//
//                Toast.makeText(MyAccountActivity.this, userPhoto, Toast.LENGTH_SHORT).show();
//                Toast.makeText(MyAccountActivity.this, userEmail, Toast.LENGTH_SHORT).show();
//                Toast.makeText(MyAccountActivity.this, userName, Toast.LENGTH_SHORT).show();
//                Toast.makeText(MyAccountActivity.this, userFBID, Toast.LENGTH_SHORT).show();
//                Toast.makeText(MyAccountActivity.this, userGUid, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                fail("Failed to get country: " + databaseError.getMessage());

            }
        });

//        Toast.makeText(this, userIDIn, Toast.LENGTH_SHORT).show();
//
//        userQuery = database.getReference("usersID").equalTo(userIDIn);
//
//        //userQuery = FirebaseDatabase.getInstance().getReference("usersID").child(userIDIn);
//        userQuery.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot userSnapshot: dataSnapshot.getChildren()) {
//
//                    userProfile = userSnapshot.getValue(User.class);
//                    updateGUI(userProfile);
//
//
//
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                fail("Failed to get country: " + databaseError.getMessage());
//            }
//        });
    }

    private void updateGUI(User userProfile) {

        profileURL = "https://facebook.com/profile.php?id=" + userProfile.getFbID();

        ProfileName.setText(userProfile.getName());
        PicassoImageLoad(profilePicture, profileBackground, ProfileName, userProfile.getPhotoUrl());

        final ImageView profileFacebookButton = (ImageView) findViewById(R.id.profileFacebookButton);
        profileFacebookButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(profileURL));
                startActivity(i);

            }
        });

        final ImageView profileTicketButton = (ImageView) findViewById(R.id.profileTicketButton);
        profileTicketButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click

                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, profileURL);
                startActivity(Intent.createChooser(shareIntent, "Share link using"));

            }
        });

        final ImageView profileShareButton = (ImageView) findViewById(R.id.profileShareButton);
        profileShareButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click

                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, profileURL);
                startActivity(Intent.createChooser(shareIntent, "Share link using"));

            }
        });

        Picasso.with(MyAccountActivity.this)
                .load(userProfile.getPhotoUrl())
                .placeholder(R.drawable.sesh_logo)
                .into(profilePicture);

    }

    private void declareVariables() {




        profilePicture = (ImageView) findViewById(R.id.friendProfilePicture);
        profileBackground = (LinearLayout) findViewById(R.id.profileBackground);
        ProfileName = (TextView) findViewById(R.id.ProfileName);

        facebookUserId = "";
        db = FirebaseFirestore.getInstance();
        database = FirebaseDatabase.getInstance();

        // Get Firebase User
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        extras = getIntent().getExtras();
        if (extras != null) {
            userID = extras.getString("userID");
        }else {
            userID = "NA";
        }

        myRef = database.getReference("usersID");
    }

    private void loadUser(FirebaseUser user) {

        Toast.makeText(this, userID, Toast.LENGTH_SHORT).show();

//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // This method is called once with the initial value and again
//                // whenever data at this location is updated.
//                list = new ArrayList<User>();
//                for(DataSnapshot dataSnapshot1 :dataSnapshot.getChildren()){
//
//                    User value = dataSnapshot1.getValue(User.class);
//                    User fire = new User();
//                    String name = value.getName();
//                    String email = value.getName();
//                    String photo = value.getPhotoUrl();
//                    fire.setName(name);
//                    fire.setEmail(email);
//                    fire.setPhotoUrl(photo);
//                    list.add(fire);
//
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
//                Log.w("Hello", "Failed to read value.", error.toException());
//            }
//
//
//        });

//        myRef.child("ixU1tYuBERVaiApthSErIJptPVt2").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot2) {
//
//                list = new ArrayList<User>();
//                for(DataSnapshot dataSnapshot1 :dataSnapshot2.getChildren()){
//
//                    User value = dataSnapshot1.getValue(User.class);
//                    User fire = new User();
//                    String name = value.getName();
//                    String email = value.getName();
//                    String photo = value.getPhotoUrl();
//                    fire.setName(name);
//                    fire.setEmail(email);
//                    fire.setPhotoUrl(photo);
//                    list.add(fire);
//
//                }
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                // ...
//            }
//        });

//        // find the Facebook profile and get the user's uID
////        for(UserInfo profile : user.getProviderData()) {
////            // check if the provider uID matches "facebook.com"
////            if(FacebookAuthProvider.PROVIDER_ID.equals(profile.getProviderId())) {
////                facebookUserId = profile.getUid();
////            }
////        }

        // construct the URL to the profile picture, with a custom height
        // alternatively, use '?type=small|medium|large' instead of ?height=
        photoUrl = "https://graph.facebook.com/" + list.get(0).getFbID() + "/picture?height=200";
        profileURL = "https://graph.facebook.com/" + list.get(0).getFbID();

        ProfileName.setText(list.get(0).getName());
        PicassoImageLoad(profilePicture, profileBackground, ProfileName, list.get(0).getPhotoUrl());

        final ImageView profileFacebookButton = (ImageView) findViewById(R.id.profileFacebookButton);
        profileFacebookButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(profileURL));
                startActivity(i);

            }
        });

        final ImageView profileTicketButton = (ImageView) findViewById(R.id.profileTicketButton);
        profileTicketButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click

                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, profileURL);
                startActivity(Intent.createChooser(shareIntent, "Share link using"));

            }
        });

        final ImageView profileShareButton = (ImageView) findViewById(R.id.profileShareButton);
        profileShareButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click

                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, profileURL);
                startActivity(Intent.createChooser(shareIntent, "Share link using"));

            }
        });

        Picasso.with(MyAccountActivity.this)
                .load(photoUrl)
                .placeholder(R.drawable.sesh_logo)
                .into(profilePicture);

    }

    private void PicassoImageLoad(ImageView profilePicture, LinearLayout profileBackground, TextView profileName, String photoUrlIn) {
        Picasso.with(MyAccountActivity.this)
                .load(photoUrlIn)
                .resize(200, 200)
                .centerCrop()
                .into(profilePicture);
    }

    public class RoundedTransformation implements com.squareup.picasso.Transformation {
        private final int radius;
        private final int margin;  // dp

        // radius is corner radii in dp
        // margin is the board in dp
        public RoundedTransformation(final int radius, final int margin) {
            this.radius = radius;
            this.margin = margin;
        }

        @Override
        public Bitmap transform(final Bitmap source) {


            final Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setShader(new BitmapShader(source, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));

            Bitmap output = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(output);
            canvas.drawRoundRect(new RectF(margin, margin, source.getWidth() - margin, source.getHeight() - margin), radius, radius, paint);

            if (source != output) {
                source.recycle();
            }

            Paint paint1 = new Paint();
            paint1.setColor(Color.BLACK);
            paint1.setStyle(Paint.Style.STROKE);
            paint1.setAntiAlias(true);
            paint1.setStrokeWidth(2);
            canvas.drawCircle((source.getWidth() - margin)/2, (source.getHeight() - margin)/2, radius-2, paint1);


            return output;
        }

        @Override
        public String key() {
            return "rounded";
        }
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
        startActivity(new Intent(MyAccountActivity.this, MainActivity.class));
    }

}
