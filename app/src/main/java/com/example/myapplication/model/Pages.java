package com.example.myapplication.model;

/**
 * Created by kurisani on 18/7/18.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pages {

    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("currentPage")
    @Expose
    private Integer currentPage;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

}