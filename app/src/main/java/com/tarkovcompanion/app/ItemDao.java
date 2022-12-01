package com.tarkovcompanion.app;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Item item);

    @Delete
    void delete(Item item);

    @Query("DELETE FROM item_table")
    void deleteAll();

    @Query("SELECT * FROM item_table")
    public LiveData<List<Item>> loadAllSavedItems();

    @Query("SELECT * FROM item_table WHERE id = :id")
    int isItemSaved(String id);


}
