package com.example.beatnation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class PreRegisterActivity : AppCompatActivity() {
    private lateinit var spinnerSelectTypeUser: Spinner;
    private lateinit var btnGoToRegisterFanTEMP: Button;
    private lateinit var btnGoToRegisterIndustryTEMP: Button;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        setContentView(R.layout.pre_register_activity);

        // Configurar selector de tipo de usuario
        spinnerSelectTypeUser = findViewById(R.id.spinnerSelectTypeUser);
//        ArrayAdapter.createFromResource(
//            this,
//            R.array.userTypeOptions,
//            R.layout.pre_register_activity
//        ).also { adapter ->
//            adapter.setDropDownViewResource(R.layout.pre_register_activity);
//            spinnerSelectTypeUser.adapter = adapter;
//        }

        btnGoToRegisterFanTEMP = findViewById(R.id.btnGoToRegister);
        btnGoToRegisterFanTEMP.setOnClickListener({
            startActivity(
                Intent(this, RegisterFansActivity::class.java)
            );
            finish();
        });

        btnGoToRegisterIndustryTEMP = findViewById(R.id.btnGoToRegisterIndustry);
        btnGoToRegisterIndustryTEMP.setOnClickListener({
            startActivity(
                Intent(this, RegisterTypeIndustryActivity::class.java)
            );
            finish();
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