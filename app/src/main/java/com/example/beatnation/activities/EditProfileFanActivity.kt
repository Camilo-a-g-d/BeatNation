package com.example.beatnation.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.beatnation.R

class EditProfileFanActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_profile_fan_activity);
    }

    override fun onStart() {
        super.onStart()

        Log.d("EditProfileFanActivity", "onStart: EditProfileFanActivity está en segundo plano");
    }

    override fun onResume() {
        super.onResume()

        Log.d("EditProfileFanActivity", "onResume: Entro en onResume");
    }

    override fun onStop() {
        super.onStop()

        Log.d("EditProfileFanActivity", "onStop: Entro en onStop");
    }

    override fun onPause() {
        super.onPause()

        Log.d("EditProfileFanActivity", "onPause: Entro en onPause");
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.d("EditProfileFanActivity", "onDestroy: Entro en onDestroy");
    }
}