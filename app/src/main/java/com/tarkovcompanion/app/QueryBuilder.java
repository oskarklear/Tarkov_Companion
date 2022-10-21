package com.tarkovcompanion.app;

public class QueryBuilder {
    // "{ \"query\": \"{ items { name description types avg24hPrice basePrice width height changeLast48hPercent iconLink link sellFor { price source } } }\" }";
    private final String defaultQuery = "{ items(limit: 5 offset: 0) { name avg24hPrice } }";
    private int loadingLimit = 5;

    public QueryBuilder() {}

    public String getDefaultQuery() {
        return defaultQuery;
    }

    public String makeQueryFromSearch(String search) {
        if (search.equals("")) {
            return defaultQuery;
        }
        return "{ items(limit: " + loadingLimit + " offset: 0 name: \"" + search + "\") { name avg24hPrice } }";
    }


}
