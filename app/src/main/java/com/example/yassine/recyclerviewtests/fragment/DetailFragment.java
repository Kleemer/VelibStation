package com.example.yassine.recyclerviewtests.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yassine.recyclerviewtests.R;
import com.example.yassine.recyclerviewtests.activity.DetailsActivity;
import com.example.yassine.recyclerviewtests.json.VelibStation;

public class DetailFragment extends Fragment {

    /*public static DetailFragment newInstance(String sectionName) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putString("position", sectionName);
        fragment.setArguments(args);
        return fragment;
    }*/

    public static DetailFragment newInstance(VelibStation velibstation) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putString("position", velibstation.field.name);
        args.putString("status", velibstation.field.status);
        args.putInt("bike_stands", velibstation.field.availableBikeStands);
        args.putInt("available_bike_stands", velibstation.field.availableBikes);
        args.putString("adress", velibstation.field.address);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_details, container, false);
        TextView textView = (TextView) rootView.findViewById(R.id.textViewFragment);
        textView.setText(getArguments().getString("position"));
        TextView textViewStatus = (TextView) rootView.findViewById(R.id.textViewFragmentStatus);
        textViewStatus.setText("Status: " + getArguments().getString("status"));
        TextView textViewBikeStands = (TextView) rootView.findViewById(R.id.textViewFragmentBikeStands);
        textViewBikeStands.setText("Bike Stands: " + getArguments().getInt("bike_stands"));
        TextView textViewBikeStandsAvailable = (TextView) rootView.findViewById(R.id.textViewFragmentBikeStandsAvailable);
        textViewBikeStandsAvailable.setText("Available Bike Stands: " + getArguments().getInt("available_bike_stands"));
        TextView textViewFragmentAdress = (TextView) rootView.findViewById(R.id.textViewFragmentAdress);
        textViewFragmentAdress.setText("Adress: " + getArguments().getString("adress"));

        textViewFragmentAdress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + getArguments().getString("adress"));
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                context.startActivity(mapIntent);

            }
        });
        return rootView;
    }
}