package com.example.beatnation.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.beatnation.R

class SplashActivity : AppCompatActivity() {

    private val SPLASH_TIME_OUT: Long = 1850

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)

        Handler(mainLooper).postDelayed({
            startActivity(Intent(this, WelcomeActivity::class.java))
            finish()
        }, SPLASH_TIME_OUT)
    }

    override fun onStart() {
        super.onStart()
        Log.d("SplashActivity", "onStart: SplashActivity está en primer plano")
    }

    override fun onResume() {
        super.onResume()
        Log.d("SplashActivity", "onResume: Entro en onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("SplashActivity", "onPause: Entro en onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("SplashActivity", "onStop: Entro en onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("SplashActivity", "onDestroy: Entro en onDestroy")
    }
}
