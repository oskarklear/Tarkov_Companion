package com.tarkovcompanion.app;

import java.util.List;

public class QueryBuilder {
    // "{ \"query\": \"{ items { name description types avg24hPrice basePrice width height changeLast48hPercent iconLink link sellFor { price source } } }\" }";

    private final String queryContents = "{ id name shortName description lastLowPrice avg24hPrice " +
            "sellFor { vendor { name } price currency } " +
            "buyFor { vendor { name } price currency } " +
            "height width fleaMarketFee iconLink image512pxLink }";
    private final String defaultQuery = "{ items(offset: 0) " + queryContents + "}";
    private int loadingLimit = 5;

    public QueryBuilder() {}

    public String getDefaultQuery() {
        return defaultQuery;
    }

    public String makeQueryFromSearch(String search) {
        if (search.equals("")) {
            return defaultQuery;
        }
        return "{ items(offset: 0 name: \"" + search + "\")" + queryContents + "}";
    }

    public String makeQueryFromIds(List<String> ids) {
        StringBuilder idListSB = new StringBuilder();
        idListSB.append('[');
        for (String id : ids) {
            idListSB.append('"');
            idListSB.append(id);
            idListSB.append('"');
            idListSB.append(',');
        }
        idListSB.append(']');
        return "{ items(ids: " + idListSB.toString() + ")" + queryContents + "}";
    }

}
