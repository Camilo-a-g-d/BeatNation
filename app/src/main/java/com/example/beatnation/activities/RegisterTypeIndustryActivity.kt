package com.example.beatnation.activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.beatnation.R

class RegisterTypeIndustryActivity : AppCompatActivity() {
    private lateinit var spinnerSelectTypeIndustry: Spinner;
    private lateinit var userEmailIndustry: EditText;
    private lateinit var userPasswordIndustry: EditText;
    private lateinit var btnRegisterUserIndustry: Button;
    private lateinit var btnGoToLogin: Button;
    private lateinit var sharedPreferences: SharedPreferences;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.register_type_industry_activity);

        // Inicializar propiedades
        userEmailIndustry = findViewById(R.id.inputUserEmailIndustry);
        userPasswordIndustry = findViewById(R.id.inputUserPasswordIndustry);
        sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE);
        btnGoToLogin = findViewById(R.id.btnGoToLoginFromRegisterIndustry);

        // Configurar selector de tipo de industria
        spinnerSelectTypeIndustry = findViewById(R.id.spinnerTypeIndustry);
        ArrayAdapter.createFromResource(
            this,
            R.array.userTypeIndustryOptions,
            R.layout.spinner_item_selected
        ).also { adapter ->
            adapter.setDropDownViewResource(R.layout.spinner_dropdown_items);
            spinnerSelectTypeIndustry.adapter = adapter;
        }

        btnRegisterUserIndustry = findViewById(R.id.btnRegisterIndustry);

        btnRegisterUserIndustry.setOnClickListener {
            val typeIndustryUserSelected = spinnerSelectTypeIndustry.selectedItem.toString();
            val editor = sharedPreferences.edit();

            if(typeIndustryUserSelected == "Comerciante") {
                editor.putString("typeIndustryUser", "Comerciante");
                editor.apply();
            }

            if(typeIndustryUserSelected == "Artista") {
                editor.putString("typeIndustryUser", "Artista");
                editor.apply();
            }

            if(validateForm()) {
                preSaveDataUser();
            }
        };

        btnGoToLogin.setOnClickListener {
            startActivity(
                Intent(this, LoginActivity::class.java)
            );
            finish();
        };
    }

    private fun validateForm(): Boolean {
        val userEmailFanValue = userEmailIndustry.text.toString().trim();
        val userPasswordValue = userPasswordIndustry.text.toString().trim();

        if(userEmailFanValue.isEmpty()) {
            Toast
                .makeText(this, "Debes ingresar el correo electrónico para continuar", Toast.LENGTH_SHORT)
                .show();

            return false;
        }

        if(userPasswordValue.isEmpty()) {
            Toast
                .makeText(this, "Debes ingresar la contraseña para continuar", Toast.LENGTH_SHORT)
                .show();

            return false;
        }

//        if(!isValidPassword(userPasswordValue)) {
//            Toast
//                .makeText(this, "La contraseña no cumple con los requisitos mínimos", Toast.LENGTH_SHORT)
//                .show();
//
//            return false;
//        }

        return true;
    }

    private fun preSaveDataUser() {
        val editor = sharedPreferences.edit();

        editor.putString("userEmail", userEmailIndustry.text.toString().trim());
        editor.putString("userPassword", userPasswordIndustry.text.toString().trim());

        editor.apply();

        startActivity(
            Intent(this, MainRegisterActivity::class.java)
        );
        finish();
    }

    private fun isValidPassword(password: String): Boolean {
        val passwordPattern = "^(?=(.*[A-Z]){1,})(?=(.*[a-z]){1,})(?=(.*[!@#$%^&*(),.?\":{}|<>]){1,})(?=(.*\\d){3,4}).{10}$".toRegex();
        return passwordPattern.matches(password);
    }

    override fun onStart() {
        super.onStart()

        Log.d("RegisterTypeIndustryActivity", "onStart: RegisterTypeIndustryActivity está en segundo plano");
    }

    override fun onResume() {
        super.onResume()

        Log.d("RegisterTypeIndustryActivity", "onResume: Entro en onResume");
    }

    override fun onStop() {
        super.onStop()

        Log.d("RegisterTypeIndustryActivity", "onStop: Entro en onStop");
    }

    override fun onPause() {
        super.onPause()

        Log.d("RegisterTypeIndustryActivity", "onPause: Entro en onPause");
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.d("RegisterTypeIndustryActivity", "onDestroy: Entro en onDestroy");
    }
}