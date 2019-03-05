package com.example.myapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by kurisani on 18/7/18.
 */

public class InvoiceModel implements Serializable {
    @SerializedName("customerName")
    @Expose
    private String customerName;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("totalTax")
    @Expose
    private double totalTax;
    @SerializedName("totalAmount")
    @Expose
    private double totalAmount;
    @SerializedName("items")
    @Expose
    private List<TransactionLineItem> items = null;
    @SerializedName("invoiceNumber")
    @Expose
    private String invoiceNumber;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getTotalTax() {
        return totalTax;
    }

    public void setTotalTax(double totalTax) {
        this.totalTax = totalTax;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<TransactionLineItem> getItems() {
        return items;
    }

    public void setItems(List<TransactionLineItem> items) {
        this.items = items;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

}
