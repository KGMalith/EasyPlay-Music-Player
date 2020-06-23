package com.example.easyplay.SplashScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.easyplay.BoardingScreen.OnBoarding;
import com.example.easyplay.R;
import com.example.easyplay.Player.AudioPlayer;

public class SplashScreen extends AppCompatActivity {

    private static int SPLASH_SCREEN = 5000;

    //Variables
    Animation topAnim,bottomAnim,bottomAnim2;
    ImageView splashLogo;
    TextView topic1,topic2;

    SharedPreferences onBoardingScreen;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        //Animations
        topAnim = AnimationUtils.loadAnimation(this,R.anim.splash_screen_top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.splash_screen_bottom_animation);
        bottomAnim2 = AnimationUtils.loadAnimation(this,R.anim.splash_screen_bottom_animation_subtpoic);

        //Hooks
        splashLogo = findViewById(R.id.splashLogo);
        topic1 = findViewById(R.id.splashTopic);
        topic2 = findViewById(R.id.splashSubTopic);

        splashLogo.setAnimation(topAnim);
        topic1.setAnimation(bottomAnim);
        topic2.setAnimation(bottomAnim2);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                onBoardingScreen = getSharedPreferences("onBoardingScreen",MODE_PRIVATE);
                boolean isFirstTime = onBoardingScreen.getBoolean("firstTime",true);

                if(isFirstTime){

                    SharedPreferences.Editor editor = onBoardingScreen.edit();
                    editor.putBoolean("firstTime",false);
                    editor.commit();

                    Intent i = new Intent(SplashScreen.this, OnBoarding.class);
                    startActivity(i);
                    finish();
                }else{
                    Intent i = new Intent(SplashScreen.this, AudioPlayer.class);
                    startActivity(i);
                    finish();
                }


            }
        },SPLASH_SCREEN);

    }
}
