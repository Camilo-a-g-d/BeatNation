package com.example.beatnation.activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.beatnation.R

class MainRegisterActivity : AppCompatActivity() {
    private lateinit var userName: EditText;
    private lateinit var userLastname: EditText;
    private lateinit var userEmail: EditText;
    private lateinit var userPhoneNumber: EditText;
    private lateinit var userCountry: EditText;
    private lateinit var userCity: EditText;
    private lateinit var userStoreName: EditText;
    private lateinit var userStoreNit: EditText;
    private lateinit var userStoreAddress: EditText;
    private lateinit var userNameArtist: EditText;
    private lateinit var spinnerTypeMuscialGenre: Spinner;
    private lateinit var userArtistBiography: EditText;
    private lateinit var sharedPreferences: SharedPreferences;
    private lateinit var typeInstrustyUserView: TextView
    private lateinit var moreInfoLabel: TextView;
    private lateinit var btnRegisterNewUser: Button;
    private var typeGenreSelected = "";
    private var typeGenreIndexSelected = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.main_register_activity);

        //Inicializar propiedades
        userName = findViewById(R.id.UserNameInputMainRegisterActivity);
        userLastname = findViewById(R.id.UserLastnameMainRegisterActivity);
        userEmail = findViewById(R.id.UserEmailMainRegisterActivity);
        userPhoneNumber = findViewById(R.id.UserPhoneNumberMainRegisterActivity);
        userCountry = findViewById(R.id.UserCountryMainRegisterActivity);
        userCity = findViewById(R.id.UserCityMainRegisterActivity);
        userStoreName = findViewById(R.id.UserStoreNameMainRegisterActivity);
        userStoreNit = findViewById(R.id.UserStoreNitMainRegisterActivity);
        userStoreAddress = findViewById(R.id.UserStoreAddressMainRegisterActivity);
        userNameArtist = findViewById(R.id.UserNameArtistMainRegisterActivity);
        spinnerTypeMuscialGenre = findViewById(R.id.spinnerTypeMuscialGenre);
        userArtistBiography = findViewById(R.id.UserArtistBiographyMainRegisterActivity);
        typeInstrustyUserView = findViewById(R.id.typeInstrustyUser);
        moreInfoLabel = findViewById(R.id.moreInfoLabelRegister);
        btnRegisterNewUser = findViewById(R.id.btnContinueMainRegisterActivity);
        sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE);
        val typeUser = sharedPreferences.getString("typeUser", "");
        val typeIndsutryUser = sharedPreferences.getString("typeIndustryUser", "");

        // Mostrar el email pre registrado
        userEmail.setText(sharedPreferences.getString("userEmail", ""));

        // Configurar selector de tipo de género musical
        ArrayAdapter.createFromResource(
            this,
            R.array.UserTypeMuscialGenre,
            R.layout.spinner_item_selected
        ).also { adapter ->
            adapter.setDropDownViewResource(R.layout.spinner_dropdown_items);
            spinnerTypeMuscialGenre.adapter = adapter;
        }

        // Manejador del Spinner de los géneros musicales
        spinnerTypeMuscialGenre.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                typeGenreIndexSelected = position;

                if (position == 0) {
                    Toast
                        .makeText(this@MainRegisterActivity, R.string.noItemSelecedValid, Toast.LENGTH_LONG)
                        .show();
                } else {
                    typeGenreSelected = parent.getItemAtPosition(position).toString();

                    Toast
                        .makeText(this@MainRegisterActivity, "Genero seleccionado: " + typeGenreSelected, Toast.LENGTH_SHORT)
                        .show();
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        if(typeUser.equals("Fan")) {
            // Ocultar los campos para los usuarios compradores
            typeInstrustyUserView.visibility = View.GONE;
            moreInfoLabel.visibility = View.GONE;
            userStoreName.visibility = View.GONE;
            userStoreNit.visibility = View.GONE;
            userStoreAddress.visibility = View.GONE;
            userNameArtist.visibility = View.GONE;
            spinnerTypeMuscialGenre.visibility = View.GONE;
            userArtistBiography.visibility = View.GONE;
        } else {
            typeInstrustyUserView.visibility = View.VISIBLE;
            typeInstrustyUserView.text = typeIndsutryUser.toString();
            moreInfoLabel.visibility = View.VISIBLE;

            if(typeIndsutryUser.toString() == "Comerciante") {
                // Mostrar los campos del comerciante
                userStoreName.visibility = View.VISIBLE;
                userStoreNit.visibility = View.VISIBLE;
                userStoreAddress.visibility = View.VISIBLE;

                // Ocultar los campos del artista
                userNameArtist.visibility = View.GONE;
                spinnerTypeMuscialGenre.visibility = View.GONE;
                userArtistBiography.visibility = View.GONE;
            } else {
                // Mostrar los campos del artista
                userNameArtist.visibility = View.VISIBLE;
                spinnerTypeMuscialGenre.visibility = View.VISIBLE;
                userArtistBiography.visibility = View.VISIBLE;

                // Ocultar los campos del comerciante
                userStoreName.visibility = View.GONE;
                userStoreNit.visibility = View.GONE;
                userStoreAddress.visibility = View.GONE;
            }
        }

        btnRegisterNewUser.setOnClickListener {
            if(validateForm()) {
                saveNewUser();
            }
        }
    }

    private fun validateForm(): Boolean {
        val userNameValue = userName.text.trim();
        val userLastnameValue = userLastname.text.trim();
        val userPhoneNumberValue = userPhoneNumber.text.trim();
        val userCountryValue = userCountry.text.trim();
        val userCityValue = userCity.text.trim();
        val userStoreNameValue = userStoreName.text.trim();
        val userStoreNitValue = userStoreNit.text.trim();
        val userStoreAddressValue = userStoreAddress.text.trim();
        val userNameArtistValue = userNameArtist.text.trim();
        val userArtistBiographyValue = userArtistBiography.text.trim();

        if(userNameValue.isEmpty()) {
            Toast
                .makeText(this, "Debes ingresar tu nombre para continuar", Toast.LENGTH_SHORT)
                .show();

            return false;
        }

        if(userLastnameValue.isEmpty()) {
            Toast
                .makeText(this, "Debes ingresar tus apellidos para continuar", Toast.LENGTH_SHORT)
                .show();

            return false;
        }

        if(userPhoneNumberValue.isEmpty()) {
            Toast
                .makeText(this, "Debes ingresar tu número de celular para continuar", Toast.LENGTH_SHORT)
                .show();

            return false;
        }

        if(userCountryValue.isEmpty()) {
            Toast
                .makeText(this, "Debes ingresar tu país de origen para continuar", Toast.LENGTH_SHORT)
                .show();

            return false;
        }

        if(userCityValue.isEmpty()) {
            Toast
                .makeText(this, "Debes ingresar tu ciudad de nacimiento para continuar", Toast.LENGTH_SHORT)
                .show();

            return false;
        }

        // Validar los campos del comerciante si es necesario
        if(typeInstrustyUserView.text.toString() == "Comerciante") {
            if(userStoreNameValue.isEmpty()) {
                Toast
                    .makeText(this, "Debes ingresar el nombre de la tienda para continuar", Toast.LENGTH_SHORT)
                    .show();

                return false;
            }

            if(userStoreNitValue.isEmpty()) {
                Toast
                    .makeText(this, "Debes ingresar el NIT de la tienda para continuar", Toast.LENGTH_SHORT)
                    .show();

                return false;
            }

            if(userStoreAddressValue.isEmpty()) {
                Toast
                    .makeText(this, "Debes ingresar la dirección de la tienda para continuar", Toast.LENGTH_SHORT)
                    .show();

                return false;
            }
        }

        // Validar los campos de los artistas si es necesario
        if(typeInstrustyUserView.text.toString() == "Artista") {
            if(userNameArtistValue.isEmpty()) {
                Toast
                    .makeText(this, "Debes ingresar tu nombre artístico o el de la banda/grupo para continuar", Toast.LENGTH_SHORT)
                    .show();

                return false;
            }

            if(typeGenreIndexSelected == 0) {
                Toast
                    .makeText(this, "El género seleccionado no es valido", Toast.LENGTH_SHORT)
                    .show();

                return false;
            }

            if(userArtistBiographyValue.isEmpty()) {
                Toast
                    .makeText(this, "Debes ingresar una biografía descriptiva para continuar", Toast.LENGTH_SHORT)
                    .show();

                return false;
            }
        }

        return true;
    }

    private fun saveNewUser() {
        val editor = sharedPreferences.edit();

        editor.putString("userName", userName.text.toString().trim());
        editor.putString("userLastname", userLastname.text.toString().trim());
        editor.putString("userPhoneNumber", userPhoneNumber.text.toString().trim());
        editor.putString("userCountry", userCountry.text.toString().trim());
        editor.putString("userCity", userCity.text.toString().trim());
        editor.putString("userStoreName", userStoreName.text.toString().trim());
        editor.putString("userStoreNit", userStoreNit.text.toString().trim());
        editor.putString("userStoreAddress", userStoreAddress.text.toString().trim());
        editor.putString("userNameArtist", userNameArtist.text.toString().trim());
        editor.putString("userGenreArtist", typeGenreSelected)
        editor.putString("userArtistBiography", userArtistBiography.text.toString().trim());

        editor.apply();

        Toast
            .makeText(this, "Registro completado con éxito", Toast.LENGTH_SHORT)
            .show();

        Handler(mainLooper).postDelayed({
            startActivity(
                Intent(
                    this, MainActivity::class.java
                )
            );
            finish();
        }, 1800)
    }

    override fun onStart() {
        super.onStart()

        Log.d("MainRegisterActivity", "onStart: MainRegisterActivity está en segundo plano");
    }

    override fun onResume() {
        super.onResume()

        Log.d("MainRegisterActivity", "onResume: Entro en onResume");
    }

    override fun onStop() {
        super.onStop()

        Log.d("MainRegisterActivity", "onStop: Entro en onStop");
    }

    override fun onPause() {
        super.onPause()

        Log.d("MainRegisterActivity", "onPause: Entro en onPause");
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.d("MainRegisterActivity", "onDestroy: Entro en onDestroy");
    }
}