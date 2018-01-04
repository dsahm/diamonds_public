package com.darmstadt.diamonds_android.locations.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class LocationResponseBean {


    @SerializedName("data")
    List<Data> dataList;

    public List<Data> getDataList() {
        return dataList;
    }

    public static class Data {

        @SerializedName("typeid")
        int typeId;
        @SerializedName("typename")
        String typeName;
        @SerializedName("name")
        String name;
        @SerializedName("street")
        String street;
        @SerializedName("number")
        String streetNumber;
        @SerializedName("zip")
        String zip;
        @SerializedName("city")
        String city;
        @SerializedName("latitude")
        String latitude;
        @SerializedName("longitude")
        String longitude;
        @SerializedName("public")
        String wayDescription;

        public int getTypeId() {
            return typeId;
        }

        public String getTypeName() {
            return typeName;
        }

        public String getName() {
            return name;
        }

        public String getStreet() {
            return street;
        }

        public String getStreetNumber() {
            return streetNumber;
        }

        public String getZip() {
            return zip;
        }

        public String getCity() {
            return city;
        }

        public String getLatitude() {
            return latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public String getWayDescription() {
            return wayDescription;
        }
    }
}
