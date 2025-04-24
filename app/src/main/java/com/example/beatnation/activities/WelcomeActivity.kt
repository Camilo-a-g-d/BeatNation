package com.example.beatnation.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.beatnation.R

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        Log.d("WelcomeActivity", "onCreate: Layout cargado correctamente")
    }

    fun goToContentIntro(view: View?) {
        startActivity(Intent(this, ContentIntroActivity::class.java))
        finish()
    }

    fun gotToLogin(view: View?) {
        // startActivity(
        //     Intent(this, LoginActivity::class.java)
        // );
        // finish();
    }

    fun goToRegisterActivity(view: View?) {
        // startActivity(
        //     Intent(this, RegisterActivity::class.java)
        // ;
        // finish();
    }

    override fun onStart() {
        super.onStart()

        Log.d("WelcomeActivity", "onStart: WelcomeActivity está en segundo plano");
    }

    override fun onResume() {
        super.onResume()

        Log.d("WelcomeActivity", "onResume: Entro en onResume");
    }

    override fun onStop() {
        super.onStop()

        Log.d("WelcomeActivity", "onStop: Entro en onStop");
    }

    override fun onPause() {
        super.onPause()

        Log.d("WelcomeActivity", "onPause: Entro en onPause");
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.d("WelcomeActivity", "onDestroy: Entro en onDestroy");
    }
}