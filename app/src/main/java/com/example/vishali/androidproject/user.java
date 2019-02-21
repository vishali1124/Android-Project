package com.example.vishali.androidproject;

import android.widget.EditText;
import android.widget.TextView;

public class user {

    private String userId;
    private String UserName;
    private String PhoneNo;
    private String HouseNo;
    private String StreetAddress;
    private String Town;
    private String Country;

    public user(){

    }

    public user(String userId, String userName, String phoneNo, String houseNo, String streetAddress, String town, String country) {
        this.userId = userId;
        UserName = userName;
        PhoneNo = phoneNo;
        HouseNo = houseNo;
        StreetAddress = streetAddress;
        Town = town;
        Country = country;
    }

    public String getUserId() {

        return userId;
    }

    public String getUserName() {

        return UserName;
    }

    public String getPhoneNo() {

        return PhoneNo;
    }

    public String getHouseNo() {

        return HouseNo;
    }

    public String getStreetAddress() {

        return StreetAddress;
    }

    public String getTown() {

        return Town;
    }

    public String getCountry() {

        return Country;
    }

    public void setUserId(String userId) {

        this.userId = userId;
    }

    public void setUserName(String userName) {

        UserName = userName;
    }

    public void setPhoneNo(String phoneNo) {

        PhoneNo = phoneNo;
    }

    public void setHouseNo(String houseNo) {

        HouseNo = houseNo;
    }

    public void setStreetAddress(String streetAddress) {

        StreetAddress = streetAddress;
    }

    public void setTown(String town) {

        Town = town;
    }

    public void setCountry(String country) {

        Country = country;
    }
}
