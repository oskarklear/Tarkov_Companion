package com.tarkovcompanion.app;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FavoritesPage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FavoritesPage extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private ItemAdapter adapter;
    private RecyclerView recyclerView;
    private Activity activity;
    private Context context;

    public FavoritesPage() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FavoritesPage.
     */
    // TODO: Rename and change types and number of parameters
    public static FavoritesPage newInstance(String param1, String param2) {
        FavoritesPage fragment = new FavoritesPage();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = requireContext();
        activity =  requireActivity();
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
    }
}