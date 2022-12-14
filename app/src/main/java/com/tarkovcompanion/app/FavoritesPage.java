package com.tarkovcompanion.app;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class FavoritesPage extends Fragment {

    private ItemViewModel itemViewModel;
    private ItemAdapter adapter;
    private RecyclerView recyclerView;
    private FragmentActivity activity;
    private Context context;

    public FavoritesPage() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = requireContext();
        activity = requireActivity();
        itemViewModel = new ViewModelProvider((ViewModelStoreOwner) activity)
                .get(ItemViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites_page, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.favoritesList);
        recyclerView.setLayoutManager( new LinearLayoutManager(this.getContext()));
        adapter = new ItemAdapter(context);
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Button backButton = view.findViewById(R.id.favoritesBackButton);
        backButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Button Pressed", "Should go back now");

                getParentFragmentManager().popBackStackImmediate();
            }
        });
        itemViewModel.getAllSavedItems().observe(getViewLifecycleOwner(), new Observer<List<Item>>() {
            @Override
            public void onChanged(List<Item> items) {
                Log.v("Error", "Change in item data observed");
                if (items != null) {
                    adapter.updateAdapterList((ArrayList<Item>) items);
                    Log.v("Error", "Adapter updated");
                }
            }
        });
    }
}