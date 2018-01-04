package com.darmstadt.diamonds_android.global.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public abstract class BaseFragment extends Fragment {

    @Override
    public void onCreate(final Bundle savedInstanceStace) {
        super.onCreate(savedInstanceStace);
        initDagger();
    }

    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setActionBar();
        try {
            ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(getActionBarTitle());
        } catch (Exception ignore) {}
    }

    protected abstract void initDagger();
    protected abstract void setActionBar();
    protected abstract String getActionBarTitle();
}