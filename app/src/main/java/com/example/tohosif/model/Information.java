package com.example.tohosif.model;

import org.json.JSONObject;


/**
 * Created by Tohosif on 14-07-2017.
 */

public class Information extends User {
    private int iconId;

    public Information(JSONObject jsonObject, int iconId) {
        super(jsonObject);
        this.iconId = iconId;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }
}
