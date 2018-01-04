package com.darmstadt.diamonds_android.dates.view;

import com.darmstadt.diamonds_android.dates.contract.DatesContract.ILocalScheduleBean;

public class LocalScheduleBean implements ILocalScheduleBean {

    private String date;
    private int dateType;
    private String guestTeamCity;
    private String guestTeamName;
    private String homeTeamCity;
    private String homeTeamName;
    private String location;
    private String score;
    private String weatherInfo;
    private String wayDescription;
    private String ticketUrl;
    private String calendarTitle;
    private double latitude;
    private double longitude;

    @Override
    public void setDateType(final int dateType) {
        this.dateType = dateType;
    }
    @Override
    public int getDateType() {
        return dateType;
    }

    public String getCalendarTitle() {
        return calendarTitle;
    }

    public void setCalendarTitle(final String calendarTitle) {
        this.calendarTitle = calendarTitle;
    }

    public String getDate() {
        return date;
    }

    public String getWayDescription() {
        return wayDescription;
    }

    public void setWayDescription(final String wayDescription) {
        this.wayDescription = wayDescription;
    }

    public String getTicketUrl() {
        return ticketUrl;
    }

    public void setTicketUrl(final String ticketUrl) {
        this.ticketUrl = ticketUrl;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(final double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(final double longitude) {
        this.longitude = longitude;
    }

    public void setDate(final String date) {
        this.date = date;
    }

    public String getGuestTeamCity() {
        return guestTeamCity;
    }

    public void setGuestTeamCity(final String guestTeamCity) {
        this.guestTeamCity = guestTeamCity;
    }

    public String getGuestTeamName() {
        return guestTeamName;
    }

    public void setGuestTeamName(final String guestTeamName) {
        this.guestTeamName = guestTeamName;
    }

    public String getHomeTeamCity() {
        return homeTeamCity;
    }

    public void setHomeTeamCity(final String homeTeamCity) {
        this.homeTeamCity = homeTeamCity;
    }

    public String getHomeTeamName() {
        return homeTeamName;
    }

    public void setHomeTeamName(final String homeTeamName) {
        this.homeTeamName = homeTeamName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(final String location) {
        this.location = location;
    }

    public String getScore() {
        return score;
    }

    public void setScore(final String score) {
        this.score = score;
    }

    public String getWeatherInfo() {
        return weatherInfo;
    }

    public void setWeatherInfo(final String weatherInfo) {
        this.weatherInfo = weatherInfo;
    }

}
