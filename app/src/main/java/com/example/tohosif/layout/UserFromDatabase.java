package com.example.tohosif.layout;

import java.io.Serializable;

/**
 * Created by Tohosif on 28-07-2017.
 */

public class UserFromDatabase implements Serializable {
    private int id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String gender;
    private String DOB;
    private String city;
    private String emailId;
    private String phoneNo;
    private int iconId;

    public UserFromDatabase(int id, String firstName, String middleName, String lastName, String gender, String DOB, String city, String emailId, String phoneNo) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.gender = gender;
        this.DOB = DOB;
        this.city = city;
        this.emailId = emailId;
        this.phoneNo = phoneNo;
    }

    public int getId() {
        return id;
    }

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

    public String getDOB() {
        return DOB;
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
        return "Id : " + getId() + "\n" +
                "First Name : " + getFirstName() + "\n" +
                "Middle Name : " + getMiddleName() + "\n" +
                "Last Name : " + getLastName() + "\n" +
                "Gender : " + getGender() + "\n" +
                "DOB : " + getDOB() + "\n" +
                "City : " + getCity() + "\n" +
                "Email id : " + getEmailId() + "\n" +
                "Phone No. : " + getPhoneNo();
    }
}
