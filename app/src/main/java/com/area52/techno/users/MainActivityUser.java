package com.area52.techno.users;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;

import com.area52.techno.R;
import com.area52.techno.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivityUser extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference myRef ;
    List<User> list;
    RecyclerView recycle;
    Button view;
    private FirebaseAuth mAuth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        recycle = (RecyclerView) findViewById(R.id.recycle);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("usersUsername");
        list = new ArrayList<User>();
        // If logged in log Analytics
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                for(DataSnapshot dataSnapshot1 :dataSnapshot.getChildren()){

                    User value = dataSnapshot1.getValue(User.class);
                    User fire = new User();
                    String name = value.getName();
                    String email = value.getName();
                    String photo = value.getPhotoUrl();
                    String fbID = value.getFbID();
                    String uID = value.getuID();
                    fire.setName(name);
                    fire.setEmail(email);
                    fire.setPhotoUrl(photo);
                    fire.setFbID(fbID);
                    fire.setuID(uID);
                    fire.setRefDJ(value.getRefDJ());

                    if(user.getUid().equalsIgnoreCase(value.getuID())){
                        list.add(fire);
                    }






                //    Toast.makeText(MainActivityUser.this, list.toString(), Toast.LENGTH_SHORT).show();

                }


            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Hello", "Failed to read value.", error.toException());
            }
        });

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                for(DataSnapshot dataSnapshot1 :dataSnapshot.getChildren()){

                    User value = dataSnapshot1.getValue(User.class);
                    User fire = new User();
                    String name = value.getName();
                    String email = value.getName();
                    String photo = value.getPhotoUrl();
                    String fbID = value.getFbID();
                    String uID = value.getuID();
                    fire.setName(name);
                    fire.setEmail(email);
                    fire.setPhotoUrl(photo);
                    fire.setFbID(fbID);
                    fire.setuID(uID);
                    fire.setRefDJ(value.getRefDJ());

                    if(!user.getUid().equalsIgnoreCase(value.getuID())){
                        list.add(fire);
                    }





                    //    Toast.makeText(MainActivityUser.this, list.toString(), Toast.LENGTH_SHORT).show();

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
        }, 500);   //.5 seconds

//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//
//            }
//        });

    }

    private void createGrid() {
        UserRecyclerAdapter userRecyclerAdapter = new UserRecyclerAdapter(list, MainActivityUser.this);
        //    RecyclerView.LayoutManager recyce = new GridLayoutManager(MainActivityUser.this,2);
        GridLayoutManager manager = new GridLayoutManager(MainActivityUser.this, 12, GridLayoutManager.VERTICAL, false);
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
