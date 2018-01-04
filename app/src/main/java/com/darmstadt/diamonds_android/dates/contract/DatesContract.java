package com.darmstadt.diamonds_android.dates.contract;

import android.support.annotation.IntDef;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

public class DatesContract {

    public interface IDatesView {

        void setData(final List<ILocalScheduleBean> schedules);
        void showError();
        boolean uiMemberNullCheck();

    }

    public interface IDatesPresenter {

        void loadSchedule(final int unitId);
        void loadPractice();

    }

    public interface IDatesDataSource {

        void loadSchedule(final int unitId, final IOnDatesAvailableCallback callback);
        void loadPractice(final IOnDatesAvailableCallback callback);

    }

    public interface IOnDatesAvailableCallback {

        void onScheduleAvailable(final List<ILocalScheduleBean> events);
        void onError();
    }

    public interface ILocalScheduleBean {

        void setDateType(final int dateType);
        int getDateType();
    }

    public interface IAdapterClickListener {
        void onItemExpanded(final int position);
    }

    @IntDef({DateType.EVENT, DateType.PRACTISE_HEADER, DateType.PRACTISE_ITEM, DateType.SCHEDULE_HEADER, DateType.SCHEDULE_ITEM})
    @Retention(RetentionPolicy.SOURCE)
    public @interface DateType {
        int EVENT = 1;
        int PRACTISE_HEADER = 2;
        int PRACTISE_ITEM = 3;
        int SCHEDULE_HEADER = 4;
        int SCHEDULE_ITEM = 5;
    }

    @IntDef({UnitType.MALE, UnitType.U19, UnitType.CHEERLEADER})
    @Retention(RetentionPolicy.SOURCE)
    public @interface UnitType {
        int MALE = 1;
        int U19 = 2;
        int CHEERLEADER = 5;
    }

}
