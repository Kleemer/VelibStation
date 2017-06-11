package com.example.yassine.recyclerviewtests.activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.yassine.recyclerviewtests.adapter.RecyclerAdapter;
import com.example.yassine.recyclerviewtests.R;
import com.example.yassine.recyclerviewtests.json.VelibStation;
import com.example.yassine.recyclerviewtests.json.JSONInfos;
import com.example.yassine.recyclerviewtests.json.OpenDataParis;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private RecyclerView mRecyclerView;
    private RecyclerAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.title_activity_main);

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                                                     @Override
                                                     public void onRefresh() {
                                                         fillValues();
                                                         mSwipeRefreshLayout.setRefreshing(false);
                                                     }
                                                 });

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        if (!isNetworkAvailable())
            Toast.makeText(getApplicationContext(), "internet is not available", Toast.LENGTH_SHORT).show();

        mAdapter = new RecyclerAdapter();
        mRecyclerView.setAdapter(mAdapter);

        fillValues();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);

        SearchView searchView = (SearchView)menu.findItem(R.id.action_search).getActionView();
        searchView.setOnQueryTextListener(this);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_favorite:
                Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void fillValues()
    {
        mAdapter.clear();
        if (!isNetworkAvailable())
            Toast.makeText(getApplicationContext(), "internet is not available", Toast.LENGTH_SHORT).show();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(OpenDataParis.ENDPOINT)
                                        .addConverterFactory(GsonConverterFactory.create())
                                        .build();
        OpenDataParis velibService = retrofit.create(OpenDataParis.class);

        final Call<JSONInfos> listVelibStations = velibService.listVelibStations("stations-velib-disponibilites-en-temps-reel", 100);
        listVelibStations.enqueue(new Callback<JSONInfos>() {
            @Override
            public void onResponse(Call<JSONInfos> call, Response<JSONInfos> response) {
                if(response.isSuccessful()) {
                    ArrayList<VelibStation> res = response.body().records;
                    mAdapter.setmDataset(res);
                    mAdapter.setmDatasetCurrent(res);
                    mAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onFailure(Call<JSONInfos> call, Throwable t) {
                Log.e("Fail", t.getMessage());
            }
        });
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        filterSearch(query);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        filterSearch(newText);
        return true;
    }

    private void filterSearch(String newText) {
        if (newText.equals(""))
        {
            mAdapter.setmDatasetCurrent(mAdapter.mDataset);
            mAdapter.notifyDataSetChanged();
        }
        else
        {
            ArrayList<VelibStation> filtered = new ArrayList<>();
            for (int i = 0; i < mAdapter.mDataset.size(); i++)
                if (mAdapter.mDataset.get(i).field.name.toLowerCase().contains(newText.toLowerCase()))
                    filtered.add(mAdapter.mDataset.get(i));
            mAdapter.setmDatasetCurrent(filtered);
            mAdapter.notifyDataSetChanged();
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}