package com.sophiataskova.apps.smartshoppinglist.models;

import android.text.format.DateUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;

public class Tweet {

    private String body;
    private long uid;
    private User user;
    private String createdAt;

    public static Tweet fromJSON(JSONObject jsonObject) {
        Tweet tweet = new Tweet();
        try {
            tweet.body = jsonObject.getString("text");
            tweet.user = User.fromJSON(jsonObject.getJSONObject("user"));
            tweet.uid = jsonObject.getLong("id");
            tweet.createdAt = getRelativeTimeAgo(jsonObject.getString("created_at"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return tweet;
    }

    public String getBody() {
        return body;
    }

    public long getUid() {
        return uid;
    }

    public String getCreatedAt() {
        return createdAt;
    }
    public User getUser() {
        return user;
    }

    public static Collection<? extends Tweet> fromJSONArray(JSONArray response) {
        ArrayList<Tweet> tweets = new ArrayList<>();
        for (int i = 0; i < response.length(); i++) {
            try {
                JSONObject tweetJsonObject = response.getJSONObject(i);
                Tweet tweet = fromJSON(tweetJsonObject);
                if (tweet != null) {
                    tweets.add(tweet);
                }
            } catch (JSONException e) {
                e.printStackTrace();
                continue;
            }

        }
        return tweets;
    }

    public static String getRelativeTimeAgo(String rawJsonDate) {
        String twitterFormat = "EEE MMM dd HH:mm:ss ZZZZZ yyyy";
        SimpleDateFormat sf = new SimpleDateFormat(twitterFormat, Locale.ENGLISH);
        sf.setLenient(true);

        String relativeDate = "";
        try {
            long dateMillis = sf.parse(rawJsonDate).getTime();
            relativeDate = DateUtils.getRelativeTimeSpanString(dateMillis,
                    System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS).toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return shortenRelativeDate(relativeDate);
    }

    public static String shortenRelativeDate(String longRelativeDate) {
        String shortDate = longRelativeDate.replaceAll(" hour(s)? ago",
                "h");
        shortDate = shortDate.replaceAll(" day(s)? ago", "d");
        shortDate = shortDate.replaceAll(" minute(s)? ago","m");
        shortDate = shortDate.replaceAll(" second(s)? ago","s");
        shortDate = shortDate.replaceAll("Yesterday", "1d");
        shortDate = shortDate.replaceAll(", [0-9]{4}", "");
        return shortDate;
    }

    public static Tweet fromTweetModel(TweetModel tweetModel) {
        Tweet tweet = new Tweet();
//        tweet.body = item.body;
//        tweet.uid = item.localId;
//        tweet.createdAt = item.timeStamp;

//        User user1 = new User();
//        user1.setName(item.name);
//        user1.setUid(item.localUId);
//        user1.setScreenName(item.screenName);
//        user1.setProfilePicUrl(item.profileImage);

//        tweet.user = user1;
        tweet.body = tweetModel.body;
        tweet.uid = tweetModel.uid;
        tweet.createdAt = tweetModel.createdAt;
//        tweet.user = User.fromUserModel(tweetModel.user);

        return tweet;
    }
}
