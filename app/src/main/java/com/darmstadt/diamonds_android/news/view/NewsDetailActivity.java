package com.darmstadt.diamonds_android.news.view;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.util.Linkify;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import com.darmstadt.diamonds_android.DiamondsApplication;
import com.darmstadt.diamonds_android.R;
import com.darmstadt.diamonds_android.global.util.UiUtil;
import com.darmstadt.diamonds_android.news.contract.NewsDetailContract.INewsDetailPresenter;
import com.darmstadt.diamonds_android.news.contract.NewsDetailContract.INewsDetailView;
import com.darmstadt.diamonds_android.news.di.NewsDetailMVPModule;
import com.elmargomez.typer.Font;
import com.elmargomez.typer.Typer;
import javax.inject.Inject;

public class NewsDetailActivity extends AppCompatActivity implements INewsDetailView {

    public static final String TAG = NewsDetailActivity.class.getName();
    private static final String LOG_TAG = NewsDetailActivity.class.getSimpleName();

    public static final String EXTRA_ITEM = "extra_item";
    private LocalNewsBean mLocalNewsBean;
    @Inject
    INewsDetailPresenter mPresenter;
    @BindView(R.id.app_bar)
    AppBarLayout mAppBarLayout;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    @BindView(R.id.image_detail)
    ImageView mDetailImage;
    @BindView(R.id.progress)
    ProgressBar mProgressBar;
    @BindView(R.id.text_content)
    TextView mTextContent;
    @BindView(R.id.title)
    TextView mTextTitle;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDagger();
        overridePendingTransition(R.anim.enter_from_right, R.anim.stay);
        setContentView(R.layout.activity_news_detail);
        ButterKnife.bind(this);
        setActionBar();
        setActionBarTitle();
        mCollapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));
        mCollapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.white));
        mCollapsingToolbarLayout.setTitle(getString(R.string.news));
        final Typeface font = Typer.set(getApplicationContext()).getFont(Font.ROBOTO_MEDIUM);
        mCollapsingToolbarLayout.setCollapsedTitleTypeface(font);
        final Intent intent = getIntent();
        mLocalNewsBean = intent.getParcelableExtra(EXTRA_ITEM);
        mPresenter.loadNewsDetail(mLocalNewsBean.getId());
        Glide.with(this).load(mLocalNewsBean.getImageHeaderFile()).into(mDetailImage);
    }

    protected void initDagger() {
        ((DiamondsApplication) this.getApplication()).getApplicationComponent()
                .getNewsDetailMVPComponent(new NewsDetailMVPModule(this))
                .inject(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.stay, R.anim.exit_to_right);
    }

    @Override
    public boolean onSupportNavigateUp() {
        supportFinishAfterTransition();
        overridePendingTransition(R.anim.stay, R.anim.exit_to_right);
        return true;
    }

    @Override
    public void setDetailMessage(final String message) {
        if (uiMemberNullCheck()) {
            mProgressBar.setVisibility(View.GONE);
            mTextContent.setText(message);
            mTextTitle.setText(mLocalNewsBean.getHeadline());
            Linkify.addLinks(mTextContent, Linkify.WEB_URLS | Linkify.EMAIL_ADDRESSES);
        }
    }

    @Override
    public void showError() {
        UiUtil.showToast(this, R.string.data_loading_error, Toast.LENGTH_LONG);
        if (uiMemberNullCheck()) {
            mProgressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean uiMemberNullCheck() {
        return mProgressBar != null && mTextContent != null;
    }

    private void setActionBar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setActionBarTitle() {
        getSupportActionBar().setTitle("");
    }
}
