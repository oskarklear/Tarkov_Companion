package com.tarkovcompanion.app;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class SearchRepository {

    private SearchDao searchDao;
    private LiveData<List<Search>> allSearches;
    private final String TAG = getClass().getSimpleName();

    SearchRepository(Application app) {
        AppDatabase db = AppDatabase.getDatabase(app);
        searchDao = db.getSearchDao();
        allSearches = searchDao.loadAllSearches();
    }

    void insert(Search search) {
        AppDatabase.databaseWriteExectutor.execute(() -> {
            searchDao.insert(search);
        });
    }

    void delete(Search search) {
        AppDatabase.databaseWriteExectutor.execute(() -> {
            searchDao.delete(search);
        });
    }

    void deleteAll() {
        AppDatabase.databaseWriteExectutor.execute(() -> {
            searchDao.deleteAll();
        });
    }

    LiveData<List<Search>> getAllSearches() {
        return allSearches;
    }

    LiveData<List<Search>> findSimilarSearch(String query) {
        return searchDao.findSimilarSearch(query);
    }




}
