package com.tarkovcompanion.app;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SearchDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Search search);

    @Delete
    void delete(Search search);

    @Query("DELETE FROM search_table")
    void deleteAll();

    @Query("SELECT search FROM search_table")
    public LiveData<List<Search>> loadAllSearches();

    @Query("SELECT search FROM search_table WHERE search LIKE :query")
    public LiveData<List<Search>> findSimilarSearch(String query);


}
