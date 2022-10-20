package com.tarkovcompanion.app;

import com.google.gson.annotations.SerializedName;

public class TarkovDevResponse {

    @SerializedName("items")
    public ItemResponse mItems;

    public ItemResponse getItems() {
        return mItems;
    }

    public void setItems(ItemResponse items) {
        this.mItems = items;
    }

}
