package com.tarkovcompanion.app;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;


public class ItemResponse {
    @SerializedName("item")
    private List<Item> mItems;

    public ItemResponse() {
        mItems = new ArrayList<>();
    }

    public List<Item> getItems() {
        return mItems;
    }

    public void setItems(List<Item> items) {
        this.mItems = items;
    }
}
