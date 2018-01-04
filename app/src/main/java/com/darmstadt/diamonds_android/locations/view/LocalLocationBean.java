package com.darmstadt.diamonds_android.locations.view;

public class LocalLocationBean  {

    private double latitude;
    private double longitude;
    private String location;
    private String locationType;
    private String subTitle;
    private String title;
    private String wayDescription;

    public void setLatitude(final double latitude) {
        this.latitude = latitude;
    }

    public double getLatitude() {
        return latitude;
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

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(final String subTitle) {
        this.subTitle = subTitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getWayDescription() {
        return wayDescription;
    }

    public void setWayDescription(final String wayDescription) {
        this.wayDescription = wayDescription;
    }
}
