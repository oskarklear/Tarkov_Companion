package com.tarkovcompanion.app;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    // creating a variable for array list and context.
    private ArrayList<Item> itemList;
    private Context context;
    // creating a constructor for our variables.
    public ItemAdapter(Context context)
    {
        this.context = context;
        itemList = new ArrayList<>();
    }

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
        holder.recyclerItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                fragmentJump(item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // creating variables for our views.
        private final TextView itemNameTV;
        private final TextView itemDescTV;
        private final RelativeLayout recyclerItem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our views with their ids.
            itemNameTV = itemView.findViewById(R.id.itemName);
            itemDescTV = itemView.findViewById(R.id.itemDescription);
            recyclerItem = itemView.findViewById(R.id.recyclerItem);
        }
    }

    private void fragmentJump(Item item) {
        Fragment mFragment = new DetailedItem();
        Bundle mBundle = new Bundle();
        mBundle.putString("name", item.getItemName());
        mBundle.putString("description", item.getItemDescription());
        mFragment.setArguments(mBundle);
        switchContent(R.id.fragmentContainerView, mFragment);
    }

    public void switchContent(int id, Fragment fragment) {
        if (context == null)
            return;
        if (context instanceof MainActivity) {
            MainActivity mainActivity = (MainActivity) context;
            Fragment frag = fragment;
            mainActivity.switchContent(id, frag);
        }

    }
}
