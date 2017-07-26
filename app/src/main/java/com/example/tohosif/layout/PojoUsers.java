package com.example.tohosif.layout;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class PojoUsers {

    @SerializedName("users")
    @Expose
    //If {@code true}, the field marked with this annotation is deserialized from the JSON. Defaults to {@code true}.
    private List<PojoUser> users = new ArrayList<>();

    public List<PojoUser> getUsers() {
        return users;
    }
}