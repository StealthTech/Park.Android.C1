package com.example.mvolynov.april;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ActivityOpener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridLayoutFragment mGridLayoutFragment = new GridLayoutFragment();

        int frameLayoutId = R.id.frame_layout_container_general;

        getSupportFragmentManager().beginTransaction().replace(frameLayoutId, mGridLayoutFragment).addToBackStack("grid").commit();
    }

    @Override
    public void openDataFragment(ArrayList<Integer> lst) {
        Fragment fragment = new SeeFragment();
        Bundle args = new Bundle();
        args.putIntegerArrayList("position-array", lst);
        fragment.setArguments(args);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_container_general, fragment).addToBackStack("fragment-opened").commit();

    }
}
