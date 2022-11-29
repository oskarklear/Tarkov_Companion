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

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "name";
    private static final String ARG_PARAM2 = "description";

    private Activity activity;
    private Context context;
    private ItemViewModel itemViewModel;

    private Item item;

    public DetailedItem() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetailedItem.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailedItem newInstance(String param1, String param2) {
        DetailedItem fragment = new DetailedItem();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = requireActivity();
        itemViewModel = new ViewModelProvider((ViewModelStoreOwner) activity)
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
        TextView itemDescriptionView = view.findViewById(R.id.itemDescriptionView);
        TextView itemLastLowPriceView = view.findViewById(R.id.lowPriceView);
        TextView itemPricePerSlotView = view.findViewById(R.id.pricePerSlotView);
        TextView itemDayAverageView = view.findViewById(R.id.dayAverageView);
        TextView itemFleaMarketVeeView = view.findViewById(R.id.fleaMarketFeeView);
        TextView itemTraderPriceView = view.findViewById(R.id.traderPriceView);
        ImageView itemImageView = view.findViewById(R.id.imageView);

        // TODO: remove this after getting image loading
        TextView temporaryIconLink = view.findViewById(R.id.temporaryIconLink);

        itemNameView.setText(item.getItemName());
        itemDescriptionView.setText(item.getDescription());
        itemLastLowPriceView.setText(String.format("%s₽", item.getLastLowPrice()));
        itemPricePerSlotView.setText(String.format("%s₽", calculatePricePerSlot()));
        itemDayAverageView.setText(String.format("%s₽", item.getAvg24hPrice()));
        itemFleaMarketVeeView.setText(String.format("%s₽",item.getFleaMarketFee()));
        itemTraderPriceView.setText(String.format("%s₽", calculateHighestTraderSellPrice()));

        // TODO: remove this after getting image loading
        temporaryIconLink.setText(item.getIconLink());

        // TODO: do this asynchronously
        URL imageUrl = null;
        try {
            imageUrl = new URL(item.getIconLink());
            Bitmap bmp = BitmapFactory.decodeStream(imageUrl.openConnection().getInputStream());
            itemImageView.setImageBitmap(bmp);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Button backButton = view.findViewById(R.id.backButton);
        backButton.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getParentFragmentManager().popBackStackImmediate();
            }
        });

        Button favoriteButton = view.findViewById(R.id.favoriteButton);
        favoriteButton.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v)
            {
                itemViewModel.insert(item);
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