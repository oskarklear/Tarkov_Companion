package com.tarkovcompanion.app;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.provider.SearchRecentSuggestions;

public class SearchableActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(getIntent().getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            SearchRecentSuggestions suggestions = new SearchRecentSuggestions(this,
                    SuggestionProvider.AUTHORITY, SuggestionProvider.MODE);
            suggestions.saveRecentQuery(query, null);
            doMySearch(query);
        }
    }

    /**
     * Performs a search and passes the results to the container
     * Activity that holds your Fragments.
     */
    public void doMySearch(String query) {
        // TODO: implement this
    }
}
