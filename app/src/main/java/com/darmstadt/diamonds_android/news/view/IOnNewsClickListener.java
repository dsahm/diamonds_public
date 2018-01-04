package com.darmstadt.diamonds_android.news.view;

import android.widget.ImageView;

public interface IOnNewsClickListener {

    void OnNewsClicked(final LocalNewsBean newsBean, final ImageView transitionImage, final int position);

}
