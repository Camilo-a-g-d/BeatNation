package com.example.beatnation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class RegisterFansActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.register_fans_activity);
    }

    override fun onStart() {
        super.onStart()

        Log.d("RegisterFansActivity", "onStart: RegisterFansActivity está en segundo plano");
    }

    override fun onResume() {
        super.onResume()

        Log.d("RegisterFansActivity", "onResume: Entro en onResume");
    }

    override fun onStop() {
        super.onStop()

        Log.d("RegisterFansActivity", "onStop: Entro en onStop");
    }

    override fun onPause() {
        super.onPause()

        Log.d("RegisterFansActivity", "onPause: Entro en onPause");
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.d("RegisterFansActivity", "onDestroy: Entro en onDestroy");
    }
}