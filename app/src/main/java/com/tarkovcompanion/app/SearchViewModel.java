package com.tarkovcompanion.app;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class SearchViewModel extends AndroidViewModel {

    private final SearchRepository repository;

    private final LiveData<List<Search>> searchLiveData;

    public SearchViewModel(Application app) {
        super(app);
        this.repository = new SearchRepository(app);
        searchLiveData = repository.getAllSearches();
    }

    public void insert(Search search) { repository.insert(search); }

    public void delete(Search search) { repository.delete(search); };

    public void deleteAll() { repository.deleteAll(); }

    LiveData<List<Search>> getSearchLiveData() { return searchLiveData; }

    LiveData<List<Search>> findSimilarSearch(String query) {
        return repository.findSimilarSearch(query);
    };

}
