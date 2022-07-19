package com.example.quickyjob.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.quickyjob.R;
import com.example.quickyjob.category_clickListner;
import com.example.quickyjob.modelclass.fetchcategorydata_response_model;

import java.util.List;

public class bottomCategoryAdapter extends RecyclerView.Adapter <bottomCategoryAdapter.BottomCategoryViewHolder>{

    Context context;
    public bottomCategoryAdapter(List<fetchcategorydata_response_model> dataCategory, Context context)
    {
        this.dataCategory = dataCategory;
        this.context = context;
    }
    List<fetchcategorydata_response_model> dataCategory;

    @NonNull
    @Override
    public BottomCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerowbottomcategory,parent,false);
        return new BottomCategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BottomCategoryViewHolder holder, @SuppressLint("RecyclerView") int position) {
            holder.single_row_bottom_tv.setText(dataCategory.get(position).getCatname());
        Glide.with(context).load("http://192.168.29.89/quickyapi/admin/images/"+dataCategory.get(position).getCatimage()).into(holder.catimage_single);

            final fetchcategorydata_response_model temp=dataCategory.get(position);

            // SetOnClickListner
        holder.single_row_bottom_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(context, category_clickListner.class);
                intent.putExtra("data",temp.getCatname());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return dataCategory.size();
    }

    class BottomCategoryViewHolder extends RecyclerView.ViewHolder{
        TextView single_row_bottom_tv;
        ImageView catimage_single;
        public BottomCategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            single_row_bottom_tv = itemView.findViewById(R.id.single_row_bottom_tv);
            catimage_single = itemView.findViewById(R.id.catimage_single);

        }
    }
}
