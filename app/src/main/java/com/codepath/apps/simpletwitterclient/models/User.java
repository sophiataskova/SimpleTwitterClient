package com.codepath.apps.simpletwitterclient.models;

import org.json.JSONException;
import org.json.JSONObject;

public class User {

    private String name;
    private long uid;
    private String screenName;
    private String profilePicUrl;


    private String tagLine;
    private int followersCount;
    private int followingCount;

    public void setName(String name) {
        this.name = name;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public void setProfilePicUrl(String profilePicUrl) {
        this.profilePicUrl = profilePicUrl;
    }


    public static User fromJSON(JSONObject jsonObject) {
        User user = new User();
        try {
            user.name = jsonObject.getString("name");
            user.profilePicUrl = jsonObject.getString("profile_image_url");
            user.screenName = jsonObject.getString("screen_name");
            user.uid = jsonObject.getLong("id");
            user.tagLine = jsonObject.getString("description");
            user.followersCount = jsonObject.getInt("followers_count");
            user.followingCount = jsonObject.getInt("friends_count");
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

    public String getTagLine() {
        return tagLine;
    }

    public int getFollowersCount() {
        return followersCount;
    }

    public int getFollowingCount() {
        return followingCount;
    }
    public static User fromUserModel(UserModel userModel) {
        User user = new User();
        user.name = userModel.name;
        user.uid = userModel.uid;
        user.profilePicUrl = userModel.profilePicUrl;
        user.screenName = userModel.screenName;

        return user;
    }
}
