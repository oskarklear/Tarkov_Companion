package com.tarkovcompanion.app;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

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

    boolean doesItemExist(String id) { return itemDao.isItemSaved(id) != 0; }
}
