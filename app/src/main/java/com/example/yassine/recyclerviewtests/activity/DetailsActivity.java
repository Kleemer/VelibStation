package com.example.yassine.recyclerviewtests.activity;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

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

        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Detail");

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_detail, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_group:
                Intent intent = new Intent(DetailsActivity.this, AboutActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_share:
                String textToShare = mDataset.get(firstPos).getTextShare();
                Intent sendIntent = new Intent(Intent.ACTION_SEND);
                sendIntent.setType("text/plain");
                sendIntent.putExtra(Intent.EXTRA_TEXT, textToShare);
                startActivity(Intent.createChooser(sendIntent, "share"));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
