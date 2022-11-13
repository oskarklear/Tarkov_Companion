package com.tarkovcompanion.app;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

public class DetailedItem extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "name";
    private static final String ARG_PARAM2 = "description";

    // TODO: Rename and change types of parameters
    private String name;
    private String description;

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
        if (getArguments() != null) {
            name = getArguments().getString(ARG_PARAM1);
            description = getArguments().getString(ARG_PARAM2);
        }
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
        TextView itemDayAverageView = view.findViewById(R.id.dayAverageView);
        itemNameView.setText(name);
        itemDayAverageView.setText(description + "₽");

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
                //TODO: Add to favorites database
            }
        });
    }
}