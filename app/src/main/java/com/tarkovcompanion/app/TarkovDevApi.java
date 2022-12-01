package com.tarkovcompanion.app;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface TarkovDevApi {

    @POST("/graphql")
    public Call<TarkovDevResponse> fetchItems(@Body JsonObject graphQLQuery);

}
