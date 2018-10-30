package com.area52.techno.dj;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;

import com.area52.techno.R;
import com.area52.techno.models.DJ;
import com.area52.techno.models.User;
import com.area52.techno.users.UserRecyclerAdapter;
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
    List<DJ> list;
    RecyclerView recycle;
    Button view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dj2);
        recycle = (RecyclerView) findViewById(R.id.recycle);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("DJ");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                list = new ArrayList<DJ>();
                for(DataSnapshot dataSnapshot1 :dataSnapshot.getChildren()){

                    User value = dataSnapshot1.getValue(User.class);
                    DJ fire = new DJ();

//                    String name = value.getName();
//                    String email = value.getName();
//                    String photo = value.getPhotoUrl();
//                    String fbID = value.getFbID();
//                    String guID = value.getuID();
//
//                    fire.setName(name);
//                    fire.setEmail(email);
//                    fire.setPhotoUrl(photo);
//                    fire.setFbID(fbID);
//                    fire.setuID(guID);

                    list.add(fire);

                //    Toast.makeText(MainActivityUser.this, list.toString(), Toast.LENGTH_SHORT).show();

                }

                DJRecyclerAdapter userRecyclerAdapter = new DJRecyclerAdapter(list,MainActivityDJ.this);
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
                recycle.setAdapter(userRecyclerAdapter);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Hello", "Failed to read value.", error.toException());
            }
        });



//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//
//            }
//        });

    }
}
