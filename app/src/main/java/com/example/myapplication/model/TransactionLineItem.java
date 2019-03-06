package com.example.myapplication.model;

import java.util.ArrayList;

public class TransactionLineItem  {

    //ArrayList<TransactionLineItem> = new ArrayList<>
    private String LineItemGuid;
    private String TransactionGuid;
    private String Barcode;
    private double Price;
    private String Name;
    private double Vat;
    private int Quantity;
    private double LineTotal;

    public TransactionLineItem(){

    }

    public String getLineItemGuid() {
        return LineItemGuid;
    }

    public void setLineItemGuid(String lineItemGuid) {
        LineItemGuid = lineItemGuid;
    }

    public String getTransactionGuid() {
        return TransactionGuid;
    }

    public void setTransactionGuid(String transactionGuid) {
        TransactionGuid = transactionGuid;
    }

    public String getBarcode() {
        return Barcode;
    }

    public void setBarcode(String barcode) {
        Barcode = barcode;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public double getVat() {
        return Vat;
    }

    public void setVat(double vat) {
        Vat = vat;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public double getLineTotal() {
        return LineTotal;
    }

    public void setLineTotal(double lineTotal) {
        LineTotal = lineTotal;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
