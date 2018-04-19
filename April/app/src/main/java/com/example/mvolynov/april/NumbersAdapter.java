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
    private String[] mDataSet;
    private Context mContext;
    private OnClickListener listener;
    private ArrayList<Integer> selectedPosArray = new ArrayList<>();
    private ArrayList<String> selectedTextArray = new ArrayList<>();

    public NumbersAdapter(Context context, String[] DataSet, OnClickListener listener, ArrayList<Integer> selectedPosArray, ArrayList<String> selectedTextArray) {
        mDataSet = DataSet;
        mContext = context;
        this.listener = listener;
        this.selectedPosArray = selectedPosArray;
        this.selectedTextArray = selectedTextArray;
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
            holder.mTextView.setBackgroundColor(Color.rgb(240, 240, 240));
        }

        holder.mLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedPosArray.contains(holder.getAdapterPosition())) {
                    holder.mTextView.setBackgroundColor(Color.TRANSPARENT);
                } else {
                    holder.mTextView.setBackgroundColor(Color.rgb(240, 240, 240));
                }
                listener.onNumberClick(holder.getAdapterPosition(), mDataSet[holder.getAdapterPosition()]);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataSet.length;
    }
}
