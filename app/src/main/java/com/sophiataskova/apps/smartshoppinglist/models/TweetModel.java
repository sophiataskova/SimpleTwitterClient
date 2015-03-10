package com.sophiataskova.apps.smartshoppinglist.models;

import com.orm.SugarRecord;

public class TweetModel extends SugarRecord<TweetModel> {
     String body;
     long uid;
     public UserModel userModel;
     String createdAt;

    public TweetModel() {
        body = "";
        uid = 0;
        userModel = new UserModel();
        createdAt = "";
    }

    public TweetModel(String body, long uid, UserModel userModel, String createdAt) {
        this.body = body;
        this.uid = uid;
        this.userModel = userModel;
        this.createdAt = createdAt;
    }
}