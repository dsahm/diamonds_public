package com.darmstadt.diamonds_android.dates.view;

import com.darmstadt.diamonds_android.dates.contract.DatesContract.ILocalScheduleBean;

public class LocalPracticeBean implements ILocalScheduleBean {

    private String comment;
    private String date;
    private int dateType;
    private double latitude;
    private String location;
    private double longitude;
    private String wayDescription;

    public String getComment() {
        return comment;
    }    @Override
    public void setDateType(final int dateType) {
        this.dateType = dateType;
    }

    public void setComment(final String comment) {
        this.comment = comment;
    }    @Override
    public int getDateType() {
        return dateType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(final String date) {
        this.date = date;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(final double latitude) {
        this.latitude = latitude;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(final String location) {
        this.location = location;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(final double longitude) {
        this.longitude = longitude;
    }

    public String getWayDescription() {
        return wayDescription;
    }

    public void setWayDescription(final String wayDescription) {
        this.wayDescription = wayDescription;
    }




}
