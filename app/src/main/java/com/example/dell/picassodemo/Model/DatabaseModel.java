package com.example.dell.picassodemo.Model;

/**
 * Created by Dell on 4/23/2017.
 */
public class DatabaseModel {
    public DatabaseModel(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String name;
    public String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }




}
