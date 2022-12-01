package com.tarkovcompanion.app;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import retrofit2.http.Url;

public class DetailedItem extends Fragment {

    private ItemViewModel itemViewModel;
    private Item item;

    public DetailedItem() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        itemViewModel = new ViewModelProvider((ViewModelStoreOwner) requireActivity())
                .get(ItemViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.item_page, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        TextView itemNameView = view.findViewById(R.id.nameView);
        TextView itemShortNameView = view.findViewById(R.id.shortNameView);
        TextView itemLastLowPriceView = view.findViewById(R.id.lowPriceView);
        TextView itemPricePerSlotView = view.findViewById(R.id.pricePerSlotView);
        TextView itemDayAverageView = view.findViewById(R.id.dayAverageView);
        TextView itemFleaMarketVeeView = view.findViewById(R.id.fleaMarketFeeView);
        TextView itemTraderPriceView = view.findViewById(R.id.traderPriceView);
        ImageView itemImageView = view.findViewById(R.id.imageView);

        itemNameView.setText(item.getItemName());
        itemShortNameView.setText(item.getShortName());
        itemLastLowPriceView.setText(String.format("%s₽", item.getLastLowPrice()));
        itemPricePerSlotView.setText(String.format("%s₽", calculatePricePerSlot()));
        itemDayAverageView.setText(String.format("%s₽", item.getAvg24hPrice()));
        itemFleaMarketVeeView.setText(String.format("%s₽",item.getFleaMarketFee()));
        itemTraderPriceView.setText(String.format("%s₽", calculateHighestTraderSellPrice()));

        boolean itemFavorited = itemViewModel.doesItemExist(item.getId());

        if (item.getLargeImageData() == null) {
            new ImageFromURLTask(itemImageView, item).execute(item.getLargeIconLink(), "l");
        } else {
            itemImageView.setImageBitmap(item.getLargeImageData());
        }

        Button backButton = view.findViewById(R.id.backButton);
        backButton.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getParentFragmentManager().popBackStackImmediate();
            }
        });

        Button favoriteButton = view.findViewById(R.id.favoriteButton);
        if (itemFavorited) {
            favoriteButton.setText("Remove From Favorites");
        }
        favoriteButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if (itemFavorited) {
                    itemViewModel.delete(item);
                } else {
                    itemViewModel.insert(item);
                }
            }
        });
    }

    public void setItem(Item item) {
        this.item = item;
    }

    private double calculatePricePerSlot() {
        return 1.0 * item.getLastLowPrice() / (item.getHeight() * item.getWidth());
    }

    private int calculateHighestTraderSellPrice() {
        int max = Integer.MIN_VALUE;
        for (ItemPrice itemPrice : item.getSellForItemPrices()) {
            if (!itemPrice.getItemVendor().getName().equals("Flea Market")
                && itemPrice.getPrice() > max ) {
                max = itemPrice.getPrice();
            }
        }
        return max;
    }




}