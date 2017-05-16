package com.example.yassine.recyclerviewtests.json;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class JSONInfos {
    @SerializedName("nhits")
    public int nhits;
    @SerializedName("records")
    public ArrayList<VelibStation> records;

    JSONInfos(int nhits, ArrayList<VelibStation> records)
    {
        this.nhits = nhits;
        this.records = records;
    }
}
