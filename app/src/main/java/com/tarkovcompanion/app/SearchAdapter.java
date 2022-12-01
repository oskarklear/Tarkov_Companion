package com.tarkovcompanion.app;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends ArrayAdapter<Search> {

    private EditText searchBarEditText;
    private List<Search> searchList;
    private LayoutInflater inflater;
    private Context context;
    private SearchViewModel searchViewModel;

    public SearchAdapter(Context context, int resourceId, ArrayList<Search> searchList, SearchViewModel searchViewModel) {
        super(context, resourceId, searchList);
        this.context = context;
        this.searchList = searchList;
        this.searchViewModel = searchViewModel;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.list_item_x_button, parent, false);
        }

        TextView searchText = (TextView)view.findViewById(R.id.searchTextView);
        ImageView delete = (ImageView)view.findViewById(R.id.deleteSearchButton);

        Search search = searchList.get(position);

        searchText.setText(search.searchText);

        searchText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (searchBarEditText != null) {
                    searchBarEditText.setText(searchText.getText());
                    searchBarEditText.setSelection(searchBarEditText.getText().length());
                }
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchList.remove(position);
                searchViewModel.delete(search);
                notifyDataSetChanged();
            }
        });


        return view;
    }

    public void setSearchBarEditText(EditText searchBarEditText) { this.searchBarEditText = searchBarEditText; }

    public void clear() {
        this.searchList.clear();
    }

    public void addAll(List<Search> searchList) {
        this.searchList.addAll(searchList);
    }

    @Override
    public int getCount() {
        return searchList.size();
    }

    @Override
    public Search getItem(int position) {
        return searchList.get(position);
    }

    @Override
    public Filter getFilter() {
        return super.getFilter();
    }




}
