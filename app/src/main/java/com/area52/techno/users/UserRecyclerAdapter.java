package com.area52.techno.users;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.area52.techno.MyAccountActivity;
import com.area52.techno.R;
import com.area52.techno.models.User;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by csa on 3/7/2017.
 */

public class UserRecyclerAdapter extends RecyclerView.Adapter<UserRecyclerAdapter.MyHoder>{

    List<User> list;
    Context context;

    public UserRecyclerAdapter(List<User> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyHoder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.card,parent,false);
        MyHoder myHoder = new MyHoder(view);

        return myHoder;
    }

    @Override
    public void onBindViewHolder(MyHoder holder, int position) {
        User mylist = list.get(position);
        holder.name.setText(mylist.getName());
        holder.name.setSelected(true);

//        holder.thumbnail.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                // Perform action on click
//                Intent i=new Intent(context,MyAccountActivity.class);
//                i.putExtra("userID", mylist.getuID());
//                context.startActivity(i);
//            }
//        });

//        Glide.with(context)
//                .load(mylist.getPhotoUrlDJ())
//                .apply(new RequestOptions()
//                        .fitCenter()
//                        .format(DecodeFormat.PREFER_ARGB_8888)
//                        .override(Target.SIZE_ORIGINAL)
//                .placeholder(R.drawable.sesh_logo))
//                .into(holder.thumbnail);

//        Picasso.with(context)
//                .load(mylist.getPhotoUrl())
//                .placeholder(R.drawable.sesh_logo)
//                .into(holder.thumbnail);

        Picasso.with(context)
                .load(mylist.getPhotoUrl())
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
                                .load(mylist.getPhotoUrl())
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

    class MyHoder extends RecyclerView.ViewHolder{

        TextView name;
        ImageView thumbnail;

        public MyHoder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.vname);
        //    email= (TextView) itemView.findViewById(R.id.vemail);
            thumbnail= (ImageView) itemView.findViewById(R.id.thumbnail);


        }
    }
}
