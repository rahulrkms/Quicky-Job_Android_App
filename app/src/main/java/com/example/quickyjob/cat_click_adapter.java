package com.example.quickyjob;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class cat_click_adapter extends RecyclerView.Adapter<cat_click_adapter.holderClick> {

    public cat_click_adapter(List<category_clickListener_response_model> cat_data) {
        this.cat_data = cat_data;
    }

    List<category_clickListener_response_model> cat_data;


    @NonNull
    @Override

    public holderClick onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerowcatclick,parent,false);
        return new holderClick(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holderClick holder, @SuppressLint("RecyclerView") int position) {

        holder.cat_text.setText(cat_data.get(position).getCpname());


        // setOnClickListner On url Button
        holder.cat_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String bb =  cat_data.get(position).getUrl();
                Uri uri = Uri.parse(bb);
                view.getContext().startActivity(new Intent(Intent.ACTION_VIEW,uri));
            }
        });

    }

    @Override
    public int getItemCount() {
        return cat_data.size();
    }

    public class holderClick extends RecyclerView.ViewHolder {
        TextView cat_text;
        Button cat_btn;
        public holderClick(@NonNull View itemView) {
            super(itemView);

            cat_text = itemView.findViewById(R.id.cat_text_btn);
            cat_btn = itemView.findViewById(R.id.cat_url_btn);


        }
    }

}
