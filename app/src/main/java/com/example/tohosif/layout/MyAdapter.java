package com.example.tohosif.layout;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tohosif.recyclerview.R;

import java.util.Collections;
import java.util.List;

/**
 * Created by Tohosif on 14-07-2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    private LayoutInflater inflater;
    List<Information> data= Collections.emptyList();

    public MyAdapter(Context context,List<Information> data){
        inflater=LayoutInflater.from(context);       //To get LayoutInflater in a Given Context
        this.data=data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.custom_row,parent,false); //LayoutParam???
        MyViewHolder holder=new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Information currentInformation=data.get(position);
        holder.iv_img.setImageResource(currentInformation.iconId);
        holder.tx_txt.setText(""+currentInformation.title); //position
        holder.tx_txt.setTextColor(Color.RED);
    }

    @Override
    public int getItemCount() {
            return data.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tx_txt;
        ImageView iv_img;
        public MyViewHolder(View itemView) {
            super(itemView);
            tx_txt=(TextView)itemView.findViewById(R.id.txt);
            iv_img=(ImageView)itemView.findViewById(R.id.img);

        }
    }
}
