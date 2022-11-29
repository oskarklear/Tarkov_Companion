package com.tarkovcompanion.app;

import com.google.gson.annotations.SerializedName;

public class ItemPrice {

    @SerializedName("vendor")
    private Vendor itemVendor;

    @SerializedName("price")
    private int price;

    @SerializedName("currency")
    private String currency;

    public Vendor getItemVendor() { return itemVendor; }
    public int getPrice() { return price; }
    public String getCurrency() { return currency; }


}
