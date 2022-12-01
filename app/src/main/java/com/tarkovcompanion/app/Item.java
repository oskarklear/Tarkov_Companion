package com.tarkovcompanion.app;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Entity(tableName = "item_table")
public class Item {
    @PrimaryKey
    @NonNull
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String itemName;
    @SerializedName("shortName")
    private String shortName;
    @SerializedName("description")
    private String description;
    @SerializedName("lastLowPrice")
    private int lastLowPrice;
    @SerializedName("avg24hPrice")
    private int avg24hPrice;
    @SerializedName("sellFor")
    private List<ItemPrice> sellForItemPrices;
    @SerializedName("buyFor")
    private List<ItemPrice> buyForItemPrices;
    @SerializedName("height")
    private int height;
    @SerializedName("width")
    private int width;
    @SerializedName("fleaMarketFee")
    private int fleaMarketFee;
    @SerializedName("iconLink")
    private String iconLink;
    @SerializedName("image512pxLink")
    private String largeIconLink;
    @Ignore
    private Bitmap smallImageData;
    @Ignore
    private Bitmap largeImageData;

    public void setSmallImageData(Bitmap imageData) {
        this.smallImageData = imageData;
    }

    public Bitmap getSmallImageData() { return smallImageData; }

    public void setLargeImageData(Bitmap imageData) {
        this.largeImageData = imageData;
    }

    public Bitmap getLargeImageData() {
        return largeImageData;
    }

    public Item() {
        this.id = "";
        this.favorited = false;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getAvg24hPrice() {
        return avg24hPrice;
    }

    public void setAvg24hPrice(int avg24hPrice) {
        this.avg24hPrice = avg24hPrice;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLastLowPrice() {
        return lastLowPrice;
    }

    public void setLastLowPrice(int lastLowPrice) {
        this.lastLowPrice = lastLowPrice;
    }

    public List<ItemPrice> getSellForItemPrices() {
        return sellForItemPrices;
    }

    public void setSellForItemPrices(List<ItemPrice> sellForItemPrices) {
        this.sellForItemPrices = sellForItemPrices;
    }

    public List<ItemPrice> getBuyForItemPrices() {
        return buyForItemPrices;
    }

    public void setBuyForItemPrices(List<ItemPrice> buyForItemPrices) {
        this.buyForItemPrices = buyForItemPrices;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getFleaMarketFee() {
        return fleaMarketFee;
    }

    public void setFleaMarketFee(int fleaMarketFee) {
        this.fleaMarketFee = fleaMarketFee;
    }

    public String getIconLink() {
        return iconLink;
    }

    public void setIconLink(String iconLink) {
        this.iconLink = iconLink;
    }

    public String getLargeIconLink() { return largeIconLink; }

    public void setLargeIconLink(String largeIconLink) { this.largeIconLink = largeIconLink; }
}
