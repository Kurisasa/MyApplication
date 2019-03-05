package com.example.myapplication.model;

public class Device {

    private String DeviceGuid;
    private String DeviceSerialNumber;
    private String DeviceMake;
    private String DeviceModel;
    private String DeviceIsActive;
    private String VendorGuid;
    private String DevicePurchaseDate;
    private String updateUser;
    private String updateDts;
    private String updateType;

    public Device(){

    }

    public String getDeviceGuid() {
        return DeviceGuid;
    }

    public void setDeviceGuid(String deviceGuid) {
        DeviceGuid = deviceGuid;
    }

    public String getDeviceSerialNumber() {
        return DeviceSerialNumber;
    }

    public void setDeviceSerialNumber(String deviceSerialNumber) {
        DeviceSerialNumber = deviceSerialNumber;
    }

    public String getDeviceMake() {
        return DeviceMake;
    }

    public void setDeviceMake(String deviceMake) {
        DeviceMake = deviceMake;
    }

    public String getDeviceModel() {
        return DeviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        DeviceModel = deviceModel;
    }

    public String getDeviceIsActive() {
        return DeviceIsActive;
    }

    public void setDeviceIsActive(String deviceIsActive) {
        DeviceIsActive = deviceIsActive;
    }

    public String getVendorGuid() {
        return VendorGuid;
    }

    public void setVendorGuid(String vendorGuid) {
        VendorGuid = vendorGuid;
    }

    public String getDevicePurchaseDate() {
        return DevicePurchaseDate;
    }

    public void setDevicePurchaseDate(String devicePurchaseDate) {
        DevicePurchaseDate = devicePurchaseDate;
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
