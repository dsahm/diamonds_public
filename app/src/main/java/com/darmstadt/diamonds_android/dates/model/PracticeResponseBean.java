package com.darmstadt.diamonds_android.dates.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class PracticeResponseBean {

    @SerializedName("response")
    Response response;

    public Response getResponse() {
        return response;
    }

    public static class Response {

        @SerializedName("data")
        List<Data> dataList;

        public List<Data> getDataList() {
            return dataList;
        }

        public static class Data {

            @SerializedName("age")
            String age;
            @SerializedName("information")
            String information;
            @SerializedName("name")
            String name;
            @SerializedName("practice_practicetimes")
            List<PracticePracticeTimes> practicePraceTimes;

            public String getAge() {
                return age;
            }

            public String getInformation() {
                return information;
            }

            public String getName() {
                return name;
            }

            public List<PracticePracticeTimes> getPracticePraceTimes() {
                return practicePraceTimes;
            }

            public static class PracticePracticeTimes {

                @SerializedName("information")
                String information;
                @SerializedName("practicetime")
                PracticeTime practiceTime;

                public String getInformation() {
                    return information;
                }

                public PracticeTime getPracticeTime() {
                    return practiceTime;
                }

                public static class PracticeTime {

                    @SerializedName("comment")
                    String comment;
                    @SerializedName("day")
                    String day;
                    @SerializedName("endtime")
                    String endTime;
                    @SerializedName("location")
                    Location location;
                    @SerializedName("name")
                    String name;
                    @SerializedName("starttime")
                    String startTime;

                    public String getComment() {
                        return comment;
                    }

                    public String getDay() {
                        return day;
                    }

                    public String getEndTime() {
                        return endTime;
                    }

                    public Location getLocation() {
                        return location;
                    }

                    public String getName() {
                        return name;
                    }

                    public String getStartTime() {
                        return startTime;
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

                        public String getWayDescription() {
                            return wayDescription;
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
