package com.example.yassine.recyclerviewtests;

import com.google.gson.annotations.SerializedName;

public class Field {

    @SerializedName("name")
    protected final String name;
    @SerializedName("address")
    protected final String address;
    @SerializedName("status")
    protected final String status;

    public Field(String name, String address, String status)
    {
        this.name = name;
        this.address = address;
        this.status = status;
    }
}
