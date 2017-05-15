package com.example.yassine.recyclerviewtests;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Yassine on 15/05/2017.
 */

public class VelibStation {

    @SerializedName("recordid")
    protected final String recordid;
    @SerializedName("fields")
    protected final Field field;

    public VelibStation(String recordid, Field field) {
        this.recordid = recordid;
        this.field = field;
    }
}