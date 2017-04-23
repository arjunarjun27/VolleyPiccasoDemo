package com.example.dell.picassodemo.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Dell on 4/15/2017.
 */
public class Children {

    @SerializedName("data")
    private Post post;

    public Post getPost() {
        return post;
    }

}
