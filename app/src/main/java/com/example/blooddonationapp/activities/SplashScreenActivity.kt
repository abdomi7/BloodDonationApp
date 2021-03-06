package com.example.blooddonationapp.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.blooddonationapp.R
import com.example.blooddonationapp.viewModels.SplashScreenActivityViewModel

class SplashScreenActivity : AppCompatActivity() {
    var progressBar: ProgressBar? = null

    private val model: SplashScreenActivityViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        progressBar = findViewById(R.id.progressBar1)
        Handler().postDelayed({
            // This method will be executed once the timer is over\
            when {
                isFirstTime -> {
                    // What you do when the Application is Opened First time Goes here
                    val s = Intent(applicationContext, AppIntro::class.java)
                    startActivity(s)
                    finish()
                }
                model.currentUser == null -> {

                    //check if no user is logged in
                    val i = Intent(applicationContext, LoginActivity::class.java)
                    startActivity(i)
                    finish()
                }
                else -> {
                    val i = Intent(applicationContext, MainActivity::class.java)
                    startActivity(i)
                    finish()
                }
            }

        }, 1000)
    }

    // first time
    private val isFirstTime: Boolean
        get() {
            val preferences = getPreferences(MODE_PRIVATE)
            val ranBefore = preferences.getBoolean("RanBefore", false)
            if (!ranBefore) {
                // first time
                val editor = preferences.edit()
                editor.putBoolean("RanBefore", true)
                editor.apply()
            }
            return !ranBefore
        }
}