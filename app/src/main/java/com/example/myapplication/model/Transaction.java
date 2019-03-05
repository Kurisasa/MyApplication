package com.example.myapplication.model;

import java.util.List;

public class Transaction {

    private String TransactionGuid;
    private String VendorGuid;
    private String DeviceGuid;
    private String PeriodGuid;
    private String TransactionTypeId;
    private String DocumentNumber;
    private double TaxableTotal;
    private double NonTaxableTotal;
    private double VAT;
    private double Total;
    private double Tender;
    private double Change;
    private String TransactionDTS;
    private String Location;
    private List<TransactionLineItem> lineItemList;

    public Transaction(){

    }

    public List<TransactionLineItem> getLineItemList() {
        return lineItemList;
    }

    public void setLineItemList(List<TransactionLineItem> lineItemList) {
        this.lineItemList = lineItemList;
    }

    public String getTransactionGuid() {
        return TransactionGuid;
    }

    public void setTransactionGuid(String transactionGuid) {
        TransactionGuid = transactionGuid;
    }

    public String getVendorGuid() {
        return VendorGuid;
    }

    public void setVendorGuid(String vendorGuid) {
        VendorGuid = vendorGuid;
    }

    public String getDeviceGuid() {
        return DeviceGuid;
    }

    public void setDeviceGuid(String deviceGuid) {
        DeviceGuid = deviceGuid;
    }

    public String getPeriodGuid() {
        return PeriodGuid;
    }

    public void setPeriodGuid(String periodGuid) {
        PeriodGuid = periodGuid;
    }

    public String getTransactionTypeId() {
        return TransactionTypeId;
    }

    public void setTransactionTypeId(String transactionTypeId) {
        TransactionTypeId = transactionTypeId;
    }

    public String getDocumentNumber() {
        return DocumentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        DocumentNumber = documentNumber;
    }

    public double getTaxableTotal() {
        return TaxableTotal;
    }

    public void setTaxableTotal(double taxableTotal) {
        TaxableTotal = taxableTotal;
    }

    public double getNonTaxableTotal() {
        return NonTaxableTotal;
    }

    public void setNonTaxableTotal(double nonTaxableTotal) {
        NonTaxableTotal = nonTaxableTotal;
    }

    public double getTotal() {
        return Total;
    }

    public void setTotal(double total) {
        Total = total;
    }

    public double getVAT() {
        return VAT;
    }

    public void setVAT(double VAT) {
        this.VAT = VAT;
    }

    public String getTransactionDTS() {
        return TransactionDTS;
    }

    public void setTransactionDTS(String transactionDTS) {
        TransactionDTS = transactionDTS;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public double getTender() {
        return Tender;
    }

    public void setTender(double tender) {
        Tender = tender;
    }

    public double getChange() {
        return Change;
    }

    public void setChange(double change) {
        Change = change;
    }
}
