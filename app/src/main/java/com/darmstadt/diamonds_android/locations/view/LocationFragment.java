package com.darmstadt.diamonds_android.locations.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.darmstadt.diamonds_android.DiamondsApplication;
import com.darmstadt.diamonds_android.R;
import com.darmstadt.diamonds_android.global.util.UiUtil;
import com.darmstadt.diamonds_android.global.view.BaseFragment;
import com.darmstadt.diamonds_android.locations.contract.LocationContract;
import com.darmstadt.diamonds_android.locations.contract.LocationContract.ILocationsPresenter;
import com.darmstadt.diamonds_android.locations.contract.LocationContract.LocationType;
import com.darmstadt.diamonds_android.locations.di.LocationMVPModule;
import java.util.List;
import javax.inject.Inject;


public class LocationFragment extends BaseFragment implements LocationContract.ILocationsView, LocationContract.IAdapterClickListener {

    public static final String TAG = LocationFragment.class.getName();
    @Inject
    ILocationsPresenter mPresenter;
    @BindView(R.id.progress)
    ProgressBar mProgress;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    private LocationAdapter mAdapter;
    private Unbinder mUnbinder;

    public static LocationFragment newInstance() {
        return new LocationFragment();
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View view = inflater.inflate(R.layout.fragment_locations, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Parameter does not yet have functionality
        mPresenter.loadLocation(LocationType.HOME);
    }

    @Override
    protected void initDagger() {
        ((DiamondsApplication) getActivity().getApplication()).getApplicationComponent()
                .getLocationMVPComponent(new LocationMVPModule(this))
                .inject(this);
    }

    @Override
    protected void setActionBar() {
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
    }

    @Override
    protected String getActionBarTitle() {
        return getString(R.string.addresses);
    }

    @Override
    public void onItemExpanded(final int position) {
        if (mRecyclerView != null) {
            mRecyclerView.smoothScrollToPosition(position);
        }
    }

    @Override
    public void setData(final List<LocalLocationBean> schedules) {
        if (uiMemberNullCheck()) {
            mProgress.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);
            mAdapter = new LocationAdapter(schedules, getContext(), this);
            final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            mRecyclerView.setLayoutManager(layoutManager);
            mRecyclerView.setAdapter(mAdapter);
            mRecyclerView.addItemDecoration(new DividerItemDecoration(mRecyclerView.getContext(), layoutManager.getOrientation()));
        }
    }

    @Override
    public void showError() {
        if (uiMemberNullCheck()) {
            mProgress.setVisibility(View.GONE);
            UiUtil.showToast(getContext(), R.string.data_loading_error, Toast.LENGTH_LONG);
        }
    }

    @Override
    public boolean uiMemberNullCheck() {
        return mRecyclerView != null && mProgress != null;
    }

}
