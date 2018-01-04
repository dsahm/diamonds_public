package com.darmstadt.diamonds_android.news.model;

import com.google.gson.annotations.SerializedName;

public class RemoteNewsBean {

    @SerializedName("id")
    private int id;
    @SerializedName("date")
    private String date;
    @SerializedName("headline")
    private String headline;
    @SerializedName("imageFile")
    private String imageFile;
    @SerializedName("imageHeaderFile")
    private String imageHeaderFile;
    @SerializedName("text")
    private String text;
    @SerializedName("linkExtern")
    private String linkExtern;
    @SerializedName("category")
    private String category;

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getHeadline() {
        return headline;
    }

    public String getImageFile() {
        return imageFile;
    }

    public String getImageHeaderFile() {
        return imageHeaderFile;
    }

    public String getText() {
        return text;
    }

    public String getLinkExtern() {
        return linkExtern;
    }

    public String getCategory() {
        return category;
    }

    //    @SerializedName("linkExternTitle")
//    String linkExternTitle;
//    @SerializedName("linkMisc")
//    String linkMisc;
//    @SerializedName("linkMiscTitle")
//    String linkMiscTitle;
//    @SerializedName("linkStoryId")
//    int linkStoryId;
//    @SerializedName("linkStoryTitle")
//    String linkStoryTitle;
//    @SerializedName("linkYouthAGameId")
//    int linkYouthAGameId;
//    @SerializedName("linkGameId")
//    int linkGameId;
//

}
