package com.example.yassine.recyclerviewtests;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<VelibStation> mDataset;

    static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView mTextView;
        private ImageView mImageView;

        public ViewHolder(View v) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.list_text_item);
            mTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "ITEM PRESSED", Toast.LENGTH_SHORT).show();                }
            });
            mImageView = (ImageView) v.findViewById(R.id.icon);
        }

    }

    public MyAdapter(List<VelibStation> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_line, parent, false);
        return new ViewHolder(v);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTextView.setText(mDataset.get(position).field.name);
        holder.mImageView.setImageResource(
                mDataset.get(position).field.status.equals("OPEN") ? R.drawable.open : R.drawable.closed);
    }
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public void setmDataset(List<VelibStation> mDataset) {
        this.mDataset = mDataset;
    }
}