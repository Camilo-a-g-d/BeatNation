package com.example.beatnation

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    // Propiedades del formulario
    private lateinit var inputTextEmail: EditText;
    private lateinit var inputTextPassword: EditText;
    private lateinit var btnLogin: Button;
    private lateinit var btnGoToPreRegister: Button;

    // Propiedades para manejar información de forma local
    private lateinit var sharedPreferences: SharedPreferences;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.login_activity);

        // Inicializar almacenamiento temporal
        sharedPreferences = getSharedPreferences("UserDataTemp", MODE_PRIVATE);

        // Inicializar variables
        inputTextEmail = findViewById(R.id.inputUserEmailLogin);
        inputTextPassword = findViewById(R.id.inputPasswordLogin);
        btnLogin = findViewById(R.id.btnLogin);
        btnGoToPreRegister = findViewById(R.id.btnGoToPreRegister);

        btnLogin.setOnClickListener {
            // if(validateForm()) {
            //     validateUser();
            // }
        };

        btnGoToPreRegister.setOnClickListener {
            startActivity(
                Intent(this, PreRegisterActivity::class.java)
            )
            finish();
        };
    }

    private fun validateForm(): Boolean {
        val userEmail = inputTextEmail.text.toString().trim();
        val userPassword = inputTextPassword.text.toString().trim();

        if(userEmail.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()) {
            Toast
                .makeText(this, "Debes ingresar un correo valido para continuar", Toast.LENGTH_SHORT)
                .show();

            return false;
        }

        if(userPassword.isEmpty()) {
            Toast
                .makeText(this, "Debes ingresar una contraseña para continuar", Toast.LENGTH_SHORT)
                .show();

            return false;
        }

        return true;
    }

    private fun validateUser() {
        val email = inputTextEmail.text.toString().trim();
        val password = inputTextPassword.text.toString().trim();
        val emailInDB = sharedPreferences.getString("userEmail", "");
        val passwordInDB = sharedPreferences.getString("userPassword", "");

        if(email != emailInDB) {
            Toast
                .makeText(this, "El correo ingresado es incorrecto o no exisite", Toast.LENGTH_SHORT)
                .show();

            return;
        }

        if(password != passwordInDB) {
            Toast
                .makeText(this, "La contraseña ingresada es incorrecta", Toast.LENGTH_SHORT)
                .show();

            return;
        }

        btnLogin.postDelayed({
            Toast
                .makeText(this, "Ingresando...", Toast.LENGTH_SHORT)
                .show();

            // startActivity(
            //     Intent(this, PerfilActivity::class.java)
            // )
            // finish();
        }, 2000);
    }

    fun goToRegisterActivity(view: View?) {
        // startActivity(
        //     Intent(this, RegisterActivity::class.java)
        // );
        // finish();
    }

    fun goToResetPasswordActivity(view: View?) {
        // startActivity(
        //     Intent(this, ResetPasswordActivity::class.java)
        // );
        // finish();
    }

    override fun onStart() {
        super.onStart()

        Log.d("LoginActivity", "onStart: LoginActivity está en segundo plano");
    }

    override fun onResume() {
        super.onResume()

        Log.d("LoginActivity", "onResume: Entro en onResume");
    }

    override fun onStop() {
        super.onStop()

        Log.d("LoginActivity", "onStop: Entro en onStop");
    }

    override fun onPause() {
        super.onPause()

        Log.d("LoginActivity", "onPause: Entro en onPause");
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.d("LoginActivity", "onDestroy: Entro en onDestroy");
    }
}