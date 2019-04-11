package com.area52.techno.dj;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.area52.techno.R;
import com.area52.techno.models.DJ;
import com.area52.techno.models.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivityDJ extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference myRef ;
    List<DJ> listDJs;
    List<User> list;
    RecyclerView recycle;
    Button view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dj2);

    //    Toast.makeText(MainActivityDJ.this, "Here", Toast.LENGTH_SHORT).show();

        recycle = (RecyclerView) findViewById(R.id.recycleDJ);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("DJ");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                listDJs = new ArrayList<DJ>();
                for(DataSnapshot dataSnapshot1 :dataSnapshot.getChildren()){

                    DJ value = dataSnapshot1.getValue(DJ.class);
                    DJ dj = new DJ();

//                    Toast.makeText(MainActivityDJ.this, value.toString(), Toast.LENGTH_SHORT).show();

                    dj.setuID(value.getuID());
                    dj.setName(value.getName());
                    dj.setEmail(value.getEmail());
                    dj.setPhoto(value.getPhotoUrlDJ());
                    dj.setFacebookUserID(value.getFacebookUserID());
                    dj.setDjLogoImage(value.getDjLogoImage());
                    dj.setCounty(value.getCounty());
                    dj.setCountry(value.getCountry());
                    dj.setPhoneNumber(value.getPhoneNumber());
                    dj.setBio(value.getBio());
                    dj.setSoundcloudLink(value.getSoundcloudLink());
                    dj.setYoutubeLink(value.getYoutubeLink());
                    dj.setMixcloudLink(value.getMixcloudLink());
                    dj.setFacebookLink(value.getFacebookLink());
                    dj.setInstagramLink(value.getInstagramLink());
                    dj.setSpotifyLink(value.getSpotifyLink());
                    dj.setBookingEmail(value.getBookingEmail());
                    dj.setGenre(value.getGenre());

                    listDJs.add(dj);

//                    Toast.makeText(MainActivityDJ.this, listDJs.toString(), Toast.LENGTH_SHORT).show();

                }

                DJRecyclerAdapter djRecyclerAdapter = new DJRecyclerAdapter(listDJs,MainActivityDJ.this);
             //    RecyclerView.LayoutManager recyce = new GridLayoutManager(MainActivityUser.this,2);
                GridLayoutManager manager = new GridLayoutManager(MainActivityDJ.this, 12, GridLayoutManager.VERTICAL, false);
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
                recycle.setAdapter(djRecyclerAdapter);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Hello", "Failed to read value.", error.toException());
            }
        });


    }
}
