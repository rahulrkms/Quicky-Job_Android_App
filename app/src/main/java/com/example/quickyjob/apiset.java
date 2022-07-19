package com.example.quickyjob;

import com.example.quickyjob.modelclass.FetchProfileDataResponseModel;
import com.example.quickyjob.modelclass.ForgetPasswordResponseModel;
import com.example.quickyjob.modelclass.SecurityQuestionVerificationResponse;
import com.example.quickyjob.modelclass.ShowProfileDataResponseModel;
import com.example.quickyjob.modelclass.UpdatePasswordModelClass;
import com.example.quickyjob.modelclass.UpdateProfile;
import com.example.quickyjob.modelclass.category_clickListener_response_model;
import com.example.quickyjob.modelclass.fetch_dashboard_response_model;
import com.example.quickyjob.modelclass.fetchcategorydata_response_model;
import com.example.quickyjob.modelclass.login_response_model;
import com.example.quickyjob.modelclass.signup_response_model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface apiset {


    @GET("fetchdata.php")
    Call<List<fetch_dashboard_response_model>> fetchModelC();


    @FormUrlEncoded
    @POST("signup.php")
    Call<signup_response_model> getregister(
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String password,
            @Field("mobile") String mobile,
            @Field("address") String address
    );

    @FormUrlEncoded
    @POST("login.php")
    Call<login_response_model> getlogin(
            @Field("email") String email,
            @Field("password") String password
    );


    @GET("fetchcategorydata.php")
    Call<List<fetchcategorydata_response_model>> getFetchCategoryData();

    @FormUrlEncoded
    @POST("fetch_cat_data.php")
    Call<List<category_clickListener_response_model>>getcat(
            @Field("catname") String catname
    );


    @FormUrlEncoded
    @POST("fetchprofiledata.php")
    Call<List<ShowProfileDataResponseModel>>getProfileData(
            @Field("email") String email

    );
    @FormUrlEncoded
    @POST("update_password.php")
    Call<UpdatePasswordModelClass>getUpdatedPassword(
      @Field("email") String email,
      @Field("current") String current,
      @Field("new") String newpass
    );


    @FormUrlEncoded
    @POST("fetch_on_myprofile.php")
    Call<List<FetchProfileDataResponseModel>> getProfileDataForUpdate(
      @Field("email") String email
    );

    @FormUrlEncoded
    @POST("update_profile.php")
    Call<UpdateProfile> updateProfileOfUser(
            @Field("email") String email,
            @Field("newname") String newname,
            @Field("newmob") String newmob
    );

    @FormUrlEncoded
    @POST("security_question_verification.php")
    Call<SecurityQuestionVerificationResponse> verifySecurityQuestion (
            @Field("question") String question,
            @Field("answer") String answer,
            @Field("email") String email

    );

    @FormUrlEncoded
    @POST("match_security_question.php")
    Call<ForgetPasswordResponseModel> forgePass(
            @Field("match_ques") String matchQuestion,
            @Field("match_ans") String matchAnswer,
            @Field("email") String email
    );

    @FormUrlEncoded
    @POST("forget_new_password.php")
    Call<ForgetPasswordResponseModel> resetForgetPassword(
      @Field("new_password") String newPassword,
      @Field("email") String email
    );


}
