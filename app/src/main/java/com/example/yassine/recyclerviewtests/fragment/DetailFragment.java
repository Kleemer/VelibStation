package com.example.yassine.recyclerviewtests.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yassine.recyclerviewtests.R;

public class DetailFragment extends Fragment {

    public static DetailFragment newInstance(String sectionName) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putString("position", sectionName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_details, container, false);
        TextView textView = (TextView) rootView.findViewById(R.id.textViewFragment);
        textView.setText(getArguments().getString("position"));
        return rootView;
    }
}