package com.darmstadt.diamonds_android.dates.model;

import com.darmstadt.diamonds_android.dates.contract.DatesContract;
import com.darmstadt.diamonds_android.dates.contract.DatesContract.DateType;
import com.darmstadt.diamonds_android.dates.contract.DatesContract.ILocalScheduleBean;
import com.darmstadt.diamonds_android.dates.contract.DatesContract.IOnDatesAvailableCallback;
import com.darmstadt.diamonds_android.dates.model.PracticeResponseBean.Response.Data;
import com.darmstadt.diamonds_android.dates.model.PracticeResponseBean.Response.Data.PracticePracticeTimes;
import com.darmstadt.diamonds_android.dates.model.PracticeResponseBean.Response.Data.PracticePracticeTimes.PracticeTime;
import com.darmstadt.diamonds_android.dates.model.PracticeResponseBean.Response.Data.PracticePracticeTimes.PracticeTime.Location;
import com.darmstadt.diamonds_android.dates.model.ScheduleResponseBean.Response.ScheduleBean.Schedule;
import com.darmstadt.diamonds_android.dates.view.LocalDatesHeaderBean;
import com.darmstadt.diamonds_android.dates.view.LocalPracticeBean;
import com.darmstadt.diamonds_android.dates.view.LocalScheduleBean;
import com.darmstadt.diamonds_android.global.endpoint.DampsEndpoint;
import com.darmstadt.diamonds_android.global.util.DateFormatter;
import com.orhanobut.logger.Logger;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.List;


public class DatesDataSource implements DatesContract.IDatesDataSource {

    private static final String LOG_TAG = DatesDataSource.class.getSimpleName();

    private DampsEndpoint mEndpoint;

    public DatesDataSource(final DampsEndpoint endpoint) {
        mEndpoint = endpoint;
    }

    @Override
    public void loadSchedule(final int unitType, final IOnDatesAvailableCallback callback) {
        mEndpoint.loadSchedule(unitType)
                .subscribeOn(Schedulers.io())
                .map(this::getLocalScheduleBean)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<ILocalScheduleBean>>() {
                    @Override
                    public void onSubscribe(final Disposable d) {

                    }

                    @Override
                    public void onNext(final List<ILocalScheduleBean> scheduleBeans) {
                        callback.onScheduleAvailable(scheduleBeans);
                    }

                    @Override
                    public void onError(final Throwable e) {
                        Logger.log(Logger.ERROR, LOG_TAG, "Error loading schedules", e);
                        callback.onError();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void loadPractice(final IOnDatesAvailableCallback callback) {
        mEndpoint.loadPractice()
                .subscribeOn(Schedulers.io())
                .map(this::getLocalPracticeBean)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<ILocalScheduleBean>>() {
                    @Override
                    public void onSubscribe(final Disposable d) {

                    }

                    @Override
                    public void onNext(final List<ILocalScheduleBean> scheduleBeans) {
                        callback.onScheduleAvailable(scheduleBeans);
                    }

                    @Override
                    public void onError(final Throwable e) {
                        Logger.log(Logger.ERROR, LOG_TAG, "Error loading practice", e);
                        callback.onError();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private List<ILocalScheduleBean> getLocalScheduleBean(final ScheduleResponseBean response) {
        final List<ILocalScheduleBean> localScheduleBeans = new ArrayList<>();
        final List<ScheduleResponseBean.Response.ScheduleBean> scheduleBeans = response.getResponse()
                .getScheduleBeans();
        for (int i = 0; i < scheduleBeans.size(); i++) {
            final List<Schedule> schedules = scheduleBeans.get(i).getSchedules();
            for (int j = 0; j < schedules.size(); j++) {
                final Schedule schedule = schedules.get(j);
                final LocalScheduleBean localScheduleBean = new LocalScheduleBean();
                final String dateString = DateFormatter.formatDate(schedule.getKickOffTime());
                localScheduleBean.setHomeTeamCity(schedule.getHometeam().getCity());
                localScheduleBean.setGuestTeamCity(schedule.getGuestTeam().getCity());
                localScheduleBean.setDate(dateString);
                localScheduleBean.setLocation(String.format("@%s", schedule.getHometeam().getLocation().getLocationName()));
                localScheduleBean.setDateType(DateType.SCHEDULE_ITEM);
                localScheduleBean.setHomeTeamName(String.format("%s %s", schedule.getHometeam().getCity(), schedule.getHometeam().getName()));
                localScheduleBean.setGuestTeamName(String.format("%s %s", schedule.getGuestTeam().getCity(), schedule.getGuestTeam().getName()));
                localScheduleBean.setScore(String.format("%s : %s", String.valueOf(schedule.getHomeSum()), String.valueOf(schedule.getGuestSum())));
                localScheduleBean.setLatitude(schedule.getHometeam().getLocation().getLatitude());
                localScheduleBean.setLongitude(schedule.getHometeam().getLocation().getLongitude());
                localScheduleBean.setWayDescription(schedule.getHometeam().getLocation().getWayDescription());
                localScheduleBean.setTicketUrl(schedule.getTickets());
                localScheduleBean.setCalendarTitle(String.format("%s - %s", schedule.getHometeam().getFullName(), schedule.getGuestTeam().getFullName()));
                localScheduleBeans.add(localScheduleBean);
            }
        }
        return localScheduleBeans;
    }

    private List<ILocalScheduleBean> getLocalPracticeBean(final PracticeResponseBean response) {
        final List<ILocalScheduleBean> localScheduleBeans = new ArrayList<>();
        final List<PracticeResponseBean.Response.Data> data = response.getResponse().getDataList();
        for (int i = 0; i < data.size(); i++) {
            // Header level
            final Data dataBean = data.get(i);
            final LocalDatesHeaderBean headerBean = new LocalDatesHeaderBean();
            headerBean.setDateType(DateType.PRACTISE_HEADER);
            headerBean.setTitle(String.format("%s %s", dataBean.getName(), dataBean.getAge()));
            headerBean.setInformation(dataBean.getInformation());
            localScheduleBeans.add(headerBean);
            final List<PracticePracticeTimes> practiceTimes = data.get(i).getPracticePraceTimes();
            for (int j = 0; j < practiceTimes.size(); j++) {
                final PracticePracticeTimes practicePracticeTimes = practiceTimes.get(j);
                final LocalPracticeBean localPracticeBean = new LocalPracticeBean();
                // TODO: Use resource
                final PracticeTime practiceTime = practicePracticeTimes.getPracticeTime();
                final String date = String.format("%s %s bis %s", practiceTime.getDay(), practiceTime.getStartTime(), practiceTime.getEndTime());
                localPracticeBean.setDate(date);
                localPracticeBean.setComment(practiceTime.getComment());
                final Location location = practiceTime.getLocation();
                localPracticeBean.setLocation(String.format("%s, %s", location.getLocationName(), location.getStreet()));
                localPracticeBean.setWayDescription(location.getWayDescription());
                localPracticeBean.setLatitude(location.getLatitude());
                localPracticeBean.setLongitude(location.getLongitude());
                localPracticeBean.setDateType(DateType.PRACTISE_ITEM);
                localScheduleBeans.add(localPracticeBean);
            }
        }
        return localScheduleBeans;
    }
}
