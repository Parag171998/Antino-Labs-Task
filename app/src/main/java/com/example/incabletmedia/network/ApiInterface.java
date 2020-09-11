package com.example.incabletmedia.network;

import com.example.incabletmedia.model.Restaurant;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface ApiInterface {

    @Headers("user-key: 1d63821dbb228ee5d09e8c8f7cfe10c3")
    @GET("collections?city_id=pune&lat=18.5204&lon=73.8567&count=20")
    Call<Restaurant> getRestaurant();
}
