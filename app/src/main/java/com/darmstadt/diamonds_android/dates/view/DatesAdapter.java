package com.darmstadt.diamonds_android.dates.view;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.provider.CalendarContract;
import android.provider.CalendarContract.Events;
import android.support.v4.content.ContextCompat;
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
import com.brandongogetap.stickyheaders.exposed.StickyHeaderHandler;
import com.darmstadt.diamonds_android.R;
import com.darmstadt.diamonds_android.dates.contract.DatesContract.DateType;
import com.darmstadt.diamonds_android.dates.contract.DatesContract.IAdapterClickListener;
import com.darmstadt.diamonds_android.dates.contract.DatesContract.ILocalScheduleBean;
import com.darmstadt.diamonds_android.global.util.DateFormatter;
import com.darmstadt.diamonds_android.global.util.TextUtil;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import net.cachapa.expandablelayout.ExpandableLayout;
import net.cachapa.expandablelayout.ExpandableLayout.OnExpansionUpdateListener;

public class DatesAdapter extends RecyclerView.Adapter<ViewHolder> implements StickyHeaderHandler {

    private Context mContext;
    private List<ILocalScheduleBean> mDataSet;
    private Resources mResources;
    private IAdapterClickListener mExpandClickListener;
    private int mExpandAnimationDuration;

    DatesAdapter(final List<ILocalScheduleBean> dataSet, final Context context, final IAdapterClickListener expandClickListener) {
        mDataSet = dataSet;
        mContext = context;
        mExpandClickListener = expandClickListener;
        mResources = mContext.getResources();
        mExpandAnimationDuration = mResources.getInteger(R.integer.expand_animation_duration);
    }

    @Override
    public List<?> getAdapterData() {
        return mDataSet;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case DateType.SCHEDULE_HEADER:
            case DateType.PRACTISE_HEADER:
                return new DatesAdapter.ScheduleHeaderViewHolder(inflater.inflate(R.layout.item_schedule_header, parent, false));
            case DateType.SCHEDULE_ITEM:
                return new DatesAdapter.ScheduleViewHolder(inflater.inflate(R.layout.item_schedule, parent, false));
            case DateType.PRACTISE_ITEM:
                return new DatesAdapter.PracticeViewHolder(inflater.inflate(R.layout.item_practice, parent, false));
            default:
                return new DatesAdapter.ScheduleViewHolder(inflater.inflate(R.layout.item_schedule, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        final ILocalScheduleBean bean = mDataSet.get(position);
        switch (getItemViewType(position)) {
            case DateType.SCHEDULE_HEADER:
            case DateType.PRACTISE_HEADER:
                if (bean instanceof LocalDatesHeaderBean) {
                    final ScheduleHeaderViewHolder vh = (ScheduleHeaderViewHolder) holder;
                    final LocalDatesHeaderBean localDatesHeaderBean = (LocalDatesHeaderBean) bean;
                    if (!TextUtils.isEmpty(localDatesHeaderBean.getTitle())) {
                        vh.title.setText(localDatesHeaderBean.getTitle());
                        vh.title.setVisibility(View.VISIBLE);
                    } else {
                        vh.title.setVisibility(View.GONE);
                    }
                    if (!TextUtils.isEmpty(localDatesHeaderBean.getInformation())) {
                        vh.subTitle.setText(localDatesHeaderBean.getInformation());
                        vh.subTitle.setVisibility(View.VISIBLE);
                    } else {
                        vh.subTitle.setVisibility(View.GONE);
                    }
                }
                break;
            case DateType.PRACTISE_ITEM:
                if (bean instanceof LocalPracticeBean) {
                    final PracticeViewHolder vh = (PracticeViewHolder) holder;
                    final LocalPracticeBean localPracticeBean = (LocalPracticeBean) bean;
                    vh.date.setText(localPracticeBean.getDate());
                    if (!TextUtils.isEmpty(localPracticeBean.getLocation())) {
                        vh.address.setText(localPracticeBean.getLocation());
                        vh.address.setVisibility(View.VISIBLE);
                    } else {
                        vh.address.setVisibility(View.GONE);
                    }
                    if (!TextUtils.isEmpty(localPracticeBean.getComment())) {
                        vh.comment.setText(localPracticeBean.getComment());
                        vh.comment.setVisibility(View.VISIBLE);
                    } else {
                        vh.comment.setVisibility(View.GONE);
                    }
                    vh.expandableLayout.setInterpolator(new OvershootInterpolator());
                    vh.expandableLayout.setOnExpansionUpdateListener(new OnExpansionUpdateListener() {
                        @Override
                        public void onExpansionUpdate(final float expansionFraction, final int state) {
                            mExpandClickListener.onItemExpanded(vh.getAdapterPosition());
                        }
                    });
                    vh.itemView.setOnClickListener(v ->{
                        if (vh.expandableLayout.isExpanded()) {
                            vh.expandableLayout.collapse();
                            vh.expandView.animate().rotation(0).setDuration(mExpandAnimationDuration).start();
                        } else {
                            vh.expandableLayout.expand();
                            vh.expandView.animate().rotation(-180).setDuration(mExpandAnimationDuration).start();
                        }
                    });

                    if (!TextUtils.isEmpty(localPracticeBean.getWayDescription())) {
                        vh.wayDescriptionAction.setVisibility(View.VISIBLE);
                        vh.wayDescriptionAction.setOnClickListener(v -> wayDescriptionAction(localPracticeBean.getLocation(), localPracticeBean.getWayDescription()));
                    } else {
                        vh.wayDescriptionAction.setVisibility(View.GONE);
                    }

                    if (localPracticeBean.getLatitude() != 0 && localPracticeBean.getLongitude() != 0) {
                        vh.navigationAction.setVisibility(View.VISIBLE);
                        vh.navigationAction.setOnClickListener(v -> navigationAction(localPracticeBean.getLatitude(), localPracticeBean.getLongitude()));
                    } else {
                        vh.navigationAction.setVisibility(View.GONE);
                    }

                    break;
                }
            case DateType.SCHEDULE_ITEM:
                if (bean instanceof LocalScheduleBean) {
                    final ScheduleViewHolder vh = (ScheduleViewHolder) holder;
                    final LocalScheduleBean localScheduleBean = (LocalScheduleBean) bean;
                    vh.date.setText(localScheduleBean.getDate());
                    vh.homeTeam.setText(localScheduleBean.getHomeTeamName());
                    vh.guestTeam.setText(localScheduleBean.getGuestTeamName());
                    vh.location.setText(localScheduleBean.getLocation());
                    vh.score.setText(localScheduleBean.getScore());
                    vh.weather.setText(localScheduleBean.getWeatherInfo());
                    final int homeTeamDrawableId = mResources.getIdentifier(localScheduleBean.getHomeTeamCity()
                            .toLowerCase(), "drawable", mContext.getPackageName());
                    vh.homeTeamLogo.setImageDrawable(ContextCompat.getDrawable(mContext, homeTeamDrawableId));
                    final int guestTeamDrawableId = mResources.getIdentifier(localScheduleBean.getGuestTeamCity()
                            .toLowerCase(), "drawable", mContext.getPackageName());
                    vh.guestTeamLogo.setImageDrawable(ContextCompat.getDrawable(mContext, guestTeamDrawableId));

                    // TODO: Optimize all of this with dagger

                    if (!TextUtils.isEmpty(localScheduleBean.getWayDescription())) {
                        vh.wayDescriptionAction.setVisibility(View.VISIBLE);
                        vh.wayDescriptionAction.setOnClickListener(v -> wayDescriptionAction(localScheduleBean.getLocation(), localScheduleBean.getWayDescription()));
                    } else {
                        vh.wayDescriptionAction.setVisibility(View.GONE);
                    }

                    if (!TextUtils.isEmpty(localScheduleBean.getTicketUrl())) {
                        vh.ticketAction.setVisibility(View.VISIBLE);
                        vh.ticketAction.setOnClickListener(v -> {
                            final Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(localScheduleBean.getTicketUrl()));
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            mContext.startActivity(intent);
                        });
                    } else {
                        vh.ticketAction.setVisibility(View.GONE);
                    }

                    if (!TextUtils.isEmpty(localScheduleBean.getDate())) {
                        vh.calendarAction.setVisibility(View.VISIBLE);
                        vh.calendarAction.setOnClickListener(v -> {
                            calendarAction(localScheduleBean.getDate(), localScheduleBean.getCalendarTitle(), localScheduleBean.getLocation());
                        });
                    } else {
                        vh.calendarAction.setVisibility(View.GONE);
                    }

                    if (localScheduleBean.getLatitude() != 0 && localScheduleBean.getLongitude() != 0) {
                        vh.navigationAction.setVisibility(View.VISIBLE);
                        vh.navigationAction.setOnClickListener(v -> {
                            navigationAction(localScheduleBean.getLatitude(), localScheduleBean.getLongitude());
                        });
                    } else {
                        vh.navigationAction.setVisibility(View.GONE);
                    }

                    break;
                }
        }
    }

    @Override
    public int getItemViewType(final int position) {
        return mDataSet.get(position).getDateType();
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    void setItems(final List<ILocalScheduleBean> dataSet) {
        mDataSet = dataSet;
        notifyDataSetChanged();
    }

    private void wayDescriptionAction(final String title, final String description) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setCancelable(true);
        builder.setMessage(description)
                .setTitle(TextUtil.fromHtml("<font color='#004781'>" + title + "</font>"));

        builder.setNegativeButton(R.string.finished, null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    // TODO: Create helper class for actions

    private void navigationAction(final double latitude, final double longitude) {
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(
                "https://www.google.com/maps/dir/?api=1&destination="
                        + String.format("%s,%s", latitude, longitude)));
        mContext.startActivity(intent);
    }

    private void calendarAction(final String beginTime, final String calendarTitle, final String location) {
        final Date date = DateFormatter.getDateFromString(beginTime, DateFormatter.TO_FORMAT);
        final Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        instance.add(Calendar.HOUR, 2);
        final Date endDate = instance.getTime();
        Intent intent = new Intent(Intent.ACTION_INSERT)
                .setData(Events.CONTENT_URI)
                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, date.getTime())
                .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endDate.getTime())
                .putExtra(Events.TITLE, calendarTitle)
                .putExtra(Events.DESCRIPTION, mContext.getString(R.string.calendar_description))
                .putExtra(Events.EVENT_LOCATION, location)
                .putExtra(Events.AVAILABILITY, Events.AVAILABILITY_BUSY);
        mContext.startActivity(intent);
    }

    class ScheduleViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.calendar)
        ImageView calendarAction;
        @BindView(R.id.date)
        TextView date;
        @BindView(R.id.guest_team)
        TextView guestTeam;
        @BindView(R.id.imageViewGuest)
        ImageView guestTeamLogo;
        @BindView(R.id.home_team)
        TextView homeTeam;
        @BindView(R.id.imageViewHome)
        ImageView homeTeamLogo;
        @BindView(R.id.location)
        TextView location;
        @BindView(R.id.map)
        ImageView navigationAction;
        @BindView(R.id.score)
        TextView score;
        @BindView(R.id.ticket)
        ImageView ticketAction;
        @BindView(R.id.way_description)
        ImageView wayDescriptionAction;
        @BindView(R.id.weather)
        TextView weather;

        ScheduleViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }

    class ScheduleHeaderViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.subtitle)
        TextView subTitle;
        @BindView(R.id.title)
        TextView title;

        ScheduleHeaderViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }

    class PracticeViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.address)
        TextView address;
        @BindView(R.id.comment)
        TextView comment;
        @BindView(R.id.date)
        TextView date;
        @BindView(R.id.expand_button)
        ImageView expandView;
        @BindView(R.id.expandable_layout)
        ExpandableLayout expandableLayout;
        @BindView(R.id.linlay_action_item)
        LinearLayout linlayActionItem;
        @BindView(R.id.linlay_main_item)
        LinearLayout linlayMainItem;
        @BindView(R.id.way_description)
        ImageView wayDescriptionAction;
        @BindView(R.id.map)
        ImageView navigationAction;

        PracticeViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }

    }
}
