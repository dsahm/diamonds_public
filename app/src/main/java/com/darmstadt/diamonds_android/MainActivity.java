package com.darmstadt.diamonds_android;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.crashlytics.android.Crashlytics;
import com.darmstadt.diamonds_android.dates.view.DatesFragment;
import com.darmstadt.diamonds_android.global.util.FragUtil;
import com.darmstadt.diamonds_android.global.util.UiUtil;
import com.darmstadt.diamonds_android.locations.view.LocationFragment;
import com.darmstadt.diamonds_android.news.view.NewsFragment;
import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity {

    private static final String STATE_SELECTED_ITEM = "State";
    private int mSelectedItem;

    @BindView(R.id.navigation_view)
    BottomNavigationView mBottomNavigationView;

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        UiUtil.removeShiftMode(mBottomNavigationView);

        if (savedInstanceState == null) {
            mSelectedItem = R.id.bottom_news;
        } else {
            mSelectedItem = savedInstanceState.getInt(STATE_SELECTED_ITEM);
        }

        setUpBottomView(mSelectedItem);
    }

    @Override
    protected void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE_SELECTED_ITEM, mSelectedItem);
    }

    @Override
    public boolean onSupportNavigateUp() {
        FragUtil.popBackStack(getSupportFragmentManager());
        return true;
    }

    private void loadFragmentForItem(final MenuItem item) {
        Fragment fragment = null;
        String tag = "";
        mSelectedItem = item.getItemId();
        switch (mSelectedItem) {
            case R.id.bottom_news:
                fragment = NewsFragment.newInstance();
                tag = NewsFragment.TAG;
                break;
            case R.id.bottom_events:
                fragment = DatesFragment.newInstance();
                tag = DatesFragment.TAG;
                break;
            case R.id.bottom_addresses:
                fragment = LocationFragment.newInstance();
                tag = LocationFragment.TAG;
                break;
        }
        FragUtil.replaceFragmentNoBackStack(getSupportFragmentManager(), fragment, tag);
    }

    private void setUpBottomView(final int itemId) {
        mBottomNavigationView.setOnNavigationItemSelectedListener((item) -> {
            loadFragmentForItem(item);
            return true;
        });
        mBottomNavigationView.setOnNavigationItemReselectedListener(this::loadFragmentForItem);
        mBottomNavigationView.setSelectedItemId(itemId);
    }

}
