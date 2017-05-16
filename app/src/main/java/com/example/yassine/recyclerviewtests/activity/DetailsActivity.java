package com.example.yassine.recyclerviewtests.activity;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.yassine.recyclerviewtests.R;
import com.example.yassine.recyclerviewtests.adapter.ViewPagerAdapter;
import com.example.yassine.recyclerviewtests.json.VelibStation;

import java.util.ArrayList;
import java.util.List;

public class DetailsActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private ViewPagerAdapter mPagerAdapter;
    public ArrayList<VelibStation> mDataset = new ArrayList<>();
    int firstPos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        final Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra("stations"))
                mDataset = getIntent().getExtras().getParcelableArrayList("stations");
            if (intent.hasExtra("firstPos"))
                firstPos = getIntent().getExtras().getInt("firstPos");
        }

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), mDataset, firstPos);
        mViewPager.setAdapter(mPagerAdapter);
        Log.d("pos", "" + firstPos);
        mViewPager.setCurrentItem(firstPos);
    }
}
