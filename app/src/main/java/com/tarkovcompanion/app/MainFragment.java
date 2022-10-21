package com.tarkovcompanion.app;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment {

    private ItemViewModel itemViewModel;
    private RecyclerView recyclerView;
    private ItemAdapter adapter;
    private ArrayList<Item> itemList;


    public MainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();

        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Activity activity =  requireActivity();
        itemViewModel = new ViewModelProvider((ViewModelStoreOwner) activity)
                .get(ItemViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Log.i("Fragment State", "onCreateView() called");
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        setHasOptionsMenu(true);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager( new LinearLayoutManager(this.getContext()));
        adapter = new ItemAdapter();
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        SearchView searchView = (SearchView) view.findViewById(R.id.searchView);
        itemViewModel.getItemLiveData().observe(getViewLifecycleOwner(), new Observer<List<Item>>() {
            @Override
            public void onChanged(List<Item> items) {
                Log.v("Error", "Change in data observed");
                if (items != null) {
                    adapter.updateAdapterList((ArrayList<Item>) items);
                    Log.v("Error", "Adapter updated");
                }
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.v("Error", "Retrieving new items...");
                itemViewModel.retrieveItems(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //filter(newText);
                return false;
            }
        });

    }
    @Override
    public void onStart() {
        super.onStart();
        Log.i("Fragment State", "onStart() called");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("Fragment State", "onResume() called");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("Fragment State", "onPause() called");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i("Fragment State", "onStop() called");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("Fragment State", "onDestroy() called");
    }


    private void filter(String text) {
        ArrayList<Item> filteredlist = new ArrayList<>();

        for (Item item : Objects.requireNonNull(itemViewModel.getItemLiveData().getValue())) {
            if (item.getItemName().toLowerCase().contains(text.toLowerCase())) {
                filteredlist.add(item);
            }
        }
        if (!filteredlist.isEmpty()) {
            adapter.updateAdapterList(filteredlist);
        }
    }

    private void buildRecyclerView() {

        itemList = new ArrayList<Item>();

        itemList.add(new Item("Hera Arms CQR tactical foregrip", "30,788₽"));
        itemList.add(new Item("MP-155 Ultima large recoil pad", "24,552₽"));
        itemList.add(new Item("VKBO army bag", "14,112₽"));
        itemList.add(new Item("9x21mm P gzh", "831₽"));
        itemList.add(new Item("MPX/MCX PMM ULSS foldable stock", "21,300₽"));

        //adapter = new ItemAdapter(itemList, this.getContext());

        LinearLayoutManager manager = new LinearLayoutManager(this.getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }
}