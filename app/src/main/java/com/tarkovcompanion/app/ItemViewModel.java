package com.tarkovcompanion.app;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class ItemViewModel extends AndroidViewModel {
    private final ItemRepository repository;
    private final MutableLiveData<List<Item>> mItemLiveData;
    private final LiveData<List<Item>> mFavoriteLiveData;
    private final TarkovDevFetcher mTarkovDevFetcher = new TarkovDevFetcher();
    private final QueryBuilder queryBuilder = new QueryBuilder();

    public ItemViewModel(Application app) {
        super(app);
        this.repository = new ItemRepository(app);
        mItemLiveData = new MutableLiveData<>();
        mTarkovDevFetcher.fetchItems(queryBuilder.getDefaultQuery(), mItemLiveData);
        mFavoriteLiveData = repository.getAllSavedItems();
    }
    public void retrieveItemsFromSearch(String search) {
        String query = queryBuilder.makeQueryFromSearch(search);
        mTarkovDevFetcher.fetchItems(query, mItemLiveData);
    }

    public void insert(Item item) { repository.insert(item); }

    public void delete(Item item) { repository.delete(item); };

    public void deleteAll() { repository.deleteAll(); }

    public LiveData<List<Item>> getAllSavedItems() { return repository.getAllSavedItems(); }

    public boolean doesItemExist(String id) { return repository.doesItemExist(id); }

    public LiveData<List<Item>> getItemLiveData() {
        return mItemLiveData;
    }
}
