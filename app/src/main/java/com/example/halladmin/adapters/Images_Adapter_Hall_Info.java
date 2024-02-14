package com.example.halladmin.adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.halladmin.R;

import java.util.ArrayList;

public class Images_Adapter_Hall_Info extends RecyclerView.Adapter<Images_Adapter_Hall_Info.ViewHolder> {

    private final ArrayList<Uri> uriArrayList;
    private final Context context;
    private final itemclickListener itemclickListener;

    public Images_Adapter_Hall_Info(ArrayList<Uri> uriArrayList, Context context,itemclickListener itemclickListener) {
        this.uriArrayList = uriArrayList;
        this.context = context;
        this.itemclickListener = itemclickListener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imageView,delete;
        itemclickListener itemclickListener;
        public ViewHolder(@NonNull View itemView, itemclickListener itemclickListener) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            delete = itemView.findViewById(R.id.img_delete);
            this.itemclickListener = itemclickListener;

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            if(itemclickListener != null){
                itemclickListener.itemClick(getAdapterPosition());
            }
        }
    }

    @NonNull
    @Override
    public Images_Adapter_Hall_Info.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.custom_single_image,parent,false);
        return new ViewHolder(view,itemclickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull Images_Adapter_Hall_Info.ViewHolder holder, int position) {
        //holder.imageView.setImageURI(uriArrayList.get(position));
        Glide.with(context)
                .load(uriArrayList.get(position))
                .into(holder.imageView);

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uriArrayList.remove(uriArrayList.get(position));
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,getItemCount());
            }
        });
    }

    @Override
    public int getItemCount() {
        return uriArrayList.size();
    }

    public interface itemclickListener{
        void itemClick(int position);
    }

}
