package com.tarkovcompanion.app;

public class QueryBuilder {
    // "{ \"query\": \"{ items { name description types avg24hPrice basePrice width height changeLast48hPercent iconLink link sellFor { price source } } }\" }";
    private final static String defaultQuery = "{ \"query\": \"{ items(limit: 5 offset: 0) { name description } }\" }";

    public static String getDefaultQuery() {
        return defaultQuery;
    }

}
