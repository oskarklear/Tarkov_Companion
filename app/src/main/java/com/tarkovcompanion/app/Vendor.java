package com.tarkovcompanion.app;

import com.google.gson.annotations.SerializedName;

public class Vendor {

    @SerializedName("name")
    private String name;

    public String getName() { return name; }

}
