package com.tarkovcompanion.app;

import com.google.gson.annotations.SerializedName;

public class Item {
    @SerializedName("name")
    private String itemName;
    @SerializedName("avg24hPrice")
    private String itemDescription;

    public Item(String itemName, String itemDescription) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }
}
