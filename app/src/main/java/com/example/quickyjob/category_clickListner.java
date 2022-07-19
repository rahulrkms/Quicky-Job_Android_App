package com.example.quickyjob;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.quickyjob.adapters.cat_click_adapter;
import com.example.quickyjob.modelclass.category_clickListener_response_model;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class category_clickListner extends AppCompatActivity {


    RecyclerView cat_click_recview;
    TextView CatName;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_click_listner);

        CatName=findViewById(R.id.fetchedCatName);


       // imageSliderCatClick();

       // getSupportActionBar().hide();
        cat_click_recview = findViewById(R.id.cat_click_recview);
        cat_click_recview.setLayoutManager(new LinearLayoutManager(this));

        // Getting Category Name In String SS From bottomCategoryAdapter class
        String ss = getIntent().getStringExtra("data");
        CatName.setText("Category: "+ss);


        Call<List<category_clickListener_response_model>> call  = apicontroller.getInstance().getapi().getcat(ss);
        call.enqueue(new Callback<List<category_clickListener_response_model>>() {
            @Override
            public void onResponse(Call<List<category_clickListener_response_model>> call, Response<List<category_clickListener_response_model>> response) {
               List<category_clickListener_response_model> ccrm_data = response.body();
               cat_click_adapter cca = new cat_click_adapter(ccrm_data);
               cat_click_recview.setAdapter(cca);

            }

            @Override
            public void onFailure(Call<List<category_clickListener_response_model>> call, Throwable t) {
                Toast.makeText(category_clickListner.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

//    private void imageSliderCatClick() {
//        ImageSlider imageSlider = findViewById(R.id.slider_cat_botton);
//        List<SlideModel> slideModels = new ArrayList<>();
//        slideModels.add(new SlideModel(R.drawable.slide_three, ScaleTypes.FIT));
//        slideModels.add(new SlideModel("https://picsum.photos/id/237/200/300\n", ScaleTypes.FIT));
//        slideModels.add(new SlideModel("https://picsum.photos/id/237/200/300\n", ScaleTypes.FIT));
//        slideModels.add(new SlideModel("https://picsum.photos/seed/picsum/200/300\n", ScaleTypes.FIT));
//
//        imageSlider.setImageList(slideModels,ScaleTypes.FIT);
//
//    }
}