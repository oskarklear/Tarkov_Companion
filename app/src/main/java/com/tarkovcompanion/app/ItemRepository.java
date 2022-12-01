package com.tarkovcompanion.app;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Future;

public class ItemRepository {

    private final ItemDao itemDao;
    private LiveData<List<Item>> allSavedItems;
    private final String TAG = getClass().getSimpleName();

    ItemRepository(Application app) {
        AppDatabase db = AppDatabase.getDatabase(app);
        itemDao = db.getItemDao();
        allSavedItems = itemDao.loadAllSavedItems();
    }

    void insert(Item item) {
        AppDatabase.databaseWriteExectutor.execute(() -> {
            itemDao.insert(item);
        });
    }

    void delete(Item item) {
        AppDatabase.databaseWriteExectutor.execute(() -> {
            itemDao.delete(item);
        });
    }

    void deleteAll() {
        AppDatabase.databaseWriteExectutor.execute(itemDao::deleteAll);
    }

    LiveData<List<Item>> getAllSavedItems() { return allSavedItems; }

    Future<Boolean> doesItemExist(String id) {
        return AppDatabase.databaseWriteExectutor.submit(() -> itemDao.isItemSaved(id) != 0);
    }
}
