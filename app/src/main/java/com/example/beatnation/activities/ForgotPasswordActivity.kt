package com.example.beatnation.activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.beatnation.R

class ForgotPasswordActivity : AppCompatActivity() {
    private lateinit var inputTextEmail: EditText;
    private lateinit var btnGoToConfirmCode: Button;
    private lateinit var btnGoToLoginFromForgot: Button;
    private lateinit var sharedPreferences: SharedPreferences;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password_activity);

        // Inicialización de propieades
        sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE);
        inputTextEmail = findViewById(R.id.emailTextToRessetPassword);
        btnGoToConfirmCode = findViewById(R.id.btnGoToConfirmCode);
        btnGoToLoginFromForgot = findViewById(R.id.btnGoToLoginFromForgot);

        btnGoToLoginFromForgot.setOnClickListener {
            startActivity(
                Intent(this, LoginActivity::class.java)
            );
            finish();
        };

        btnGoToConfirmCode.setOnClickListener {
            if(validateForm()) {
                validetEmail();
            }
        };
    }

    private fun validateForm(): Boolean {
        val userEmail = inputTextEmail.text.toString().trim();

        if(userEmail.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()) {
            Toast
                .makeText(this, "Debes ingresar un correo valido para continuar", Toast.LENGTH_SHORT)
                .show();

            return false;
        }

        return true;
    }

    private fun validetEmail() {
        val email = inputTextEmail.text.toString().trim();
        val emailInDB = sharedPreferences.getString("userEmail", "");

        if(email == emailInDB) {
            Toast
                .makeText(this, "Código de verificación enviado al correo 📧", Toast.LENGTH_SHORT)
                .show();


            startActivity(
                Intent(this, VerifyCodeResetPasswordActivity::class.java)
            );
            finish();
        } else {
            Toast
                .makeText(this, "No hemos encontrado un correo que coincida con el ingresado", Toast.LENGTH_SHORT)
                .show();
        }
    }

    override fun onStart() {
        super.onStart()

        Log.d("ForgotPasswordActivity", "onStart: ForgotPasswordActivity está en segundo plano");
    }

    override fun onResume() {
        super.onResume()

        Log.d("ForgotPasswordActivity", "onResume: Entro en onResume");
    }

    override fun onStop() {
        super.onStop()

        Log.d("ForgotPasswordActivity", "onStop: Entro en onStop");
    }

    override fun onPause() {
        super.onPause()

        Log.d("ForgotPasswordActivity", "onPause: Entro en onPause");
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.d("ForgotPasswordActivity", "onDestroy: Entro en onDestroy");
    }
}