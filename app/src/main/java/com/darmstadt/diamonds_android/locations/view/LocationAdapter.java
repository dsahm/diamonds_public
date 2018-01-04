package com.darmstadt.diamonds_android.locations.view;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.darmstadt.diamonds_android.R;
import com.darmstadt.diamonds_android.global.util.TextUtil;
import com.darmstadt.diamonds_android.locations.contract.LocationContract.IAdapterClickListener;
import java.util.List;
import net.cachapa.expandablelayout.ExpandableLayout;
import net.cachapa.expandablelayout.ExpandableLayout.OnExpansionUpdateListener;

public class LocationAdapter extends RecyclerView.Adapter<ViewHolder> {

    private Context mContext;
    private List<LocalLocationBean> mDataSet;
    private int mExpandAnimationDuration;
    private IAdapterClickListener mExpandClickListener;
    private Resources mResources;

    LocationAdapter(final List<LocalLocationBean> dataSet, final Context context, final IAdapterClickListener expandClickListener) {
        mDataSet = dataSet;
        mContext = context;
        mExpandClickListener = expandClickListener;
        mResources = mContext.getResources();
        mExpandAnimationDuration = mResources.getInteger(R.integer.expand_animation_duration);
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new LocationAdapter.LocationViewHolder(inflater.inflate(R.layout.item_location, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final LocalLocationBean bean = mDataSet.get(position);
        final LocationViewHolder vh = (LocationViewHolder) holder;

        final LocalLocationBean localLocationBean = (LocalLocationBean) bean;
        vh.title.setText(localLocationBean.getTitle());
        if (!TextUtils.isEmpty(localLocationBean.getLocation())) {
            vh.address.setText(localLocationBean.getLocation());
            vh.address.setVisibility(View.VISIBLE);
        } else {
            vh.address.setVisibility(View.GONE);
        }
        if (!TextUtils.isEmpty(localLocationBean.getSubTitle())) {
            vh.subTitle.setText(localLocationBean.getSubTitle());
            vh.subTitle.setVisibility(View.VISIBLE);
        } else {
            vh.subTitle.setVisibility(View.GONE);
        }

        vh.expandableLayout.setInterpolator(new OvershootInterpolator());
        vh.expandableLayout.setOnExpansionUpdateListener(new OnExpansionUpdateListener() {
            @Override
            public void onExpansionUpdate(final float expansionFraction, final int state) {
                mExpandClickListener.onItemExpanded(vh.getAdapterPosition());
            }
        });

        vh.itemView.setOnClickListener(v -> {
            if (vh.expandableLayout.isExpanded()) {
                vh.expandableLayout.collapse();
                vh.expandView.animate().rotation(0).setDuration(mExpandAnimationDuration).start();
            } else {
                vh.expandableLayout.expand();
                vh.expandView.animate().rotation(-180).setDuration(mExpandAnimationDuration).start();
            }
        });

        if (!TextUtils.isEmpty(localLocationBean.getWayDescription())) {
            vh.wayDescriptionAction.setVisibility(View.VISIBLE);
            vh.wayDescriptionAction.setOnClickListener(v -> wayDescriptionAction(localLocationBean.getLocation(), localLocationBean
                    .getWayDescription()));
        } else {
            vh.wayDescriptionAction.setVisibility(View.GONE);
        }

        if (localLocationBean.getLatitude() != 0 && localLocationBean.getLongitude() != 0) {
            vh.navigationAction.setVisibility(View.VISIBLE);
            vh.navigationAction.setOnClickListener(v -> navigationAction(localLocationBean.getLatitude(), localLocationBean
                    .getLongitude()));
        } else {
            vh.navigationAction.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    // TODO: Create helper class for actions
    private void navigationAction(final double latitude, final double longitude) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(
                "https://www.google.com/maps/dir/?api=1&destination=" + String.format("%s,%s", latitude, longitude)));
        mContext.startActivity(intent);
    }

    private void wayDescriptionAction(final String title, final String description) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setCancelable(true);
        builder.setMessage(description).setTitle(TextUtil.fromHtml("<font color='#004781'>" + title + "</font>"));

        builder.setNegativeButton(R.string.finished, null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    class LocationViewHolder extends ViewHolder {

        @BindView(R.id.address)
        TextView address;
        @BindView(R.id.expand_button)
        ImageView expandView;
        @BindView(R.id.expandable_layout)
        ExpandableLayout expandableLayout;
        @BindView(R.id.linlay_action_item)
        LinearLayout linlayActionItem;
        @BindView(R.id.linlay_main_item)
        LinearLayout linlayMainItem;
        @BindView(R.id.map)
        ImageView navigationAction;
        @BindView(R.id.sub_title)
        TextView subTitle;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.way_description)
        ImageView wayDescriptionAction;

        LocationViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }
}
