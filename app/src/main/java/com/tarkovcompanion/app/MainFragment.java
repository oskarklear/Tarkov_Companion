package com.tarkovcompanion.app;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.SearchRecentSuggestionsProvider;
import android.os.Bundle;
import android.provider.SearchRecentSuggestions;
import android.text.Editable;
import android.text.TextWatcher;
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

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.security.Key;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment {

    private ItemViewModel itemViewModel;
    private SearchViewModel searchViewModel;
    private RecyclerView recyclerView;
    private ItemAdapter adapter;
    private SearchAdapter autoCompleteAdapter;
    private ArrayList<Item> itemList;
    private Activity activity;

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
        activity =  requireActivity();
        itemViewModel = new ViewModelProvider((ViewModelStoreOwner) activity)
                .get(ItemViewModel.class);
        searchViewModel = new ViewModelProvider((ViewModelStoreOwner) activity)
                .get(SearchViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Log.i("Fragment State", "onCreateView() called");
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        setHasOptionsMenu(true);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager( new LinearLayoutManager(this.getContext()));
        adapter = new ItemAdapter();
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Context currentContext = requireContext();
        autoCompleteAdapter = new SearchAdapter(currentContext, R.layout.list_item_x_button, new ArrayList<Search>(), searchViewModel);

        AutoCompleteTextView searchView = (AutoCompleteTextView) view.findViewById(R.id.searchView);
        searchView.setThreshold(1);
        searchView.setAdapter(autoCompleteAdapter);
        itemViewModel.getItemLiveData().observe(getViewLifecycleOwner(), new Observer<List<Item>>() {
            @Override
            public void onChanged(List<Item> items) {
                Log.v("Error", "Change in item data observed");
                if (items != null) {
                    adapter.updateAdapterList((ArrayList<Item>) items);
                    Log.v("Error", "Adapter updated");
                }
            }
        });
        searchViewModel.getSearchLiveData().observe(getViewLifecycleOwner(), new Observer<List<Search>>() {
            @Override
            public void onChanged(List<Search> searches) {
                if (searches != null) {
                    Log.v("Error", "Change in search data observed");
                    autoCompleteAdapter.clear();
                    autoCompleteAdapter.addAll(searches);
                    autoCompleteAdapter.notifyDataSetChanged();
                }
            }
        });

        searchView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Log.v("Error", ""+ autoCompleteAdapter.getCount());
                if (autoCompleteAdapter != null && autoCompleteAdapter.getCount() != 0) {
                    ((AutoCompleteTextView) view).showDropDown();
                }

           }

        });
        searchView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    String searchQuery = searchView.getText().toString();
                    if (!searchQuery.equals("")) {
                        Log.v("Error", "Retrieving new items...");
                        Log.v("Error", searchQuery);
                        itemViewModel.retrieveItems(searchQuery);
                        searchViewModel.insert(new Search(searchQuery));
                        InputMethodManager imm = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
                        activity.getCurrentFocus().clearFocus();
                    }
                }
                return true;
            }
        });
        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int before) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                //autoCompleteAdapter.getFilter()
            }

            @Override
            public void afterTextChanged(Editable editable) {

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