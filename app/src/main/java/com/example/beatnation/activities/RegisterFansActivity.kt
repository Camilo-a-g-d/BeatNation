package com.example.beatnation.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.beatnation.R

class RegisterFansActivity : AppCompatActivity() {
    private lateinit var btnRegisterFan: Button;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.register_fans_activity);

        // Inicializar propiedades
        btnRegisterFan = findViewById(R.id.btnRegisterFan);

        btnRegisterFan.setOnClickListener {
          startActivity(
              Intent(this, MainRegisterActivity::class.java)
          )
          finish();
        };
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