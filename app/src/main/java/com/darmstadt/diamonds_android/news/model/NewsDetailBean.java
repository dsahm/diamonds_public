package com.darmstadt.diamonds_android.news.model;

import com.google.gson.annotations.SerializedName;

public class NewsDetailBean {

    @SerializedName("linkStory")
    LinkStory linkStory;
    @SerializedName("text")
    String text;

    public String getText() {
        return text;
    }

    public LinkStory getLinkStory() {
        return linkStory;
    }

    public static class LinkStory {

        @SerializedName("text")
        String text;

        public String getText() {
            return text;
        }

    }
}
