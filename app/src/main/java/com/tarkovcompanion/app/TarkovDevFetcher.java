package com.tarkovcompanion.app;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

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

    public LiveData<List<Item>> fetchItems(String query) {
        MutableLiveData<List<Item>> responseLiveData = new MutableLiveData<>();
        Call<TarkovDevResponse> tarkovDevRequest = mTarkovDevApi.fetchItems(query);

        tarkovDevRequest.enqueue(new Callback<TarkovDevResponse>() {
            @Override
            public void onResponse(@NonNull Call<TarkovDevResponse> call,
                                   @NonNull Response<TarkovDevResponse> response) {
                TarkovDevResponse tarkovDevResponse = response.body();
                ItemResponse itemResponse = tarkovDevResponse.getItems();
                List<Item> items = itemResponse.getItems();
                responseLiveData.setValue(items);
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                // error handling and logging
            }

        });

        return responseLiveData;
    }

}
