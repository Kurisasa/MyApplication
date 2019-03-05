package com.example.myapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by kurisani on 17/7/18.
 */

public class DateModel {
    @SerializedName("count")
    @Expose
    private String count;
    @SerializedName("date")
    @Expose
    private String date;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}