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

class PreRegisterActivity : AppCompatActivity() {
    private lateinit var spinnerSelectTypeUser: Spinner;
    private lateinit var btnGoToRegister: Button;
    private lateinit var sharedPreferences: SharedPreferences;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        setContentView(R.layout.pre_register_activity);

        // Inicializar almacenamiento temporal
        sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE);

        // Configurar selector de tipo de usuario
        spinnerSelectTypeUser = findViewById(R.id.spinnerSelectTypeUser);
        ArrayAdapter.createFromResource(
            this,
            R.array.userTypeOptions,
            R.layout.spinner_item_selected
        ).also { adapter ->
            adapter.setDropDownViewResource(R.layout.spinner_dropdown_items);
            spinnerSelectTypeUser.adapter = adapter;
        }

        btnGoToRegister = findViewById(R.id.btnGoToRegister);
        btnGoToRegister.setOnClickListener({
            val selectedItem = spinnerSelectTypeUser.selectedItem.toString();
            val editor = sharedPreferences.edit();

            if(selectedItem == "Fan (Comprador)") {
                editor.putString("typeUser", "Fan");
                editor.apply();
                startActivity(
                    Intent(this, RegisterFansActivity::class.java)
                );
                finish();
            }

            if(selectedItem == "Industria (Vendedor)") {
                editor.putString("typeUser", "Industria");
                editor.apply();
                startActivity(
                    Intent(this, RegisterTypeIndustryActivity::class.java)
                );
                finish();
            }
        });
    }

    override fun onStart() {
        super.onStart()

        Log.d("PreRegisterActivity", "onStart: PreRegisterActivity está en segundo plano");
    }

    override fun onResume() {
        super.onResume()

        Log.d("PreRegisterActivity", "onResume: Entro en onResume");
    }

    override fun onStop() {
        super.onStop()

        Log.d("PreRegisterActivity", "onStop: Entro en onStop");
    }

    override fun onPause() {
        super.onPause()

        Log.d("PreRegisterActivity", "onPause: Entro en onPause");
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.d("PreRegisterActivity", "onDestroy: Entro en onDestroy");
    }
}