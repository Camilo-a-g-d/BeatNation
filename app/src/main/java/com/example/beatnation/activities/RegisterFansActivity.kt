package com.example.beatnation.activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.beatnation.R

class RegisterFansActivity : AppCompatActivity() {
    private lateinit var userEmailFan: EditText;
    private lateinit var userPasswordFan: EditText
    private lateinit var btnRegisterFan: Button;
    private lateinit var sharedPreferences: SharedPreferences;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.register_fans_activity);

        // Inicializar propiedades
        userEmailFan = findViewById(R.id.inputUserEmailFan);
        userPasswordFan = findViewById(R.id.inputUserPasswordFan);
        btnRegisterFan = findViewById(R.id.btnRegisterFan);
        sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE);

        btnRegisterFan.setOnClickListener {
          if(validateForm()) {
              preSaveDataUser();
          }
        };
    }

    private fun validateForm(): Boolean {
        val userEmailFanValue = userEmailFan.text.trim();
        val userPasswordFanValue = userPasswordFan.text.trim();

        if(userEmailFanValue.isEmpty()) {
            Toast
                .makeText(this, "Debes ingresar el correo electrónico para continuar", Toast.LENGTH_SHORT)
                .show();

            return false;
        }

        if(userPasswordFanValue.isEmpty()) {
            Toast
                .makeText(this, "Debes ingresar la contraseña para continuar", Toast.LENGTH_SHORT)
                .show();

            return false;
        }

        return true;
    }

    private fun preSaveDataUser() {
        val editor = sharedPreferences.edit();

        editor.putString("userEmail", userEmailFan.text.toString().trim());
        editor.putString("userPassword", userPasswordFan.text.toString().trim());

        editor.apply();

        startActivity(
            Intent(this, MainRegisterActivity::class.java)
        );
        finish();
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