package com.codepath.apps.restclienttemplate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.codepath.apps.restclienttemplate.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.wafflecopter.charcounttextview.CharCountTextView;

import org.json.JSONObject;
import org.parceler.Parcels;

import cz.msebera.android.httpclient.Header;

/* Katie Mishra - FBU Android 2019 - krmishra@stanford.edu
 * ComposeActivity lets users write and post their own tweets.
 */
public class ComposeActivity extends AppCompatActivity {

    EditText etTweetInput;
    Button btnSend;
    TwitterClient client;
    public static final String RESULT_TWEET_KEY = "key";

    public ComposeActivity() {}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);

        etTweetInput = findViewById(R.id.etTweetInput);
        btnSend = findViewById(R.id.btnSend);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postTweet(etTweetInput.getText().toString());
            }
        });

        CharCountTextView charCountTextView = (CharCountTextView)findViewById(R.id.tvTextCounter);
        EditText editText = (EditText)findViewById(R.id.etTweetInput);

        charCountTextView.setEditText(editText);
        charCountTextView.setCharCountChangedListener(new CharCountTextView.CharCountChangedListener() {
            @Override
            public void onCountChanged(int i, boolean b) {

            }
        });

        client = TwitterApp.getRestClient(this);
    }

   public void postTweet(String message){
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
