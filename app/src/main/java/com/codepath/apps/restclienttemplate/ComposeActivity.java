package com.codepath.apps.restclienttemplate;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

/* Katie Mishra - FBU Android 2019 - krmishra@stanford.edu
 * ComposeActivity lets users write and post their own tweets.
 */
public class ComposeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);
        EditText simpleEditText = (EditText) findViewById(R.id.tweetText);
        String strValue = simpleEditText.getText().toString();
    }

    /*public void onTweet(View v) {
        // Prepare intent to pass back to MainActivity
        Intent data = new Intent();
        // Pass updated item text and original position
        data.putExtra("position", position); // ints work too
        data.putExtra("content", etItemText.getText().toString());
        //successful completion - can also use RESULT_CANCELLED for when you don't want to save
        setResult(RESULT_OK, data);
        //close window and go back to previous activity
        finish();
    }*/
}
