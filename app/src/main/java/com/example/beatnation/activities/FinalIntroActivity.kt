package com.example.beatnation.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.beatnation.R

class FinalIntroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_final_intro)
    }

    fun goToLogin(view: View) {
        startActivity(Intent(this, JoinUsActivity::class.java))
        finish()
    }

}
