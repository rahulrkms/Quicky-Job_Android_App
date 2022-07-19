package com.example.quickyjob.adapters;

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

import com.example.quickyjob.R;
import com.example.quickyjob.modelclass.fetch_dashboard_response_model;

import java.util.List;

public class dash_adapter extends RecyclerView.Adapter<dash_adapter.holder> {

    public dash_adapter(List<fetch_dashboard_response_model> data) {
        this.data = data;
    }

    List<fetch_dashboard_response_model> data;


    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerowdesign,parent,false);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, @SuppressLint("RecyclerView") int position) {

        holder.companyname.setText(data.get(position).getCpname());


       // setOnClickListner On url Button
        holder.url_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              String bb =  data.get(position).getUrl();
                Uri uri = Uri.parse(bb);
                view.getContext().startActivity(new Intent(Intent.ACTION_VIEW,uri));
            }
        });

        // end url listner

//        holder.elgblty.setText(data.get(position).getEligibility());
//        holder.sdesc.setText(data.get(position).getSdesc());


    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    class holder extends RecyclerView.ViewHolder{

        TextView companyname;
        Button url_btn;
        String jj;
//        TextView elgblty;
//        TextView sdesc;
        public holder(@NonNull View itemView) {
            super(itemView);

            companyname = itemView.findViewById(R.id.companyname);
            url_btn = itemView.findViewById(R.id.url_btn);
//            elgblty = itemView.findViewById(R.id.elgblty);
//            sdesc = itemView.findViewById(R.id.sdesc);

        }
    }
}
