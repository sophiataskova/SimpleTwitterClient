package com.codepath.apps.simpletwitterclient.models;

import org.json.JSONException;
import org.json.JSONObject;

public class User {


    private String name;
    private long uid;
    private String screenName;
    private String profilePicUrl;

    public static User fromJSON(JSONObject jsonObject) {
        User user = new User();
        try {
            user.name = jsonObject.getString("name");
            user.profilePicUrl = jsonObject.getString("profile_image_url");
            user.screenName = jsonObject.getString("screen_name");
            user.uid = jsonObject.getLong("id");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return user;
    }

    public String getName() {
        return name;
    }

    public long getUid() {
        return uid;
    }

    public String getScreenName() {
        return screenName;
    }

    public String getProfilePicUrl() {
        return profilePicUrl;
    }
}
