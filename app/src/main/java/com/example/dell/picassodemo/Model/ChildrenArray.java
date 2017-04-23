package com.example.dell.picassodemo.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Dell on 4/15/2017.
 */
public class ChildrenArray {


    @SerializedName("children")
    private List<Children> childrenList;

    public List<Children> getChildrenList() {
        return childrenList;
    }
}
