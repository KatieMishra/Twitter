package com.codepath.apps.restclienttemplate.models;

import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.io.Serializable;

@Parcel
public class User implements Parcelable {

    // list all the attributes
    public String name;
    public long uid;
    public String screenName;
    public String profileImageUrl;

    //deserialize the JSON
    public static User fromJSON(JSONObject json) throws JSONException {
        User user = new User();

        // extract and fill the values
        try {
            user.name = json.getString("name");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            user.uid = json.getLong("id");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            user.screenName = json.getString("screen_name");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            user.profileImageUrl = json.getString("profile_image_url");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return user;
    }
}
