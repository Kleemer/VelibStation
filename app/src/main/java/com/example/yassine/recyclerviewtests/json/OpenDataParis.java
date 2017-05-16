package com.example.yassine.recyclerviewtests.json;

import com.example.yassine.recyclerviewtests.json.JSONInfos;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface    OpenDataParis {

        String ENDPOINT = "https://opendata.paris.fr";
        @GET("/api/records/1.0/search/")
        Call<JSONInfos> listVelibStations(@Query("dataset") String query, @Query("rows") int rows);
    }