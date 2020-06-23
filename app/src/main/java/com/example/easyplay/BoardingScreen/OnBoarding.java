package com.example.easyplay.BoardingScreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.easyplay.Adapter.SliderAdapter;
import com.example.easyplay.R;
import com.example.easyplay.Player.AudioPlayer;

public class OnBoarding extends AppCompatActivity {

    ViewPager viewPager;
    LinearLayout dotsLayout;

    SliderAdapter sliderAdapter;
    TextView[] dots;
    Button letsGetStarted,skipButton,nextButton;
    Animation animation;
    int currentPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_on_boarding);

        //Hooks
        viewPager = findViewById(R.id.slider);
        dotsLayout = findViewById(R.id.dots);
        letsGetStarted = findViewById(R.id.get_started_btn);
        skipButton = findViewById(R.id.skip_btn);
        nextButton = findViewById(R.id.next_btn);

        //Call adapter
        sliderAdapter = new SliderAdapter(this);
        viewPager.setAdapter(sliderAdapter);
        addDots(0);
        viewPager.addOnPageChangeListener(changeListener);
    }

    public void skip(View view){
        startActivity(new Intent(getApplicationContext(), AudioPlayer.class));
        finish();
    }

    public void next(View view){

        viewPager.setCurrentItem(currentPosition + 1);
    }

    public void getStated(View view){
        startActivity(new Intent(getApplicationContext(), AudioPlayer.class));
        finish();
    }

    private void addDots(int position){
        dots = new TextView[3];
        dotsLayout.removeAllViews();

        for(int i = 0;i<dots.length; i++){
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(50);

            dotsLayout.addView(dots[i]);
        }
        if(dots.length>0){
            dots[position].setTextColor(getResources().getColor(R.color.colorSuccess));
            dots[position].setTextSize(55);
        }
    }

    ViewPager.OnPageChangeListener changeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDots(position);

            currentPosition = position;

            if(position == 0){
                letsGetStarted.setVisibility(View.INVISIBLE);
                skipButton.setVisibility(View.VISIBLE);
                nextButton.setVisibility(View.VISIBLE);
            }else if(position == 1){
                letsGetStarted.setVisibility(View.INVISIBLE);
                skipButton.setVisibility(View.VISIBLE);
                nextButton.setVisibility(View.VISIBLE);
            }else if(position == 2){
                animation = AnimationUtils.loadAnimation(OnBoarding.this,R.anim.boarding_screen_btn_animation);
                letsGetStarted.setAnimation(animation);
                letsGetStarted.setVisibility(View.VISIBLE);
                skipButton.setVisibility(View.INVISIBLE);
                nextButton.setVisibility(View.INVISIBLE);
            }

        }


        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}