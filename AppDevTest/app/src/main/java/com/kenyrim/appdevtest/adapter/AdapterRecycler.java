package com.kenyrim.appdevtest.adapter;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kenyrim.appdevtest.model.Model;
import com.kenyrim.appdevtest.fragments.MyDialog;
import com.kenyrim.appdevtest.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterRecycler extends RecyclerView.Adapter<AdapterRecycler.ViewHolder> {
    private Context context;
    private ArrayList<Model> data;
    private FragmentActivity activity;
    private FragmentManager fts;

    public AdapterRecycler(FragmentActivity activity,Context context,ArrayList<Model> data) {
        this.activity = activity;
        this.context = context;
        this.data = data;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder,final int position) {
        Model model = data.get(position);
        holder.title.setText(model.getTitle());


        Picasso.with(context)
                .load(model.getImageUrl())
                .into(holder.image);

    }

    class ViewHolder extends RecyclerView.ViewHolder {


        TextView title;
        ImageView image;

        ViewHolder(View v) {
            super(v);
            title = v.findViewById(R.id.tv_item);
            image = v.findViewById(R.id.iv_item);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MyDialog dialog = new MyDialog();
                    Bundle b = new Bundle();
                    b.putString("FULL_DESC", data.get(getAdapterPosition()).getTitle());
                    b.putString("FULL_IMAGE", data.get(getAdapterPosition()).getImageUrl());
                    dialog.setArguments(b);

                    fts = activity.getSupportFragmentManager();
                    dialog.show(fts,null);


                }
            });

        }
    }
}