package com.example.yassine.recyclerviewtests;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

interface OpenDataParis {

        String ENDPOINT = "https://opendata.paris.fr";
        @GET("/api/records/1.0/search/")
        Call<JSONInfos> listVelibStations(@Query("dataset") String query, @Query("rows") int rows);
    }