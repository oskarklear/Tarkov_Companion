package com.tarkovcompanion.app;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Search.class, Item.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract SearchDao getSearchDao();
    public abstract ItemDao getItemDao();
    private static volatile AppDatabase instance;
    private static final int numberOfThreads = 2;
    static final ExecutorService databaseWriteExectutor =
            Executors.newFixedThreadPool(numberOfThreads);

    static AppDatabase getDatabase(final Context context) {
        if (instance == null) {
            synchronized (AppDatabase.class) {
                instance = Room.databaseBuilder(
                        context.getApplicationContext(),
                        AppDatabase.class,
                        "app_database").build();
            }
        }
        return instance;
    }

}

