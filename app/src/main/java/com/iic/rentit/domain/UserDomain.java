package com.iic.rentit.domain;

import java.io.Serializable;

public class UserDomain implements Serializable {

    public UserDomain(String username, String password, String userid) {
        this.username = username;
        this.password = password;
        this.userid = userid;
    }

    private String username;
    private String password;
    private String userid;
    private String PhoneNumber;
    private String Address;
    private String NrcNumber;

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getNrcNumber() {
        return NrcNumber;
    }

    public void setNrcNumber(String nrcNumber) {
        NrcNumber = nrcNumber;
    }

    public String getLiscenNumber() {
        return LiscenNumber;
    }

    public void setLiscenNumber(String liscenNumber) {
        LiscenNumber = liscenNumber;
    }

    private String LiscenNumber;

    public UserDomain (){};

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserid() {
        if (userid != null && !userid.isEmpty()) {
            return Integer.parseInt(userid);
        } else {
            return -1; // or any default value indicating that the userid is not set
        }
    }


    public void setUserid(String userid) {
        this.userid = userid;
    }


}
