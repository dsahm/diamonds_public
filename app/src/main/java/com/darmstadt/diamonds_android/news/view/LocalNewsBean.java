package com.darmstadt.diamonds_android.news.view;

import android.os.Parcel;

public class LocalNewsBean implements android.os.Parcelable {

    private int id;
//    private String date;
    private String headline;
//    private String imageFile;
    private String imageHeaderFile;
//    private String linkExtern;
//    private String category;

    public int getId() {
        return id;
    }
//
//    public String getDate() {
//        return date;
//    }

    public String getHeadline() {
        return headline;
    }

//    public String getImageFile() {
//        return imageFile;
//    }

    public String getImageHeaderFile() {
        return imageHeaderFile;
    }

//    public String getLinkExtern() {
//        return linkExtern;
//    }

//    public String getCategory() {
//        return category;
//    }

//    public void setId(int id) {
//        this.id = id;
//    }

//    public void setDate(String date) {
//        this.date = date;
//    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

//    public void setImageFile(String imageFile) {
//        this.imageFile = imageFile;
//    }

    public void setImageHeaderFile(String imageHeaderFile) {
        this.imageHeaderFile = imageHeaderFile;
    }

    public void setId(final int id) {
        this.id = id;
    }

    //    public void setLinkExtern(String linkExtern) {
//        this.linkExtern = linkExtern;
//    }

//    public void setCategory(String category) {
//        this.category = category;
//    }

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.headline);
        dest.writeString(this.imageHeaderFile);
        dest.writeInt(this.id);
    }

    public LocalNewsBean() {
    }

    protected LocalNewsBean(Parcel in) {
        this.headline = in.readString();
        this.imageHeaderFile = in.readString();
        this.id = in.readInt();
    }

    public static final Creator<LocalNewsBean> CREATOR = new Creator<LocalNewsBean>() {
        @Override
        public LocalNewsBean createFromParcel(Parcel source) {
            return new LocalNewsBean(source);
        }

        @Override
        public LocalNewsBean[] newArray(int size) {
            return new LocalNewsBean[size];
        }
    };
}
