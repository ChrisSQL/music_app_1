package com.area52.techno.clubs.users;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.area52.techno.R;
import com.area52.techno.activities.MainActivity;
import com.area52.techno.models.Club;
import com.area52.techno.models.Festival;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MainActivityClub extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference myRef ;
    List<Club> list;
    RecyclerView recycle;
    Button view;
    private FirebaseAuth mAuth;
    FirebaseUser user;
    private final int REQUEST_LOCATION_PERMISSION = 1;
    GPSTracker gps = new GPSTracker(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        recycle = (RecyclerView) findViewById(R.id.recycle);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Clubs");
        list = new ArrayList<Club>();
        // If logged in log Analytics
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, 1);

//        if (ActivityCompat.checkSelfPermission(MainActivityClub.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MainActivityClub.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(MainActivityClub.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
//
//            getClubsGrid();
//
//            return;
//        }else{
//            // Write you code here if permission already given.
//            // Toast.makeText(this, "Permission Given", Toast.LENGTH_SHORT).show();
//
//            getClubsGrid();
//
//        }




//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//
//            }
//        });

    }

    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                //    Toast.makeText(this, "Granted", Toast.LENGTH_SHORT).show();


                    // Get Distance and save to variable

                    if(gps.canGetLocation()){
                        gps.getLatitude(); // returns latitude
                        gps.getLongitude();
                    }

                    getClubsGridDistance();



                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.

                    Toast.makeText(this, "Cannot show distances without location", Toast.LENGTH_SHORT).show();

                    // getClubsGrid();

                }
                return;
            }
            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    private void getClubsGridDistance() {




        myRef.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                for(DataSnapshot dataSnapshot1 :dataSnapshot.getChildren()){

                    Club value = dataSnapshot1.getValue(Club.class);
                    Club fire = new Club();

                    String name = value.getName();
                    String photo = value.getPhotoUrl();
                    String fbID = value.getFacebookLink();
                    String uID = value.getuID();
                    double latitude = value.getLatitiude();
                    double longitude = value.getLongitude();

                    fire.setName(name);
                    fire.setPhotoUrl(photo);
                    fire.setFacebookLink(fbID);
                    fire.setuID(uID);
                    fire.setLatitiude(latitude);
                    fire.setLongitude(longitude);

                    Location selected_location=new Location("locationA");
                    selected_location.setLatitude(gps.getLatitude());
                    selected_location.setLongitude(gps.getLongitude());
                    Location near_locations=new Location("locationB");
                    near_locations.setLatitude(latitude);
                    near_locations.setLongitude(longitude);
                    double distance = selected_location.distanceTo(near_locations);

                    fire.setMiles(distance);

                    list.add(fire);




                }

                // list.sort(Comparator.comparing(Club::getMiles));

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Hello", "Failed to read value.", error.toException());
            }
        });

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                createGrid();
            }
        }, 2500);   //.5 seconds

    }


    private double calculateDistance(double lat1, double lon1, double lat2, double lon2, String unit)
    {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        if (unit == "K") {
            dist = dist * 1.609344;
        } else if (unit == "M") {
            dist = dist * 0.8684;
        }
        return (dist);
    }

    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    /*::  This function converts decimal degrees to radians             :*/
    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    private double deg2rad(double deg)
    {
        return (deg * Math.PI / 180.0);
    }

    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    /*::  This function converts radians to decimal degrees             :*/
    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    private double rad2deg(double rad)
    {
        return (rad * 180.0 / Math.PI);
    }

    private void getClubsGrid() {



        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                for(DataSnapshot dataSnapshot1 :dataSnapshot.getChildren()){

                    Club value = dataSnapshot1.getValue(Club.class);
                    Club fire = new Club();

                    String name = value.getName();
                    String photo = value.getPhotoUrl();
                    String fbID = value.getFacebookLink();
                    String uID = value.getuID();
                    double latitude = value.getLatitiude();
                    double longitude = value.getLongitude();

                    fire.setName(name);
                    fire.setPhotoUrl(photo);
                    fire.setFacebookLink(fbID);
                    fire.setuID(uID);
                    fire.setLatitiude(latitude);
                    fire.setLongitude(longitude);

                    list.add(fire);

                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Hello", "Failed to read value.", error.toException());
            }
        });

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                createGrid();
            }
        }, 2500);   //.5 seconds
    }

    private void getUserLocation() {




    }




    private void createGrid() {
        ClubRecyclerAdapter userRecyclerAdapter = new ClubRecyclerAdapter(list, MainActivityClub.this);
        //    RecyclerView.LayoutManager recyce = new GridLayoutManager(MainActivityClub.this,2);
        GridLayoutManager manager = new GridLayoutManager(MainActivityClub.this, 12, GridLayoutManager.VERTICAL, false);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                // 7 is the sum of items in one repeated section
                switch (position % 7) {
                    // first three items span 3 columns each
                    case 0:
                    case 1:
                    case 2:
                        return 4;
                    // next four items span 2 columns each

                    case 3:
                    case 4:
                    case 5:
                    case 6:
                        return 4;
                }
                throw new IllegalStateException("internal error");
            }
        });
        recycle.setLayoutManager(manager);
        /// RecyclerView.LayoutManager recyce = new LinearLayoutManager(MainActivity.this);
        // recycle.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        // recycle.setLayoutManager(recyce);
        recycle.setItemAnimator( new DefaultItemAnimator());
        recycle.setAdapter(userRecyclerAdapter);
    }
}
