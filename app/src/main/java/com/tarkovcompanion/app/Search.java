package com.tarkovcompanion.app;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Fts4;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "search_table")
public class Search {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "search")
    public String searchText;

    public Search(@NonNull String search) {
        searchText = search;
    }

    public Search() {
        searchText = "";
    }

    @NonNull
    public String getSearch() {
        return searchText;
    }
}
