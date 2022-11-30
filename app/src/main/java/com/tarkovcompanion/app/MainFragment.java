package com.tarkovcompanion.app;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment
{

    private ItemViewModel itemViewModel;
    private SearchViewModel searchViewModel;
    private RecyclerView recyclerView;
    private ItemAdapter adapter;
    private SearchAdapter autoCompleteAdapter;
    private FragmentActivity activity;
    private Context context;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = requireContext();
        activity = requireActivity();
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
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager( new LinearLayoutManager(this.getContext()));
        adapter = new ItemAdapter(context);
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        autoCompleteAdapter = new SearchAdapter(context, R.layout.list_item_x_button, new ArrayList<Search>(), searchViewModel);
        AutoCompleteTextView searchView = (AutoCompleteTextView) view.findViewById(R.id.searchView);
        searchView.setThreshold(1);
        searchView.setAdapter(autoCompleteAdapter);
        autoCompleteAdapter.setSearchBarTextView(searchView);
        TextView historyItem = view.findViewById(R.id.list_item_x_button);
        AppCompatImageButton menuButton = view.findViewById(R.id.imageButton);
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
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = requireActivity()
                        .getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragmentContainerView, new FavoritesPage());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        searchView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    if (autoCompleteAdapter != null && autoCompleteAdapter.getCount() != 0) {
                        ((AutoCompleteTextView) view).showDropDown();
                    }
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
                        itemViewModel.retrieveItemsFromSearch(searchQuery);
                        searchViewModel.insert(new Search(searchQuery));
                    }
                    InputMethodManager imm = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
                    activity.getCurrentFocus().clearFocus();
                }
                return true;
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
}