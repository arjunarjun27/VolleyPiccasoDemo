package com.example.dell.picassodemo.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Dell on 4/9/2017.
 */
public class Post {

    @SerializedName("title")
    String title;

    @SerializedName("permalink")
    String permalink;

    @SerializedName("thumbnail")
    String thumbnail;

    public Post(String title, String permalink, String thumbnail) {
        this.title = title;
        this.permalink = permalink;
        this.thumbnail = thumbnail;
    }

    public String getPermalink() {
        return permalink;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getTitle() {
        return title;
    }
}