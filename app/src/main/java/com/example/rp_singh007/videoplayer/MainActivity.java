package com.example.rp_singh007.videoplayer;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity{

    Animation fade_in, fade_out,bounce;
    ViewFlipper viewFlipper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

            viewFlipper = (ViewFlipper) this.findViewById(R.id.bckgrndViewFlipper1);
            fade_in = AnimationUtils.loadAnimation(this,
                    android.R.anim.fade_in);
            fade_out = AnimationUtils.loadAnimation(this,android.R.anim.fade_out);






            viewFlipper.setInAnimation(fade_in);
            viewFlipper.setOutAnimation(fade_out);
//sets auto flipping
            viewFlipper.setAutoStart(true);
            viewFlipper.setFlipInterval(5000);
            viewFlipper.startFlipping();
        }
    }