package com.codepath.apps.simpletwitterclient.models;

import com.orm.SugarRecord;

public class UserModel extends SugarRecord<UserModel> {
     String name;
     long uid;
     String screenName;
     String profilePicUrl;

    public UserModel() {
        name = "";
        uid = 0;
        screenName = "";
        profilePicUrl = "";
    }

    public UserModel(String name, long uid, String screenName, String profilePicUrl) {
        this.name = name;
        this.uid = uid;
        this.screenName = screenName;
        this.profilePicUrl = profilePicUrl;
    }
}
