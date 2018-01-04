package com.darmstadt.diamonds_android.dates.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.OnTabSelectedListener;
import android.support.design.widget.TabLayout.Tab;
import android.support.v7.app.AppCompatActivity;
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
import com.brandongogetap.stickyheaders.StickyLayoutManager;
import com.darmstadt.diamonds_android.DiamondsApplication;
import com.darmstadt.diamonds_android.R;
import com.darmstadt.diamonds_android.dates.contract.DatesContract;
import com.darmstadt.diamonds_android.dates.contract.DatesContract.IDatesPresenter;
import com.darmstadt.diamonds_android.dates.contract.DatesContract.ILocalScheduleBean;
import com.darmstadt.diamonds_android.dates.contract.DatesContract.UnitType;
import com.darmstadt.diamonds_android.dates.di.DatesMVPModule;
import com.darmstadt.diamonds_android.global.util.UiUtil;
import com.darmstadt.diamonds_android.global.view.BaseFragment;
import java.util.List;
import javax.inject.Inject;


public class DatesFragment extends BaseFragment implements DatesContract.IDatesView, DatesContract.IAdapterClickListener {

    public static final String TAG = DatesFragment.class.getName();
    @Inject
    IDatesPresenter mPresenter;
    @BindView(R.id.progress)
    ProgressBar mProgress;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.tabs)
    TabLayout mTabLayout;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    private DatesAdapter mAdapter;
    private Unbinder mUnbinder;

    public static DatesFragment newInstance() {
        return new DatesFragment();
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View view = inflater.inflate(R.layout.fragment_dates, container, false);
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
        mTabLayout.addTab(mTabLayout.newTab().setText(getString(R.string.male)));
        mTabLayout.addTab(mTabLayout.newTab().setText(getString(R.string.u19)));
        mTabLayout.addTab(mTabLayout.newTab().setText(getString(R.string.training)));
        mPresenter.loadSchedule(UnitType.MALE);
        mTabLayout.addOnTabSelectedListener(new OnTabSelectedListener() {
            @Override
            public void onTabSelected(final Tab tab) {
                onTabClick(tab);
            }

            @Override
            public void onTabUnselected(final Tab tab) {

            }

            @Override
            public void onTabReselected(final Tab tab) {
                onTabClick(tab);
            }
        });

    }

    @Override
    protected void initDagger() {
        ((DiamondsApplication) getActivity().getApplication()).getApplicationComponent()
                .getDatesMVPComponent(new DatesMVPModule(this))
                .inject(this);
    }

    @Override
    protected void setActionBar() {
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
    }

    @Override
    protected String getActionBarTitle() {
        return getString(R.string.dates);
    }

    @Override
    public void onItemExpanded(final int position) {
        if (mRecyclerView != null) {
            mRecyclerView.smoothScrollToPosition(position);
        }
    }

    @Override
    public void setData(final List<ILocalScheduleBean> schedules) {
        if (uiMemberNullCheck()) {
            mProgress.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);
            mAdapter = new DatesAdapter(schedules, getContext(), this);
            mRecyclerView.setLayoutManager(new StickyLayoutManager(getContext(), mAdapter));
            mRecyclerView.setAdapter(mAdapter);
        }
    }

    @Override
    public void showError() {
        if (uiMemberNullCheck()) {
            UiUtil.showToast(getContext(), R.string.data_loading_error, Toast.LENGTH_LONG);
        }
    }

    @Override
    public boolean uiMemberNullCheck() {
        return mRecyclerView != null && mProgress != null;
    }

    private void onTabClick(final Tab tab) {
        mRecyclerView.setAdapter(null);
        mProgress.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.GONE);
        if (tab.getText().equals(getString(R.string.male))) {
            mPresenter.loadSchedule(UnitType.MALE);
        } else if (tab.getText().equals(getString(R.string.u19))) {
            mPresenter.loadSchedule(UnitType.U19);
        } else {
            mPresenter.loadPractice();
        }
    }
}
