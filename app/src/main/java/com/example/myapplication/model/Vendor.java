package com.example.myapplication.model;

import java.util.List;

public class Vendor {

    private String VendorGuid;
    private String VendorNumber;
    private String VendorName;
    private String VendorDescription;
    private String Address;
    private String PhoneNo;
    private String Email;
    private String RegistrationDate;
    private String IsActive;
    private List<VendorItem> vendorItems;
    private String updateUser;
    private String updateDts;
    private String updateType;

    public Vendor(){

    }

    public List<VendorItem> getVendorItems() {
        return vendorItems;
    }

    public void setVendorItems(List<VendorItem> vendorItems) {
        this.vendorItems = vendorItems;
    }

    public String getVendorGuid() {
        return VendorGuid;
    }

    public void setVendorGuid(String vendorGuid) {
        VendorGuid = vendorGuid;
    }

    public String getVendorNumber() {
        return VendorNumber;
    }

    public void setVendorNumber(String vendorNumber) {
        VendorNumber = vendorNumber;
    }

    public String getVendorName() {
        return VendorName;
    }

    public void setVendorName(String vendorName) {
        VendorName = vendorName;
    }

    public String getVendorDescription() {
        return VendorDescription;
    }

    public void setVendorDescription(String vendorDescription) {
        VendorDescription = vendorDescription;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPhoneNo() {
        return PhoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        PhoneNo = phoneNo;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getRegistrationDate() {
        return RegistrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        RegistrationDate = registrationDate;
    }

    public String getIsActive() {
        return IsActive;
    }

    public void setIsActive(String isActive) {
        IsActive = isActive;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getUpdateDts() {
        return updateDts;
    }

    public void setUpdateDts(String updateDts) {
        this.updateDts = updateDts;
    }

    public String getUpdateType() {
        return updateType;
    }

    public void setUpdateType(String updateType) {
        this.updateType = updateType;
    }
}
