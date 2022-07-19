package com.example.quickyjob;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class apicontroller {


    final static String url = "http://192.168.1.5/quickyapi/";
    //192.168.1.45
    public static apicontroller clientoject;
    public static Retrofit retrofit;


    apicontroller(){
//        Gson gson = new GsonBuilder().setLenient().create();
//        OkHttpClient client = new OkHttpClient();


        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized apicontroller getInstance(){
        if (clientoject==null){
            clientoject = new apicontroller();
        }
        return clientoject;
    }

    public apiset getapi(){
        return retrofit.create(apiset.class);
    }
}
