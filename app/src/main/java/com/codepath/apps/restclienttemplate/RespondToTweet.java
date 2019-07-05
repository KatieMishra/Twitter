package com.codepath.apps.restclienttemplate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.codepath.apps.restclienttemplate.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;
import org.parceler.Parcels;

import cz.msebera.android.httpclient.Header;

public class RespondToTweet extends AppCompatActivity {

    EditText etTweetResponse;
    Button btnReply;
    TwitterClient client;
    public static final String RESULT_TWEET_KEY = "key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_respond_to_tweet);

        etTweetResponse = findViewById(R.id.etTweetInput);
        btnReply = findViewById(R.id.btnSend);

        btnReply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replyToTweet(etTweetResponse.getText().toString());
            }
        });

        client = TwitterApp.getRestClient(this);
    }

    public void replyToTweet(String message){
        client.sendTweet(message, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Tweet tweet= Tweet.fromJSON(response);
                Intent sendResult= new Intent();
                sendResult.putExtra("tweet", Parcels.wrap(tweet));
                setResult(RESULT_OK, sendResult);
                finish();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });
    }
}