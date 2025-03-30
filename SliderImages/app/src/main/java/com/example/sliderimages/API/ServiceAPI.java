//Nguyễn Phan Minh Trí - 22110443
package com.example.sliderimages.API;


import com.example.sliderimages.MessageModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ServiceAPI {
    @FormUrlEncoded
    @POST("newimagesmanager.php")
    Call<MessageModel> LoadImageSlider(@Field("position") int position);
}
