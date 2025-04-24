package com.example.beatnation.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.beatnation.R

class ContentIntroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content_intro)
    }

    fun goToNext(view: View) {
        startActivity(Intent(this, SatisfactionIntroActivity::class.java))
        finish()
    }

}
