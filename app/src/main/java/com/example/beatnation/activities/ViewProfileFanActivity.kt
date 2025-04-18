package com.example.beatnation.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.beatnation.R

class ViewProfileFanActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_perfil_fans_activity);
    }

    override fun onStart() {
        super.onStart()

        Log.d("ViewProfileFanActivity", "onStart: ViewProfileFanActivity está en segundo plano");
    }

    override fun onResume() {
        super.onResume()

        Log.d("ViewProfileFanActivity", "onResume: Entro en onResume");
    }

    override fun onStop() {
        super.onStop()

        Log.d("ViewProfileFanActivity", "onStop: Entro en onStop");
    }

    override fun onPause() {
        super.onPause()

        Log.d("ViewProfileFanActivity", "onPause: Entro en onPause");
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.d("ViewProfileFanActivity", "onDestroy: Entro en onDestroy");
    }
}