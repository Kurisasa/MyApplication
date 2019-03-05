package com.example.myapplication.model;

import java.util.ArrayList;

public class VendorItem {

    private String VendorItemGuid;
    private String VendorGuid;
    private String barcode;
    private String ItemGuid;
    private double TxPercentage;
    private double price;
    private String itemName;

    public VendorItem(){

    }
    private ArrayList<VendorItem> venderItem = new ArrayList<VendorItem>();

    public ArrayList<VendorItem> getVenderItem() {
        return venderItem;
    }

    public void setVenderItem(ArrayList<VendorItem> venderItem) {
        this.venderItem = venderItem;
    }

    public String getVendorItemGuid() {
        return VendorItemGuid;
    }

    public void setVendorItemGuid(String vendorItemGuid) {
        VendorItemGuid = vendorItemGuid;
    }

    public String getVendorGuid() {
        return VendorGuid;
    }

    public void setVendorGuid(String vendorGuid) {
        VendorGuid = vendorGuid;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getItemGuid() {
        return ItemGuid;
    }

    public void setItemGuid(String itemGuid) {
        ItemGuid = itemGuid;
    }

    public double getTxPercentage() {
        return TxPercentage;
    }

    public void setTxPercentage(double txPercentage) {
        TxPercentage = txPercentage;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

}
