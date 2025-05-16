package com.example.beatnation.activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.beatnation.R

class ChangePasswordActivity: AppCompatActivity() {
    private lateinit var inputNewPassword: EditText;
    private lateinit var changePasswordBtn: Button;
    private lateinit var sharedPreferences: SharedPreferences;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_password_activity);

        // Inicializar propiedades
        sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE);
        inputNewPassword = findViewById(R.id.inputNewPassword);
        changePasswordBtn = findViewById(R.id.btnSetNewPassword);

        changePasswordBtn.setOnClickListener {
            if(validateForm()) {
                setNewPassword();
            }
        };
    }

    private fun validateForm(): Boolean {
        val newPassword = inputNewPassword.text.toString().trim();

        if(newPassword.isEmpty()) {
            Toast
                .makeText(this, "La contraseña es obligatoria", Toast.LENGTH_SHORT)
                .show();

            return false;
        }

        if(!isValidPassword(newPassword)) {
            Toast
                .makeText(this, "La contraseña no cumple con los requisitos mínimos", Toast.LENGTH_SHORT)
                .show();

            return false;
        }

        return true;
    }

    private fun setNewPassword() {
        val editor = sharedPreferences.edit();

        editor.putString("userPassword", inputNewPassword.text.toString().trim());

        editor.apply();

        Toast
            .makeText(this, "Contraseña actualizada exitosamente", Toast.LENGTH_SHORT)
            .show();

        Handler(mainLooper).postDelayed({
            startActivity(
                Intent(this, LoginActivity::class.java)
            );
            finish();
        }, 2000);
    }

    private fun isValidPassword(password: String): Boolean {
        val passwordPattern = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[!@#$%^&*(),.?\":{}|<>])(?=.*\\d{3,4}).{10}$".toRegex();
        return passwordPattern.matches(password);
    }

    override fun onStart() {
        super.onStart()

        Log.d("ChangePasswordActivity", "onStart: ChangePasswordActivity está en segundo plano");
    }

    override fun onResume() {
        super.onResume()

        Log.d("ChangePasswordActivity", "onResume: Entro en onResume");
    }

    override fun onStop() {
        super.onStop()

        Log.d("ChangePasswordActivity", "onStop: Entro en onStop");
    }

    override fun onPause() {
        super.onPause()

        Log.d("ChangePasswordActivity", "onPause: Entro en onPause");
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.d("ChangePasswordActivity", "onDestroy: Entro en onDestroy");
    }
}