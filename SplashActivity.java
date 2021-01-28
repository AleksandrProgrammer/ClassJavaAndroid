package com.example.linkpost;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.ProgressBar;
 
public class SplashActivity extends Activity {
 
	private static CountDownTimer myTimer;
	private int x = 0;
	private ProgressBar PBar;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
         
        PBar = (ProgressBar) findViewById(R.id.progressBar1);
        PBar.setProgress(0);
        
        // запускаем таймер ожидания
        myTimer = new CountDownTimer(5000, 1000) {

               public void onTick(long millisUntilFinished) { 
            	   
            	   PBar.setProgress(x * 20);
            	   x++;
               }

               public void onFinish() {
                   Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                   startActivity(intent);
                   myTimer.cancel();
                   finish();
               }
            };
            myTimer.start();
            
    }
}
