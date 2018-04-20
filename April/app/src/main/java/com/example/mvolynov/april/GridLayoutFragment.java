package com.example.mvolynov.april;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class GridLayoutFragment extends Fragment {
    private static final int COLUMN_COUNT_PORTRAIT = 3;
    private static final int COLUMN_COUNT_LANDSCAPE = 5;
    public static final String TAG = "grid";

    private Context mContext;
    LinearLayout mGridLayout;
    private RecyclerView mRecyclerView;

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Context activity = getActivity();
        if (activity == null) {
            return;
        }
        mContext = activity.getApplicationContext();

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_grid, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mGridLayout = view.findViewById(R.id.grid_layout_numbers);
        mRecyclerView = view.findViewById(R.id.recycler_view_numbers);

        String[] values = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"};

        int spanCount;
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            spanCount = COLUMN_COUNT_LANDSCAPE;
        } else {
            spanCount = COLUMN_COUNT_PORTRAIT;
        }
        mLayoutManager = new GridLayoutManager(mContext, spanCount);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new NumbersAdapter(mContext, values);
        mRecyclerView.setAdapter(mAdapter);
        view.findViewById(R.id.done).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity() instanceof ActivityOpener) {
                    ((ActivityOpener) getActivity()).openDataFragment(
                            ((NumbersAdapter) mAdapter).getSelectedPosArray());
                }
            }
        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putIntegerArrayList("position-array",
                ((NumbersAdapter) mAdapter).getSelectedPosArray());
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            ((NumbersAdapter) mAdapter).setSelectedPosArray(
                    savedInstanceState.getIntegerArrayList("position-array"));
            mAdapter.notifyDataSetChanged();
        }
    }
}
