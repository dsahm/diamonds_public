package com.darmstadt.diamonds_android.news.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.darmstadt.diamonds_android.R;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private List<LocalNewsBean> mDataSet;
    private Context mContext;
    private IOnNewsClickListener mNewsClickListener;

    NewsAdapter(final List<LocalNewsBean> dataSet, final Context context, final IOnNewsClickListener newsClickListener) {
        mDataSet = dataSet;
        mContext = context;
        mNewsClickListener = newsClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View v = inflater.inflate(R.layout.item_news, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final LocalNewsBean bean = mDataSet.get(position);
        holder.title.setText(bean.getHeadline());
        Glide.with(mContext).load(bean.getImageHeaderFile()).into(holder.image);
        holder.itemView.setOnClickListener(v -> {
            mNewsClickListener.OnNewsClicked(bean, holder.image, position);
        });
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    void addItems(final List<LocalNewsBean> dataSet) {
        final int currentSize = getItemCount();
        mDataSet.addAll(dataSet);
        notifyItemRangeInserted(currentSize, mDataSet.size() - 1);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

//        @BindView(R.id.title)
        TextView title;
//        @BindView(R.id.image)
        ImageView image;

        ViewHolder(View v) {
            super(v);
//            ButterKnife.bind(v);
            title = v.findViewById(R.id.title);
            image = v.findViewById(R.id.image);
        }
    }

}
