package com.example.beatnation.activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.beatnation.R

class MainRegisterActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences;
    private lateinit var typeInstrustyUserView: TextView
    private lateinit var btnRegisterNewUser: Button;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.main_register_activity);

        //Inicializar propiedades
        typeInstrustyUserView = findViewById(R.id.typeInstrustyUser);
        btnRegisterNewUser = findViewById(R.id.btnContinueMainRegisterActivity);

        // Inicializar almacenamiento temporal
        sharedPreferences = getSharedPreferences("registerInfo", MODE_PRIVATE);
        var typeUser = sharedPreferences.getString("typeUser", "");
        var typeIndsutryUser = sharedPreferences.getString("typeIndustryUser", "");

        if(typeUser.equals("Fan")) {
            typeInstrustyUserView.visibility = View.GONE;
        } else {
            typeInstrustyUserView.visibility = View.VISIBLE;
            typeInstrustyUserView.text = typeIndsutryUser.toString();
        }

        btnRegisterNewUser.setOnClickListener {
            startActivity(
                Intent(
                    this, MainActivity::class.java
                )
            );
            finish();
        }
    }

    override fun onStart() {
        super.onStart()

        Log.d("MainRegisterActivity", "onStart: MainRegisterActivity está en segundo plano");
    }

    override fun onResume() {
        super.onResume()

        Log.d("MainRegisterActivity", "onResume: Entro en onResume");
    }

    override fun onStop() {
        super.onStop()

        Log.d("MainRegisterActivity", "onStop: Entro en onStop");
    }

    override fun onPause() {
        super.onPause()

        Log.d("MainRegisterActivity", "onPause: Entro en onPause");
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.d("MainRegisterActivity", "onDestroy: Entro en onDestroy");
    }
}