package com.area52.techno.users;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.area52.techno.R;
import com.area52.techno.models.User;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        recycle = (RecyclerView) findViewById(R.id.recycle);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("usersEmail");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                list = new ArrayList<User>();
                for(DataSnapshot dataSnapshot1 :dataSnapshot.getChildren()){

                    User value = dataSnapshot1.getValue(User.class);
                    User fire = new User();
                    String name = value.getName();
                    String email = value.getName();
                    String photo = value.getPhotoUrl();
                    fire.setName(name);
                    fire.setEmail(email);
                    fire.setPhotoUrl(photo);
                    list.add(fire);

                //    Toast.makeText(MainActivityUser.this, list.toString(), Toast.LENGTH_SHORT).show();

                }

                RecyclerAdapter recyclerAdapter = new RecyclerAdapter(list,MainActivityUser.this);
                RecyclerView.LayoutManager recyce = new GridLayoutManager(MainActivityUser.this,2);
                /// RecyclerView.LayoutManager recyce = new LinearLayoutManager(MainActivity.this);
                // recycle.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
                recycle.setLayoutManager(recyce);
                recycle.setItemAnimator( new DefaultItemAnimator());
                recycle.setAdapter(recyclerAdapter);
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
