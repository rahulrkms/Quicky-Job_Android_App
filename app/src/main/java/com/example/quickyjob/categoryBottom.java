package com.example.quickyjob;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.quickyjob.adapters.bottomCategoryAdapter;
import com.example.quickyjob.modelclass.fetchcategorydata_response_model;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class categoryBottom extends AppCompatActivity {
    RecyclerView category_bottom_recview;
    //int noOfItem = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_bottom);
        //getSupportActionBar().hide();

        imageSlider_cat();

        category_bottom_recview = findViewById(R.id.category_bottom_recview);
        category_bottom_recview.setLayoutManager(new LinearLayoutManager(this));

        fetchingdata();
    }

    private void imageSlider_cat() {
        ImageSlider imageSlider = findViewById(R.id.slider_cat_botton);
        List<SlideModel> slideModel = new ArrayList<>();

        slideModel.add(new SlideModel(R.drawable.slide_three, ScaleTypes.FIT));
        slideModel.add(new SlideModel("https://picsum.photos/id/237/200/300\n", ScaleTypes.FIT));
        slideModel.add(new SlideModel("https://picsum.photos/seed/picsum/200/300\n", ScaleTypes.FIT));

        imageSlider.setImageList(slideModel,ScaleTypes.FIT);
    }

    public void fetchingdata(){
        Call<List<fetchcategorydata_response_model>> callCategory = apicontroller.getInstance().getapi().getFetchCategoryData();
        callCategory.enqueue(new Callback<List<fetchcategorydata_response_model>>() {
            @Override
            public void onResponse(Call<List<fetchcategorydata_response_model>> call, Response<List<fetchcategorydata_response_model>> response) {
                List<fetchcategorydata_response_model> data1 = response.body();
                bottomCategoryAdapter bottomCategoryAdapter = new bottomCategoryAdapter(data1,getApplicationContext());
                category_bottom_recview.setAdapter(bottomCategoryAdapter);
            }

            @Override
            public void onFailure(Call<List<fetchcategorydata_response_model>> call, Throwable t) {
                Toast.makeText(categoryBottom.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

}