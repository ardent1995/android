package com.example.tohosif.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PojoUser implements Serializable {

    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("middle_name")
    @Expose
    private String middleName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("email_id")
    @Expose
    private String emailId;
    @SerializedName("phone_no.")
    @Expose
    private String phoneNo;

    private int iconId;

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }

    public String getCity() {
        return city;
    }

    public String getEmailId() {
        return emailId;
    }

    public String getPhoneNo() {
        return phoneNo;
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
