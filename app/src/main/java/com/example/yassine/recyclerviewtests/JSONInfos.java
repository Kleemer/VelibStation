package com.example.yassine.recyclerviewtests;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JSONInfos {
    @SerializedName("nhits")
    int nhits;
    @SerializedName("records")
    List<VelibStation> records;

    JSONInfos(int nhits, List<VelibStation> records)
    {
        this.nhits = nhits;
        this.records = records;
    }
}
