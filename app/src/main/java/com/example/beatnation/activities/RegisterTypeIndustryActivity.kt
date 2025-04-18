package com.example.beatnation.activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.example.beatnation.R

class RegisterTypeIndustryActivity : AppCompatActivity() {
    private lateinit var spinnerSelectTypeIndustry: Spinner;
    private lateinit var btnRegisterUserIndustry: Button;
    private lateinit var sharedPreferences: SharedPreferences;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.register_type_industry_activity);

        // Inicializar almacenamiento temporal
        sharedPreferences = getSharedPreferences("registerInfo", MODE_PRIVATE);

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
        btnRegisterUserIndustry.setOnClickListener{
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

            startActivity(
                Intent(this, MainRegisterActivity::class.java)
            )
            finish();
        }
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