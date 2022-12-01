package com.tarkovcompanion.app;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TarkovDevFetcher {

    private final TarkovDevApi mTarkovDevApi;
    private final Retrofit mRetrofit;

    public TarkovDevFetcher() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl("https://api.tarkov.dev")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mTarkovDevApi = mRetrofit.create(TarkovDevApi.class);
    }

    public void fetchItems(String query, MutableLiveData<List<Item>> responseLiveData, boolean replace) {
        Log.v("Error", "Fetching...");
        JsonObject queryObject = new JsonObject();
        queryObject.addProperty("query", query);
        Call<TarkovDevResponse> tarkovDevRequest = mTarkovDevApi.fetchItems(queryObject);
        tarkovDevRequest.enqueue(new Callback<TarkovDevResponse>() {
            @Override
            public void onResponse(@NonNull Call<TarkovDevResponse> call,
                                   @NonNull Response<TarkovDevResponse> response) {
                TarkovDevResponse tarkovDevResponse = response.body();
                if (tarkovDevResponse == null) {
                    Log.v("Error", "API Response was null!");
                    return;
                }
                DataResponse dataResponse = tarkovDevResponse.getDataResponse();
                List<Item> items = dataResponse.getItems();
                if (replace) {
                    responseLiveData.setValue(items);
                } else {
                    List<Item> currentItems = responseLiveData.getValue();
                    if (currentItems != null) {
                        currentItems.addAll(items);
                        responseLiveData.setValue(currentItems);
                    }
                }

                Log.v("Error", "Items fetched...");
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.v("Error", "Fetching failed...");
                Log.v("Error", t.getMessage());
            }

        });
    }

}
