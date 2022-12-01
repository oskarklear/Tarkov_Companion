package com.tarkovcompanion.app;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class Converters {
    @TypeConverter
    public static List<ItemPrice> itemPricesFromString(String json) {
        Type listType = new TypeToken<List<ItemPrice>>() {}.getType();
        return new Gson().fromJson(json, listType);
    }

    @TypeConverter
    public static String stringFromItemPrices(List<ItemPrice> itemPrices) {
        Gson gson = new Gson();
        return gson.toJson(itemPrices);
    }
}
