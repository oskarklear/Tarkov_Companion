package com.tarkovcompanion.app;

import com.google.gson.annotations.SerializedName;

public class TarkovDevResponse {

    @SerializedName("data")
    public DataResponse dataResponse;

    public DataResponse getDataResponse() {
        return dataResponse;
    }

    public void setDataResponse(DataResponse dataResponse) {
        this.dataResponse = dataResponse;
    }

}
