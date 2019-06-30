package com.area52.techno.festivals_new.dj;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.area52.techno.R;
import com.area52.techno.activities.MainActivity;
import com.area52.techno.models.DJ;
import com.area52.techno.models.Festival;
import com.area52.techno.models.User;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainActivityFestival extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference myRef ;
    List<Festival> listDJs;
    List<User> list;
    RecyclerView recycle;
    Button view;
    Query query;
    FloatingActionButton actiona, actionb, actionc, actiond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dj2);

    //    Toast.makeText(MainActivityDJ.this, "Here", Toast.LENGTH_SHORT).show();

        recycle = (RecyclerView) findViewById(R.id.recycleDJ);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Festival");

        actiona = (FloatingActionButton) findViewById(R.id.action_a);
        actionb = (FloatingActionButton) findViewById(R.id.action_b);
        actionc = (FloatingActionButton) findViewById(R.id.action_c);
        actiond = (FloatingActionButton) findViewById(R.id.action_d);

        actiona.setImageBitmap(textAsBitmap("Trance", 20, Color.WHITE));
        actionb.setImageBitmap(textAsBitmap("Techno", 20, Color.WHITE));
        actionc.setImageBitmap(textAsBitmap("House", 20, Color.WHITE));
        actiond.setImageBitmap(textAsBitmap("All", 20, Color.WHITE));

//method to convert your text to image

        query("All");

        fab();

    }

    public static Bitmap textAsBitmap(String text, float textSize, int textColor) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setTextSize(textSize);
        paint.setColor(textColor);
        paint.setTextAlign(Paint.Align.LEFT);
        float baseline = -paint.ascent(); // ascent() is negative
        int width = (int) (paint.measureText(text) + 0.0f); // round
        int height = (int) (baseline + paint.descent() + 0.0f);
        Bitmap image = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(image);
        canvas.drawText(text, 0, baseline, paint);
        return image;
    }

    private void query(String filter) {
        myRef.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                listDJs = new ArrayList<Festival>();
                for(DataSnapshot dataSnapshot1 :dataSnapshot.getChildren()){

                    Festival value = dataSnapshot1.getValue(Festival.class);
                    Festival dj = new Festival();

//                    Toast.makeText(MainActivityDJ.this, value.toString(), Toast.LENGTH_SHORT).show();

                    dj.setuID(value.getuID());
                    dj.setName(value.getName());
                    dj.setPhoto(value.getPhotoUrlDJ());
                    dj.setCounty(value.getCounty());
                    dj.setCountry(value.getCountry());
                    dj.setSoundcloudLink(value.getSoundcloudLink());
                    dj.setYoutubeLink(value.getYoutubeLink());
                    dj.setYoutubePlaylistLink(value.getYoutubePlaylistLink());
                    dj.setMixcloudLink(value.getMixcloudLink());
                    dj.setFacebookLink(value.getFacebookLink());
                    dj.setInstagramLink(value.getInstagramLink());
                    dj.setSpotifyLink(value.getSpotifyLink());
                    dj.setGenre(value.getGenre());
                    dj.setStart_date_timestamp(value.getStart_date_timestamp());


                    listDJs.sort(Comparator.comparing(Festival::getStart_date_timestamp));

                    if (filter.equalsIgnoreCase("All")){
                        listDJs.add(dj);
                        }
                    else if (filter.equalsIgnoreCase("Techno")){
                        if(value.getGenre().equalsIgnoreCase("Techno")){
                            listDJs.add(dj);
                        }
                    }else if (filter.equalsIgnoreCase("Trance")){
                        if(value.getGenre().equalsIgnoreCase("Trance")){
                            listDJs.add(dj);
                        }
                    }else if (filter.equalsIgnoreCase("House")){
                        if(value.getGenre().equalsIgnoreCase("House")){
                            listDJs.add(dj);
                        }
                    }



                }

                FestivalRecyclerAdapter djRecyclerAdapter = new FestivalRecyclerAdapter(listDJs, MainActivityFestival.this);
             //    RecyclerView.LayoutManager recyce = new GridLayoutManager(MainActivityClub.this,2);
                GridLayoutManager manager = new GridLayoutManager(MainActivityFestival.this, 12, GridLayoutManager.VERTICAL, false);
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



    private void fab() {

        final FloatingActionButton actionA = (FloatingActionButton) findViewById(R.id.action_a);
        actionA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filter("Trance");
            }
        });


        final FloatingActionButton actionB = (FloatingActionButton) findViewById(R.id.action_b);
        actionB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filter("Techno");
            }
        });

        final FloatingActionButton actionC = (FloatingActionButton) findViewById(R.id.action_c);
        actionC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filter("House");
            }
        });


        final FloatingActionButton actionD = (FloatingActionButton) findViewById(R.id.action_d);
        actionD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filter("All");
            }
        });

//        FloatingActionButton actionC = new FloatingActionButton(getBaseContext());
////        actionC.setTitle("Hide/Show Action above");
////        actionC.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
//////                actionB.setVisibility(actionB.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
////                actionB.setTitle("Action A clicked");
////            }
////        });
//
////        final FloatingActionsMenu menuMultipleActions = (FloatingActionsMenu) findViewById(R.id.multiple_actions);
////        menuMultipleActions.addButton(actionC);

    }

    private void filter(String genreIn) {

        query(genreIn);

    }

    public void searchDJs(View view) {

        Toast.makeText(this, "YES", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onBackPressed() {

            startActivity(new Intent(MainActivityFestival.this, MainActivity.class));
            //super.onBackPressed();

    }


}
