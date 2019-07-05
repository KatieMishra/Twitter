package com.codepath.apps.restclienttemplate;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.restclienttemplate.models.Tweet;

import org.parceler.Parcels;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class DetailActivity extends AppCompatActivity {

    //movie to display
    Tweet tweet;
    //movie image
    ImageView ivProfileImage;
    // the adapter wired to the recycler view
    TweetAdapter adapter;
    TextView name;
    TextView userID;
    TextView tweetBody;
    TextView time;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("TAG", "in detail");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ActionBar action = getSupportActionBar();
        action.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.twitter_blue)));
        action.setTitle("Tweet Details");

        //unwrap the movie passed in via intent, using its simple name as a key
        tweet = (Tweet) Parcels.unwrap(getIntent().getParcelableExtra(Tweet.class.getSimpleName()));

        name = (TextView) findViewById(R.id.dName);
        userID = (TextView) findViewById(R.id.dUID);
        tweetBody = (TextView) findViewById(R.id.dBody);
        time = (TextView) findViewById(R.id.dTime);
        ivProfileImage = (ImageView) findViewById(R.id.ivProfileImage);

        final Button button = findViewById(R.id.replyToTweet);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // create intent for the new activity
                Intent respond_to_tweet = new Intent(context, RespondToTweet.class);
                //serialize the movie using parceler, use its short name as a key
                respond_to_tweet.putExtra(Tweet.class.getSimpleName(), Parcels.wrap(tweet));
                // show the activity
                context.startActivity(respond_to_tweet);
            }
        });

        name.setText(tweet.user.name);
        userID.setText("@" + tweet.user.screenName);
        tweetBody.setText(tweet.body);
        time.setText(getRelativeTimeAgo(tweet.createdAt));
        //ivProfileImage.setImageBitmap(tweet.user.profileImageUrl);
        //Glide.with(context).load(tweet.user.profileImageUrl).into(holder.ivProfileImage);

    }

    // return how long ago relative to current time tweet was sent
    public String getRelativeTimeAgo(String rawJsonDate) {
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

        return relativeDate;
    }
}
