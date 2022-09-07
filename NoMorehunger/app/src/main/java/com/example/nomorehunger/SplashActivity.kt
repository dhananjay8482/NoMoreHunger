package com.example.nomorehunger

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView

class SplashActivity : AppCompatActivity() {

    val SPLASH_SCREEN = 2300

    //    INITIALIZE VARIABLES
    lateinit var center : Animation
    lateinit var image: ImageView
    lateinit var title: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        image = findViewById(R.id.hunger_fighter_logo)
        title = findViewById(R.id.splash_title)

//        DECLARE ANIMATION
        center = AnimationUtils.loadAnimation(this,R.anim.center_anim)

//      SET ANIMATION TO IMAGE
        image.setAnimation(center)

        //        TIME HANDLER
        Handler(Looper.getMainLooper()).postDelayed({

//            INTENT set for setting path for next Activity
            intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
            finish()

        },SPLASH_SCREEN.toLong())


    }
}