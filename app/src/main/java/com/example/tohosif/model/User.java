package com.example.tohosif.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by Tohosif on 25-07-2017.
 */

public class User implements Serializable {
    protected String firstName, middleName, lastName, gender, city, emailId, phoneNo;
    protected int iconId;

    public User() {

    }

    public User(JSONObject jsonObject) {
        try {
            setFirstName(jsonObject.getString("first_name"));
            setMiddleName(jsonObject.getString("middle_name"));
            setLastName(jsonObject.getString("last_name"));
            setGender(jsonObject.getString("gender"));
            setCity(jsonObject.getString("city"));
            setEmailId(jsonObject.getString("email_id"));
            setPhoneNo(jsonObject.getString("phone_no."));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public String getDetails() {
        return "First Name : " + getFirstName() + "\n" +
                "Middle Name : " + getMiddleName() + "\n" +
                "Last Name : " + getLastName() + "\n" +
                "Gender : " + getGender() + "\n" +
                "City : " + getCity() + "\n" +
                "Email id : " + getEmailId() + "\n" +
                "Phone No. : " + getPhoneNo();
    }
}
