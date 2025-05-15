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

class VerifyCodeResetPasswordActivity : AppCompatActivity() {
    private lateinit var inputVerificationCode: EditText;
    private lateinit var btnGoToChangePassword: Button;
    private lateinit var sharedPreferences: SharedPreferences;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verify_code_reset_password_activity);

        // Inicializar propiedades
        sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE);
        inputVerificationCode = findViewById(R.id.inputVerificationCode);
        btnGoToChangePassword = findViewById(R.id.btnGoToChangePassword);

        btnGoToChangePassword.setOnClickListener {
            if(validateForm()) {
                validatePIN();
            }
        };
    }

    private fun validateForm(): Boolean {
        val code = inputVerificationCode.text.toString().trim();

        if(code.isEmpty()) {
            Toast
                .makeText(this, "Debes ingresar el PIN de verificación para continuar", Toast.LENGTH_SHORT)
                .show();

            return false;
        }

        return true;
    }

    private fun validatePIN() {
        val code = inputVerificationCode.text.toString().trim();

        if(code == "1234567890") {
            Toast
                .makeText(this, "Verificación realizada con éxito", Toast.LENGTH_SHORT)
                .show();

            startActivity(
                Intent(this, ChangePasswordActivity::class.java)
            );
            finish();
        } else {
            Toast
                .makeText(this, "El PIN de verificación ingresado no es valido", Toast.LENGTH_SHORT)
                .show();
        }
    }

    override fun onStart() {
        super.onStart()

        Log.d("VerifyCodeResetPasswordActivity", "onStart: VerifyCodeResetPasswordActivity está en segundo plano");
    }

    override fun onResume() {
        super.onResume()

        Log.d("VerifyCodeResetPasswordActivity", "onResume: Entro en onResume");
    }

    override fun onStop() {
        super.onStop()

        Log.d("VerifyCodeResetPasswordActivity", "onStop: Entro en onStop");
    }

    override fun onPause() {
        super.onPause()

        Log.d("VerifyCodeResetPasswordActivity", "onPause: Entro en onPause");
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.d("VerifyCodeResetPasswordActivity", "onDestroy: Entro en onDestroy");
    }
}