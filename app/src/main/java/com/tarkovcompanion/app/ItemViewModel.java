package com.tarkovcompanion.app;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

public class ItemViewModel extends AndroidViewModel {
    private final ItemRepository repository;
    private final MutableLiveData<List<Item>> mItemLiveData;
    private final MediatorLiveData<List<Item>> mFavoriteLiveData;
    private final TarkovDevFetcher mTarkovDevFetcher = new TarkovDevFetcher();
    private final QueryBuilder queryBuilder = new QueryBuilder();

    private String recentSearch = "";
    private int offset = 0;

    private final int limit = 10;

    public ItemViewModel(Application app) {
        super(app);
        this.repository = new ItemRepository(app);
        mItemLiveData = new MutableLiveData<>();
        mFavoriteLiveData = new MediatorLiveData<>();
        String query = queryBuilder.makeQueryFromSearch("", 0, limit);
        mTarkovDevFetcher.fetchItems(query, mItemLiveData, true);
        mFavoriteLiveData.addSource(repository.getAllSavedItems(), mFavoriteLiveData::setValue);
    }

    public void retrieveItemsFromSearch(String search) {
        if (!recentSearch.equals(search)) {
            recentSearch = search;
        }
        String query = queryBuilder.makeQueryFromSearch(search, 0, limit);
        mTarkovDevFetcher.fetchItems(query, mItemLiveData, true);
        offset = 0;
    }

    public void pollMoreItems() {
        String query = queryBuilder.makeQueryFromSearch(recentSearch, offset, limit);
        mTarkovDevFetcher.fetchItems(query, mItemLiveData, false);
        offset += limit;
    }

    public void updateItemsFromSavedList() {
        List<Item> favorites = mFavoriteLiveData.getValue();
        if (favorites == null) {
            return;
        }

        List<String> ids = new ArrayList<>();
        for (Item i : favorites) {
            ids.add(i.getId());
            Log.i("Error", "ID:" +i.getId());
        }
        String query = queryBuilder.makeQueryFromIds(ids);

        mTarkovDevFetcher.fetchItems(query, mFavoriteLiveData, true);
        repository.insertAll(mFavoriteLiveData.getValue());
    }

    public void insert(Item item) { repository.insert(item); }

    public void delete(Item item) { repository.delete(item); };

    public void deleteAll() { repository.deleteAll(); }

    public LiveData<List<Item>> getAllSavedItems() { return mFavoriteLiveData; }

    public Future<Boolean> doesItemExist(String id) { return repository.doesItemExist(id); }

    public LiveData<List<Item>> getItemLiveData() {
        return mItemLiveData;
    }
}
