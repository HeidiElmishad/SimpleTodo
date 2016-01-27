package com.codepath.simpletodo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

/**
 * Created by snapfish on 1/26/16.
 */
public class SplashScreen extends Activity {

    private static final int SPLASH_TIMER = 2000;
    private CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);

        initVars();
    }

    @Override
    protected void onDestroy() {
        timer.cancel();
        super.onDestroy();
    }

    private void initVars() {
        Bundle b = getIntent().getExtras();
        int splashTimer = SPLASH_TIMER;
        if (b != null) {
            splashTimer = 0;
        }

        timer = new CountDownTimer(splashTimer, 500) {

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                gotoMain();
            }
        }.start();
    }

    public void gotoMain(){
        Intent mainIntent = new Intent(SplashScreen.this, MainActivity.class);
        SplashScreen.this.startActivity(mainIntent);
        SplashScreen.this.finish();
    }

}
