package com.example.beatnation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class RegisterTypeIndustryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.register_type_industry_activity);
    }

    override fun onStart() {
        super.onStart()

        Log.d("RegisterTypeIndustryActivity", "onStart: RegisterTypeIndustryActivity está en segundo plano");
    }

    override fun onResume() {
        super.onResume()

        Log.d("RegisterTypeIndustryActivity", "onResume: Entro en onResume");
    }

    override fun onStop() {
        super.onStop()

        Log.d("RegisterTypeIndustryActivity", "onStop: Entro en onStop");
    }

    override fun onPause() {
        super.onPause()

        Log.d("RegisterTypeIndustryActivity", "onPause: Entro en onPause");
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.d("RegisterTypeIndustryActivity", "onDestroy: Entro en onDestroy");
    }
}