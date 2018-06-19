package com.github.anurag145.mycheckin;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder>
{
    private ArrayList<String> In= new ArrayList<>();
    private ArrayList<String> Out= new ArrayList<>();
    private ArrayList<String> date= new ArrayList<>();
    private int length;
    CustomAdapter(ArrayList<String> In,ArrayList<String> Out,ArrayList<String> date,int length)
    {
        this.In=In;
        this.Out=Out;
        this.date=date;
        this.length=length;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_card,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
                 holder.t1.setText(date.get(position));
                 holder.t2.setText("IN: "+In.get(position));
                 holder.t3.setText("OUT: "+Out.get(position));
    }

    @Override
    public int getItemCount() {
        return length;
    }

    static class ViewHolder extends RecyclerView.ViewHolder
    {     public TextView t1,t2,t3;
             ViewHolder(View view)
             { super(view);
                   t1=view.findViewById(R.id.date);
                   t2=view.findViewById(R.id.checkin);
                   t3=view.findViewById(R.id.checkout);
             }
    }
}
