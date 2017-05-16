package com.example.yassine.recyclerviewtests.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yassine.recyclerviewtests.R;
import com.example.yassine.recyclerviewtests.activity.DetailsActivity;
import com.example.yassine.recyclerviewtests.activity.MainActivity;
import com.example.yassine.recyclerviewtests.json.VelibStation;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    public ArrayList<VelibStation> mDataset = new ArrayList<>();
    public ArrayList<VelibStation> mDatasetCurrent = new ArrayList<>();

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView mTextView;
        private ImageView mImageView;
        private int position;

        public ViewHolder(View v) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.list_text_item);
            mImageView = (ImageView) v.findViewById(R.id.icon);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent i = new Intent(context, DetailsActivity.class);
                    i.putExtra("stations", mDataset);
                    i.putExtra("firstPos", position);
                    context.startActivity(i);
                }
            });
        }
    }

    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_line, parent, false);
        return new ViewHolder(v);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTextView.setText(mDatasetCurrent.get(position).field.name);
        holder.mImageView.setImageResource(
                mDatasetCurrent.get(position).field.status.equals("OPEN") ? R.drawable.open : R.drawable.closed);
        holder.position = position;
    }
    @Override
    public int getItemCount() {
        return mDatasetCurrent.size();
    }

    public void setmDataset(ArrayList<VelibStation> mDataset) {
        this.mDataset = mDataset;
    }
    public void setmDatasetCurrent(ArrayList<VelibStation> mDataset) {
        this.mDatasetCurrent = mDataset;
    }
}