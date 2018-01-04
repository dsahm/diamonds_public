package com.darmstadt.diamonds_android.dates.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class ScheduleResponseBean {

    @SerializedName("response")
    Response response;

    public Response getResponse() {
        return response;
    }

    public static class Response {

        @SerializedName("data")
        List<ScheduleBean> scheduleBeans;

        public List<ScheduleBean> getScheduleBeans() {
            return scheduleBeans;
        }

        public static class ScheduleBean {

            @SerializedName("german")
            String germanName;
            @SerializedName("name")
            String name;
            @SerializedName("schedules")
            List<Schedule> schedules;

            public String getGermanName() {
                return germanName;
            }

            public String getName() {
                return name;
            }

            public List<Schedule> getSchedules() {
                return schedules;
            }


            public static class Schedule {

                @SerializedName("event")
                Event event;
                @SerializedName("gametype_id")
                int gameTypeId;
                @SerializedName("guest")
                int guest;
                @SerializedName("guest_sum")
                int guestSum;
                @SerializedName("guestteam")
                GuestTeam guestTeam;
                @SerializedName("home")
                int home;
                @SerializedName("home_sum")
                int homeSum;
                @SerializedName("hometeam")
                HomeTeam hometeam;
                @SerializedName("kickoff")
                String kickOffTime;
                @SerializedName("tickets")
                String tickets;

                public Event getEvent() {
                    return event;
                }

                public int getGameTypeId() {
                    return gameTypeId;
                }

                public int getGuest() {
                    return guest;
                }

                public int getGuestSum() {
                    return guestSum;
                }

                public GuestTeam getGuestTeam() {
                    return guestTeam;
                }

                public int getHome() {
                    return home;
                }

                public int getHomeSum() {
                    return homeSum;
                }

                public HomeTeam getHometeam() {
                    return hometeam;
                }

                public String getKickOffTime() {
                    return kickOffTime;
                }

                public String getTickets() {
                    return tickets;
                }

                public static class Event {

                    @SerializedName("facebookevent")
                    String facebookEvent;
                    @SerializedName("start")
                    String startTime;
                    @SerializedName("tickets")
                    String tickets;
                    @SerializedName("title")
                    String title;

                    public String getFacebookEvent() {
                        return facebookEvent;
                    }

                    public String getStartTime() {
                        return startTime;
                    }

                    public String getTickets() {
                        return tickets;
                    }

                    public String getTitle() {
                        return title;
                    }
                }

                public static class GuestTeam {

                    @SerializedName("city")
                    String city;
                    @SerializedName("full_name")
                    String fullName;
                    @SerializedName("full_name_with_unit")
                    String fullNameWithUnit;
                    @SerializedName("name")
                    String name;

                    public String getCity() {
                        return city;
                    }

                    public String getFullName() {
                        return fullName;
                    }

                    public String getFullNameWithUnit() {
                        return fullNameWithUnit;
                    }

                    public String getName() {
                        return name;
                    }
                }

                public static class HomeTeam {

                    @SerializedName("city")
                    String city;
                    @SerializedName("full_name")
                    String fullName;
                    @SerializedName("full_name_with_unit")
                    String fullNameWithUnit;
                    @SerializedName("location")
                    Location location;
                    @SerializedName("name")
                    String name;

                    public String getCity() {
                        return city;
                    }

                    public String getFullName() {
                        return fullName;
                    }

                    public String getFullNameWithUnit() {
                        return fullNameWithUnit;
                    }

                    public Location getLocation() {
                        return location;
                    }

                    public String getName() {
                        return name;
                    }

                    public static class Location {

                        @SerializedName("address")
                        String address;
                        @SerializedName("city")
                        String city;
                        @SerializedName("latitude")
                        double latitude;
                        @SerializedName("name")
                        String locationName;
                        @SerializedName("longitude")
                        double longitude;
                        @SerializedName("street")
                        String street;
                        @SerializedName("number")
                        String streetNumber;
                        @SerializedName("public")
                        String wayDescription;
                        @SerializedName("zip")
                        int zip;

                        public String getWayDescription() {
                            return wayDescription;
                        }

                        public String getAddress() {
                            return address;
                        }

                        public String getCity() {
                            return city;
                        }

                        public double getLatitude() {
                            return latitude;
                        }

                        public String getLocationName() {
                            return locationName;
                        }

                        public double getLongitude() {
                            return longitude;
                        }

                        public String getStreet() {
                            return street;
                        }

                        public String getStreetNumber() {
                            return streetNumber;
                        }

                        public int getZip() {
                            return zip;
                        }
                    }

                }
            }
        }
    }
}
