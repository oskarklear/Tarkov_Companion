package com.tarkovcompanion.app;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class ItemViewModel extends ViewModel {
    private final MutableLiveData<List<Item>> mItemLiveData;
    private final TarkovDevFetcher mTarkovDevFetcher = new TarkovDevFetcher();
    private final QueryBuilder queryBuilder = new QueryBuilder();

    public ItemViewModel() {
        mItemLiveData = new MutableLiveData<>();
        mTarkovDevFetcher.fetchItems(queryBuilder.getDefaultQuery(), mItemLiveData);
    }
    public void retrieveItems(String search) {
        String query = queryBuilder.makeQueryFromSearch(search);
        mTarkovDevFetcher.fetchItems(query, mItemLiveData);
    }

    public LiveData<List<Item>> getItemLiveData() {
        return mItemLiveData;
    }
}
