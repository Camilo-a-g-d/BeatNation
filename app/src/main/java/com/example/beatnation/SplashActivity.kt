package com.example.beatnation

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    private val SPLASH_TIME_OUT: Long = 1850;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.splash_activity);

        // Llevar a MainActivity
        Handler(mainLooper).postDelayed({
            startActivity(
                Intent(this, LoginActivity::class.java)
            );
            finish();
        }, SPLASH_TIME_OUT);
    }

    override fun onStart() {
        super.onStart()

        Log.d("SplashActiviy", "onStart: SplashActivity está en segundo plano");
    }

    override fun onResume() {
        super.onResume()

        Log.d("SplashActiviy", "onResume: Entro en onResume");
    }

    override fun onStop() {
        super.onStop()

        Log.d("SplashActiviy", "onStop: Entro en onStop");
    }

    override fun onPause() {
        super.onPause()

        Log.d("SplashActiviy", "onPause: Entro en onPause");
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.d("SplashActiviy", "onDestroy: Entro en onDestroy");
    }
}