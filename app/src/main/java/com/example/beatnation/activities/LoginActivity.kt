package com.example.beatnation.activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.beatnation.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task

class LoginActivity : AppCompatActivity() {
    // Propiedades del formulario
    private lateinit var inputTextEmail: EditText;
    private lateinit var inputTextPassword: EditText;
    private lateinit var btnLogin: Button;
    private lateinit var btnGoToPreRegister: Button;
    private lateinit var btnLoginWithGoogle: Button;
    private lateinit var resetPasswordLabel: TextView;
    private lateinit var googleSignInClient: GoogleSignInClient;
    private var RC_SIGN_IN = 123;
    private var TAG = "GoogleSignIn";

    // Propiedades para manejar información de forma local
    private lateinit var sharedPreferences: SharedPreferences;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.login_activity);

        // Inicializar almacenamiento temporal
        sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE);

        // Inicializar variables
        inputTextEmail = findViewById(R.id.inputUserEmailLogin);
        inputTextPassword = findViewById(R.id.inputPasswordLogin);
        resetPasswordLabel = findViewById(R.id.resetPasswordLabel);
        btnLogin = findViewById(R.id.btnLogin);
        btnGoToPreRegister = findViewById(R.id.btnGoToPreRegister);
        btnLoginWithGoogle = findViewById(R.id.btnLoginWithGoogle);

        val GSO = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .requestProfile()
            .build();

        // Crear cliente de Google SigIn
        googleSignInClient = GoogleSignIn.getClient(this, GSO);

        btnLoginWithGoogle.setOnClickListener {
            signInWithGoogle();
        }

        btnLogin.setOnClickListener {
             if(validateForm()) {
                 validateUser();
             }
        };

        btnGoToPreRegister.setOnClickListener {
            startActivity(
                Intent(this, PreRegisterActivity::class.java)
            )
            finish();
        };

        resetPasswordLabel.setOnClickListener {
            startActivity(
                Intent(this, ForgotPasswordActivity::class.java)
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

        Toast
            .makeText(this, "Ingresando...", Toast.LENGTH_SHORT)
            .show();

        btnLogin.postDelayed({
             startActivity(
                 Intent(this, MainActivity::class.java)
             )
             finish();
        }, 2000);
    }

    private fun signInWithGoogle() {
        val signInIntent = googleSignInClient.signInIntent;
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInWithGoogleResult(task);
        }
    }

    private fun handleSignInWithGoogleResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java);

            // Inicio de sesión exitoso
            Log.d(TAG, "SignIn success: ${account.email}");
            Toast
                .makeText(this, "Inicio de sesión con Google exitoso", Toast.LENGTH_SHORT)
                .show();

            // Ir al MainActivity
            intent = Intent(this, MainActivity::class.java);
            intent.putExtra("USER_EMAIL", account.email);
            intent.putExtra("USER_NAME", account.displayName);
            startActivity(intent);
        } catch (e: ApiException) {
            Log.e(TAG, "signInResult:failed code=${e.statusCode}")

            val mensaje = when(e.statusCode) {
                10 -> "Error de configuración. Verifica la huella SHA-1."
                12500 -> "Error con Google Play Services."
                12501 -> "Inicio de sesión cancelado por el usuario."
                else -> "Error al iniciar sesión (Código: ${e.statusCode})"
            }

            Toast
                .makeText(this, mensaje, Toast.LENGTH_SHORT)
                .show();
        }
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