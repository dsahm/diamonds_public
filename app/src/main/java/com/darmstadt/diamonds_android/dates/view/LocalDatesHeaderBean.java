package com.darmstadt.diamonds_android.dates.view;

import com.brandongogetap.stickyheaders.exposed.StickyHeader;
import com.darmstadt.diamonds_android.dates.contract.DatesContract.ILocalScheduleBean;

public class LocalDatesHeaderBean implements ILocalScheduleBean, StickyHeader {

    private String title;
    private String information;
    private int dateType;

    @Override
    public void setDateType(final int dateType) {
        this.dateType = dateType;
    }

    @Override
    public int getDateType() {
        return dateType;
    }

    public String getTitle() {
        return title;
    }

    public String getInformation() {
        return information;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public void setInformation(final String information) {
        this.information = information;
    }
}
