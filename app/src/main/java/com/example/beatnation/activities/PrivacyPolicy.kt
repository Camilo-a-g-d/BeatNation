package com.example.beatnation.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.beatnation.R

class PrivacyPolicy : AppCompatActivity() {
    private lateinit var returnToLoginFromPolicy: TextView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.privacy_policy);

        val privacyPolicyWebView = findViewById<WebView>(R.id.privacyPolicyWebView);
        returnToLoginFromPolicy = findViewById(R.id.returnToLoginFromPolicy);

        privacyPolicyWebView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                // Opcional: código a ejecutar cuando la página termine de cargar
            }
        }

        privacyPolicyWebView.loadUrl("file:///android_asset/privacy_policy.html");

        returnToLoginFromPolicy.setOnClickListener {
            startActivity(
                Intent(this, LoginActivity::class.java)
            )
            finish();
        };
    }

    override fun onStart() {
        super.onStart()

        Log.d("PrivacyPolicyActivity", "onStart: PrivacyPolicyActivity está en segundo plano");
    }

    override fun onResume() {
        super.onResume()

        Log.d("PrivacyPolicyActivity", "onResume: Entro en onResume");
    }

    override fun onStop() {
        super.onStop()

        Log.d("PrivacyPolicyActivity", "onStop: Entro en onStop");
    }

    override fun onPause() {
        super.onPause()

        Log.d("PrivacyPolicyActivity", "onPause: Entro en onPause");
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.d("PrivacyPolicyActivity", "onDestroy: Entro en onDestroy");
    }
}