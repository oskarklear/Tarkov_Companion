package com.tarkovcompanion.app;

import com.google.gson.annotations.SerializedName;

public class TarkovDevResponse {

    @SerializedName("data")
    private DataResponse dataResponse;

    public DataResponse getDataResponse() {
        return dataResponse;
    }

    public void setDataResponse(DataResponse dataResponse) {
        this.dataResponse = dataResponse;
    }

}
