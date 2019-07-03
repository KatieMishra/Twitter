package com.codepath.apps.restclienttemplate.models;

import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.io.Serializable;

@Parcel
public class Tweet implements Parcelable {

    // list out the attributes to store
    public String body;
    public long uid; //database ID for the tweet
    public User user;
    public String createdAt;

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
        return tweet;
    }
}
