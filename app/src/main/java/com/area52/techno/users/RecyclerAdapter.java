package com.area52.techno.users;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.area52.techno.R;
import com.area52.techno.models.User;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by csa on 3/7/2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyHoder>{

    List<User> list;
    Context context;

    public RecyclerAdapter(List<User> list, Context context) {
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
    //    holder.email.setText(mylist.getEmail());
        Glide.with(context)
                .load(mylist.getPhotoUrl())
                .into(holder.thumbnail);

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
