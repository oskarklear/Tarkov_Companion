package com.tarkovcompanion.app;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class ItemViewModel extends ViewModel {
    public final LiveData<List<Item>> mItemLiveData;
    private final TarkovDevFetcher mTarkovDevFetcher = new TarkovDevFetcher();

    public ItemViewModel() {
        mItemLiveData = mTarkovDevFetcher.fetchItems(QueryBuilder.getDefaultQuery());
    }

    public LiveData<List<Item>> getItemLiveData() {
        return mItemLiveData;
    }
}
