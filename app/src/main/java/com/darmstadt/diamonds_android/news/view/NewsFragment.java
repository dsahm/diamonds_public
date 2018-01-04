package com.darmstadt.diamonds_android.news.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.darmstadt.diamonds_android.DiamondsApplication;
import com.darmstadt.diamonds_android.R;
import com.darmstadt.diamonds_android.global.util.UiUtil;
import com.darmstadt.diamonds_android.global.view.BaseFragment;
import com.darmstadt.diamonds_android.news.contract.NewsContract;
import com.darmstadt.diamonds_android.news.contract.NewsContract.INewsPresenter;
import com.darmstadt.diamonds_android.news.di.NewsMVPModule;
import java.util.List;
import javax.inject.Inject;

public class NewsFragment extends BaseFragment implements NewsContract.INewsView, IOnNewsClickListener {

    public static final String TAG = NewsFragment.class.getName();
    @Inject
    INewsPresenter mPresenter;
    @BindView(R.id.progress)
    ProgressBar mProgressBar;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    private LinearLayoutManager mLayoutManager;
    private NewsAdapter mNewsAdapter;
    private int mOffset = -20;
    private int mOffsetIncrement = 20;
    private Unbinder mUnbinder;

    public static NewsFragment newInstance() {
        return new NewsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View view = inflater.inflate(R.layout.fragment_news, container, false);
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
        init();
        if (mNewsAdapter == null) {
            mPresenter.loadNews(mOffset += mOffsetIncrement);
        } else {
            mProgressBar.setVisibility(View.GONE);
            mRecyclerView.setAdapter(mNewsAdapter);
        }
    }

    @Override
    protected void initDagger() {
        ((DiamondsApplication) getActivity().getApplication()).getApplicationComponent()
                .getNewsMVPComponent(new NewsMVPModule(this))
                .inject(this);
    }

    @Override
    protected void setActionBar() {
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
    }

    @Override
    protected String getActionBarTitle() {
        return getString(R.string.news);
    }

    @Override
    public void OnNewsClicked(final LocalNewsBean newsBean, final ImageView transitionImageView, final int position) {
        final Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
        intent.putExtra(NewsDetailActivity.EXTRA_ITEM, newsBean);
        startActivity(intent);
    }

    @Override
    public void showError() {
        if (uiMemberNullCheck()) {
            UiUtil.showToast(getContext(), R.string.data_loading_error, Toast.LENGTH_LONG);
            mProgressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void setNewsData(List<LocalNewsBean> newsList) {
        if (uiMemberNullCheck()) {
            if (mNewsAdapter == null) {
                mNewsAdapter = new NewsAdapter(newsList, getContext(), this);
                mRecyclerView.setAdapter(mNewsAdapter);
            } else {
                mNewsAdapter.addItems(newsList);
            }
            mProgressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean uiMemberNullCheck() {
        return mProgressBar != null && mRecyclerView != null && mToolbar != null && getContext() != null;
    }

    private void init() {
        mRecyclerView.setHasFixedSize(false);
        mLayoutManager = new LinearLayoutManager(getContext());
        mLayoutManager.setItemPrefetchEnabled(true);
        mLayoutManager.setInitialPrefetchItemCount(5);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener(mLayoutManager) {
            @Override
            public void onLoadMore(final int page, final int totalItemsCount, final RecyclerView view) {
                if (mPresenter != null) {
                    mPresenter.loadNews(mOffset += mOffsetIncrement);
                }
            }
        });
    }

}
