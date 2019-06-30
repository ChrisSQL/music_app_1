package com.area52.techno.festivals_new.dj;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.area52.techno.DataProccessor;
import com.area52.techno.R;
import com.area52.techno.models.DJ;
import com.area52.techno.models.Festival;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class FestivalRecyclerAdapter extends RecyclerView.Adapter<FestivalRecyclerAdapter.MyHoder>{

    List<Festival> list;
    Context context;
    DataProccessor dataProccessor;

    public FestivalRecyclerAdapter(List<Festival> list, Context context) {
        this.list = list;
        this.context = context;

    }

    @Override
    public MyHoder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.card_dj,parent,false);
        MyHoder myHoder = new MyHoder(view);
        dataProccessor = new DataProccessor(context);

        return myHoder;
    }

    @Override
    public void onBindViewHolder(MyHoder holder, int position) {
        Festival mylist = list.get(position);
        holder.name.setText(mylist.getName()  + " (" + countdownDays(mylist.getStart_date_timestamp()) + " Days to go)");
        holder.name.setSelected(true);

                holder.thumbnail.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

//                dataProccessor.setStr("djReferral" , mylist.getName());
//
//                SharedPreferences sharedPreferences = context.getSharedPreferences("prefs", Context.MODE_PRIVATE);
//                SharedPreferences.Editor edt = sharedPreferences.edit();
//                edt.putString("djReferral", mylist.getName());
//                edt.apply();

                // Perform action on click
                Intent i=new Intent(context, MyFestivalActivityBranch.class);
                i.putExtra("djName", mylist.getName());
                i.putExtra("getPhotoUrlDJ", mylist.getPhotoUrlDJ());
                i.putExtra("FacebookLink", mylist.getFacebookLink());
                i.putExtra("SoundcloudLink", mylist.getSoundcloudLink());
                i.putExtra("YoutubeLink", mylist.getYoutubeLink());
                i.putExtra("YoutubePlaylistLink", mylist.getYoutubePlaylistLink());
                i.putExtra("country", mylist.getCountry());
                i.putExtra("genre", mylist.getGenre());
                i.putExtra("start_date_timestamp", mylist.getStart_date_timestamp());
                i.putExtra("ticketsLink", mylist.getTicketsLink());
                context.startActivity(i);
            }

        });

        Picasso.with(context)
                .load(mylist.getPhotoUrlDJ())
                .resize(200,200)
                .centerCrop()
                .placeholder(R.drawable.sesh_logo)
                .networkPolicy(NetworkPolicy.OFFLINE)
                .into(holder.thumbnail, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {
                        //Try again online if cache failed
                        Picasso.with(context)
                                .load(mylist.getPhotoUrlDJ())
                                .resize(200,200)
                                .centerCrop()
                                .placeholder(R.drawable.sesh_logo)
                                .error(R.drawable.sesh_logo)
                                .into(holder.thumbnail, new Callback() {
                                    @Override
                                    public void onSuccess() {

                                    }

                                    @Override
                                    public void onError() {
                                        Log.v("Picasso","Could not fetch image");
                                    }
                                });
                    }
                });

    //    Toast.makeText(context, mylist.getPhotoUrlDJ(), Toast.LENGTH_SHORT).show();


    }

    private String countdownDays(String start_date_timestamp) {

        String days;
        long now = System.currentTimeMillis();
        long dateIn = Long.parseLong(start_date_timestamp);

        long diff = dateIn - now;
        days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) + "";

        return days;
    }

    @Override
    public int getItemCount() {

        int arr = 0;

        try{
            if(list.size()==0){
                arr = 0;
            }
            else{
                arr=list.size();
            }

        }catch (Exception e){

        }

        return arr;
    }

    public void updateDataSet(List<Festival> arrayList) {
        this.list = arrayList;
    }

    class MyHoder extends RecyclerView.ViewHolder{

        TextView name;
        ImageView thumbnail;
        String soundCloud;

        public MyHoder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.vname);
        //    email= (TextView) itemView.findViewById(R.id.vemail);
            thumbnail= (ImageView) itemView.findViewById(R.id.thumbnail_dj);



        }
    }
}
