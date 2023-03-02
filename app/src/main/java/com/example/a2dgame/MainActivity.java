package com.example.a2dgame;

import android.app.Activity;
import android.os.Bundle;

/*
* Main Activity is the entry point to application
*/
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Set content to our GameClass so that it can be rendered to screen
        setContentView(new Game(this));
    }
}