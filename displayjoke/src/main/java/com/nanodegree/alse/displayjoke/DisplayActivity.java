package com.nanodegree.alse.displayjoke;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayActivity extends AppCompatActivity {

    public static final String JOKE_EXTRA = "joke_extra";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        Intent intent = getIntent();
        if(intent != null){
            String joke = intent.getStringExtra(JOKE_EXTRA);
            if(joke!=null){
                TextView textView = (TextView)findViewById(R.id.joke);
                textView.setText(joke);
            }
        }
    }
}
