package com.example.mvolynov.april;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Mikhail Volynov
 * @project April
 * @date 19.04.18
 */
public class SeeFragment extends Fragment {
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_see, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            TextView tv = (TextView) view.findViewById(R.id.text);

            ArrayList<Integer> selectedPosList = args.getIntegerArrayList("position-array");
            if (tv != null && selectedPosList != null) {
                Collections.sort(selectedPosList);
                StringBuilder builder = new StringBuilder();
                builder.append("Result: ");
                for (Integer elem : selectedPosList) {
                    builder.append(elem + 1).append(" ");
                }
                tv.setText(builder.toString());
            }
        }
    }
}
