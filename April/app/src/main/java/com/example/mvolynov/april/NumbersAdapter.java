package com.example.mvolynov.april;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;


public class NumbersAdapter extends RecyclerView.Adapter<NumbersAdapter.ViewHolder> {
    private static final int COLOR_SELECTED = Color.rgb(240, 240, 240);
    private static final int COLOR_DEFAULT = Color.TRANSPARENT;

    private String[] mDataSet;
    private Context mContext;
    private ArrayList<Integer> selectedPosArray = new ArrayList<>();

    public ArrayList<Integer> getSelectedPosArray() {
        return selectedPosArray;
    }

    public NumbersAdapter(Context context, String[] DataSet) {
        mDataSet = DataSet;
        mContext = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public LinearLayout mLinearLayout;

        public ViewHolder(View v) {
            super(v);
            mTextView = v.findViewById(R.id.item_text_view);
            mLinearLayout = v.findViewById(R.id.item_linear_layout);
        }
    }

    @NonNull
    @Override
    public NumbersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.mTextView.setText(mDataSet[position]);

        if (selectedPosArray.contains(holder.getAdapterPosition())) {
            holder.mTextView.setBackgroundColor(COLOR_SELECTED);
        } else {
            holder.mTextView.setBackgroundColor(COLOR_DEFAULT);
        }

        holder.mLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedPosArray.contains(holder.getAdapterPosition())) {
                    holder.mTextView.setBackgroundColor(COLOR_DEFAULT);
                    selectedPosArray.remove((Integer) holder.getAdapterPosition());
                } else {
                    holder.mTextView.setBackgroundColor(COLOR_SELECTED);
                    selectedPosArray.add(holder.getAdapterPosition());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataSet.length;
    }
}
