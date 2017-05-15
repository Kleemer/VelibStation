package com.example.yassine.recyclerviewtests;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private MyAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<VelibStation> values = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("size", "" + values.size());
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MyAdapter(values);
        mRecyclerView.setAdapter(mAdapter);
        fillValues();
    }

    private void fillValues()
    {
        Retrofit retrofit =
                new Retrofit.Builder().baseUrl(OpenDataParis.ENDPOINT)
                                      .addConverterFactory(GsonConverterFactory.create())
                                      .build();
        OpenDataParis velibService = retrofit.create(OpenDataParis.class);

        final Call<JSONInfos> listVelibStations = velibService.listVelibStations("stations-velib-disponibilites-en-temps-reel", 100);
        listVelibStations.enqueue(new Callback<JSONInfos>() {
            @Override
            public void onResponse(Call<JSONInfos> call, Response<JSONInfos> response) {
                if(response.isSuccessful()) {
                    List<VelibStation> res = response.body().records;
                    mAdapter.setmDataset(res);
                    mAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onFailure(Call<JSONInfos> call, Throwable t) {
                Log.e("", "errorFail");
            }
        });
    }
}