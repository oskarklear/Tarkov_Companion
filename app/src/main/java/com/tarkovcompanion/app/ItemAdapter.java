package com.tarkovcompanion.app;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    // creating a variable for array list and context.
    private ArrayList<Item> itemList;

    // creating a constructor for our variables.
    public ItemAdapter() { itemList = new ArrayList<>(); }

    public void updateAdapterList(ArrayList<Item> newList) {
        itemList.clear();
        itemList.addAll(newList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // below line is to inflate our layout.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ViewHolder holder, int position) {
        // setting data to our views of recycler view.
        Item item = itemList.get(position);
        holder.itemNameTV.setText(item.getItemName());
        holder.itemDescTV.setText(item.getItemDescription());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // creating variables for our views.
        private final TextView itemNameTV;
        private final TextView itemDescTV;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our views with their ids.
            itemNameTV = itemView.findViewById(R.id.itemName);
            itemDescTV = itemView.findViewById(R.id.itemDescription);
        }
    }
}
