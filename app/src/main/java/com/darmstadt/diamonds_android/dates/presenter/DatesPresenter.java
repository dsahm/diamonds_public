package com.darmstadt.diamonds_android.dates.presenter;

import com.darmstadt.diamonds_android.dates.contract.DatesContract;
import com.darmstadt.diamonds_android.dates.contract.DatesContract.IDatesDataSource;
import com.darmstadt.diamonds_android.dates.contract.DatesContract.IDatesView;
import com.darmstadt.diamonds_android.dates.contract.DatesContract.ILocalScheduleBean;
import com.darmstadt.diamonds_android.dates.contract.DatesContract.IOnDatesAvailableCallback;
import java.util.List;


public class DatesPresenter implements DatesContract.IDatesPresenter {

    private IDatesView mView;
    private IDatesDataSource mDataSource;

    public DatesPresenter(final IDatesView view, final IDatesDataSource dataSource) {
        mView = view;
        mDataSource = dataSource;
    }

    @Override
    public void loadSchedule(final int unitId) {
        mDataSource.loadSchedule(unitId, new IOnDatesAvailableCallback() {
            @Override
            public void onScheduleAvailable(final List<ILocalScheduleBean> schedules) {
                if (mView != null) {
                    mView.setData(schedules);
                }
            }

            @Override
            public void onError() {
                if (mView != null) {
                     mView.showError();
                }
            }
        });
    }

    @Override
    public void loadPractice() {
        mDataSource.loadPractice(new IOnDatesAvailableCallback() {
            @Override
            public void onScheduleAvailable(final List<ILocalScheduleBean> practice) {
                if (mView != null) {
                    mView.setData(practice);
                }
            }

            @Override
            public void onError() {
                if (mView != null) {
                    mView.showError();
                }
            }
        });
    }
}
