package com.example.quickyjob;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.quickyjob.activity.Logout;
import com.example.quickyjob.activity.Settings;
import com.example.quickyjob.activity.myprofile;
import com.example.quickyjob.adapters.dash_adapter;
import com.example.quickyjob.modelclass.fetch_dashboard_response_model;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class dashboard extends AppCompatActivity {
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    BottomNavigationView bnv;
    ActionBarDrawerToggle actionBarDrawerToggle;
    RecyclerView recview;
    SharedPreferences spp;
    TextView emailiyaaa;
    String em;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        bnv=findViewById(R.id.bottomNavigation);
        emailiyaaa = findViewById(R.id.emailiyan);


        // Recycler view Coding start from here---------------------------------------------------

        recview = findViewById(R.id.recview);
        recview.setLayoutManager(new LinearLayoutManager(this));

        processdata();

        imageSlider();



        // Navigation bar Coding start from here---------------------------------------------------

        navigationView = findViewById(R.id.navView);
        drawerLayout = findViewById(R.id.drawerlayout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // code for showning image in action bar START
//        ActionBar actionBar =getActionBar();
//        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View view = inflater.inflate(R.layout.custom_image,null);
//        actionBar.setCustomView(view);
        getSupportActionBar().setIcon(R.drawable.quickyjoblogodownrembg);
        // code for showning image in action bar END

        // this segment for getting data from MainActivity class to this class
//        spp = getSharedPreferences("credentials",MODE_PRIVATE);
//        String value = spp.getString("email","");
//        emailiyaaa.setText(value);



        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.myprofile:
                        startActivity(new Intent(getApplicationContext(), myprofile.class));
                        break;
                    case R.id.logout:
                        startActivity(new Intent(getApplicationContext(), Logout.class));
                        break;

                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment temp =null;
                switch (item.getItemId())
                {
                    case R.id.home_bottom:
                        startActivity(new Intent(getApplicationContext(),dashboard.class));
                        break;

                    case R.id.category_bottom:
                        startActivity(new Intent(getApplicationContext(),categoryBottom.class));
                        break;

                    case R.id.setting:
                        startActivity(new Intent(getApplicationContext(), Settings.class));
                    break;

                }
                return true;
            }
        });



    }// OnCreate end here------ --


    private void imageSlider() {
        ImageSlider imageSlider = findViewById(R.id.slider);
        List<SlideModel> slideModel = new ArrayList<>();

       // slideModel.add(new SlideModel(R.drawable.slide_three, ScaleTypes.FIT));
        slideModel.add(new SlideModel("https://quickyitsolutions.netlify.app/assets/img/clients/client-3.png\n", ScaleTypes.FIT));
        slideModel.add(new SlideModel("https://quickyitsolutions.netlify.app/assets/img/clients/client-5.png\n", ScaleTypes.FIT));
        slideModel.add(new SlideModel("https://quickyitsolutions.netlify.app/assets/img/clients/client-4.png\n", ScaleTypes.FIT));
        slideModel.add(new SlideModel("https://quickyitsolutions.netlify.app/assets/img/clients/client-2.png\n", ScaleTypes.FIT));

        imageSlider.setImageList(slideModel,ScaleTypes.FIT);

    }

    private void processdata() {
        Call<List<fetch_dashboard_response_model>> call = apicontroller.getInstance().getapi().fetchModelC();
        call.enqueue(new Callback<List<fetch_dashboard_response_model>>() {
            @Override
            public void onResponse(Call<List<fetch_dashboard_response_model>> call, Response<List<fetch_dashboard_response_model>> response) {
                List<fetch_dashboard_response_model> data = response.body();
                dash_adapter adapter = new dash_adapter(data);
                recview.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<List<fetch_dashboard_response_model>> call, Throwable t) {
                Toast.makeText(dashboard.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    // NAvigation bar Coding Outside onCreate------

    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        if (actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // Navigation bar Coding End here ---------------------------------------------------------------------

}