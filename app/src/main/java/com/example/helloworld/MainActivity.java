package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    AnimationDrawable logoAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView helloText = (TextView) findViewById(R.id.helloText);
        ImageView logoImage = (ImageView) findViewById(R.id.logo);
        ImageView stillImage = (ImageView) findViewById(R.id.logoStill);
        final String displayText = "Hello World!";


        Button startImage = findViewById(R.id.startButton);
        startImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if ((stillImage.getVisibility()==View.VISIBLE)){

                    //Makes the Android character wave
                    //https://developer.android.com/guide/topics/graphics/drawable-animation?authuser=2
                    stillImage.setVisibility(View.INVISIBLE);
                    logoImage.setVisibility(View.VISIBLE);
                    logoImage.setBackgroundResource(R.drawable.logo_animation);
                    logoAnimation = (AnimationDrawable) logoImage.getBackground();
                    logoAnimation.start();

                    //This displays the text letter by letter in the TextView
                    //https://stackoverflow.com/questions/34569675/display-a-string-letter-by-letter-in-a-textview-android/34571018
                    Thread thread = new Thread(){
                        int i;
                        @Override
                        public void run(){
                            try{
                                for(i=0;i<displayText.length(); i++) {
                                    Thread.sleep(300);
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            helloText.setText(displayText.substring(0, i));
                                        }
                                    });
                                }
                            }catch(InterruptedException e){
                                System.out.println(e);
                                System.exit(0);
                            }
                        }
                    };
                    thread.start();
                }else{

                    //Stops the Android character
                    logoAnimation.stop();
                    logoImage.setVisibility(View.INVISIBLE);
                    stillImage.setVisibility(View.VISIBLE);
                    helloText.setText("");
                }


            }
        });
    }
}