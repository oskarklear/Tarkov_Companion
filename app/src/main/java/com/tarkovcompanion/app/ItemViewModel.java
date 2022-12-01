package com.tarkovcompanion.app;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.concurrent.Future;

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

    public Future<Boolean> doesItemExist(String id) { return repository.doesItemExist(id); }

    public LiveData<List<Item>> getItemLiveData() {
        return mItemLiveData;
    }
}
