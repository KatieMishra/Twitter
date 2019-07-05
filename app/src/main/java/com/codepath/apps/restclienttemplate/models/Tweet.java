package com.codepath.apps.restclienttemplate.models;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

@Parcel
public class Tweet {

    // list out the attributes to store
    public String body;
    public long uid; //database ID for the tweet
    public User user;
    public String createdAt;
    public int retweetCount;
    public Integer favoriteCount;

    // deserialize the JSON
    public static Tweet fromJSON(JSONObject jsonObject) {
        Tweet tweet = new Tweet();

        //extract the values from JSON
        try {
            tweet.body = jsonObject.getString("text");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            tweet.uid = jsonObject.getLong("id");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            tweet.createdAt = jsonObject.getString("created_at");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            tweet.user = User.fromJSON(jsonObject.getJSONObject("user"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            tweet.retweetCount = jsonObject.getInt("retweet_count");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            tweet.favoriteCount = jsonObject.getInt("favorite_count");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return tweet;
    }

}
