package com.example.beatnation.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity.MODE_PRIVATE
import androidx.fragment.app.Fragment
import com.example.beatnation.R

class ProfileFragment : Fragment() {
    // Propiedades generales
    private lateinit var sharedPreferences: SharedPreferences;

    // Propiedades para el manejo del formulario
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
    private lateinit var typeInstrustyUserView: TextView
    private lateinit var moreInfoLabel: TextView;
    private lateinit var btnEditProfile: Button;
    private var typeGenreSelected = "";
    private var typeGenreIndexSelected = 0;

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.profile_fragment, container, false);

        //Inicializar propiedades
        userName = view.findViewById(R.id.UserNameInputProfileFragment);
        userLastname = view.findViewById(R.id.UserLastnameProfileFragment);
        userEmail = view.findViewById(R.id.UserEmailProfileFragment);
        userPhoneNumber = view.findViewById(R.id.UserPhoneNumberProfileFragment);
        userCountry = view.findViewById(R.id.UserCountryProfileFragment);
        userCity = view.findViewById(R.id.UserCityProfileFragment);
        userStoreName = view.findViewById(R.id.UserStoreNameProfileFragment);
        userStoreNit = view.findViewById(R.id.UserStoreNitProfileFragment);
        userStoreAddress = view.findViewById(R.id.UserStoreAddressProfileFragment);
        userNameArtist = view.findViewById(R.id.UserNameArtistProfileFragment);
        spinnerTypeMuscialGenre = view.findViewById(R.id.spinnerTypeMuscialGenreProfileFragment);
        userArtistBiography = view.findViewById(R.id.UserArtistBiographyProfileFragment);
        typeInstrustyUserView = view.findViewById(R.id.typeInstrustyUserProfileFragment);
        moreInfoLabel = view.findViewById(R.id.moreInfoLabelRegisterProfileFragment);
        btnEditProfile = view.findViewById(R.id.btnContinueProfileFragment);
        sharedPreferences = this.requireContext().getSharedPreferences("userInfo", MODE_PRIVATE);
        val typeUser = sharedPreferences.getString("typeUser", "");
        val typeIndsutryUser = sharedPreferences.getString("typeIndustryUser", "");

        // Mostrar información del usuario
        userName.setText(sharedPreferences.getString("userName", ""));
        userLastname.setText(sharedPreferences.getString("userLastname", ""));
        userLastname.setText(sharedPreferences.getString("userLastname", ""));
        userEmail.setText(sharedPreferences.getString("userEmail", ""));
        userPhoneNumber.setText(sharedPreferences.getString("userPhoneNumber", ""));
        userCountry.setText(sharedPreferences.getString("userCountry", ""));
        userCity.setText(sharedPreferences.getString("userCity", ""));
        userCity.setText(sharedPreferences.getString("userCity", ""));
        typeGenreSelected = sharedPreferences.getString("userGenreArtist", "").toString();

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

                userStoreName.setText(sharedPreferences.getString("userStoreName", ""));
                userStoreNit.setText(sharedPreferences.getString("userStoreNit", ""));
                userStoreAddress.setText(sharedPreferences.getString("userStoreAddress", ""));

                // Ocultar los campos del artista
                userNameArtist.visibility = View.GONE;
                spinnerTypeMuscialGenre.visibility = View.GONE;
                userArtistBiography.visibility = View.GONE;
            } else {
                // Mostrar los campos del artista
                userNameArtist.visibility = View.VISIBLE;
                spinnerTypeMuscialGenre.visibility = View.VISIBLE;
                userArtistBiography.visibility = View.VISIBLE;

                userNameArtist.setText(sharedPreferences.getString("userNameArtist", ""));
                userArtistBiography.setText(sharedPreferences.getString("userArtistBiography", ""));

                // Ocultar los campos del comerciante
                userStoreName.visibility = View.GONE;
                userStoreNit.visibility = View.GONE;
                userStoreAddress.visibility = View.GONE;
            }
        }

        // Configurar selector de tipo de género musical
        ArrayAdapter.createFromResource(
            this.requireContext(),
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
                        .makeText(this@ProfileFragment.requireContext(), R.string.noItemSelecedValid, Toast.LENGTH_LONG)
                        .show();
                } else {
                    typeGenreSelected = parent.getItemAtPosition(position).toString();

                    Toast
                        .makeText(this@ProfileFragment.requireContext(), "Genero seleccionado: " + typeGenreSelected, Toast.LENGTH_SHORT)
                        .show();
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        // Establecer el genero si hay
        if(typeGenreSelected != "") {
            val genreArray = resources.getStringArray(R.array.UserTypeMuscialGenre);
            val index = genreArray.indexOf(typeGenreSelected);
            if (index >= 0) {
                spinnerTypeMuscialGenre.setSelection(index);
            }
        }

        btnEditProfile.setOnClickListener {
            if(validateForm()) {
                saveNewUser();
            }
        }

        return view;
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
                .makeText(this@ProfileFragment.requireContext(), "Debes ingresar tu nombre para continuar", Toast.LENGTH_SHORT)
                .show();

            return false;
        }

        if(userLastnameValue.isEmpty()) {
            Toast
                .makeText(this@ProfileFragment.requireContext(), "Debes ingresar tus apellidos para continuar", Toast.LENGTH_SHORT)
                .show();

            return false;
        }

        if(userPhoneNumberValue.isEmpty()) {
            Toast
                .makeText(this@ProfileFragment.requireContext(), "Debes ingresar tu número de celular para continuar", Toast.LENGTH_SHORT)
                .show();

            return false;
        }

        if(userCountryValue.isEmpty()) {
            Toast
                .makeText(this@ProfileFragment.requireContext(), "Debes ingresar tu país de origen para continuar", Toast.LENGTH_SHORT)
                .show();

            return false;
        }

        if(userCityValue.isEmpty()) {
            Toast
                .makeText(this@ProfileFragment.requireContext(), "Debes ingresar tu ciudad de nacimiento para continuar", Toast.LENGTH_SHORT)
                .show();

            return false;
        }

        // Validar los campos del comerciante si es necesario
        if(typeInstrustyUserView.text.toString() == "Comerciante") {
            if(userStoreNameValue.isEmpty()) {
                Toast
                    .makeText(this@ProfileFragment.requireContext(), "Debes ingresar el nombre de la tienda para continuar", Toast.LENGTH_SHORT)
                    .show();

                return false;
            }

            if(userStoreNitValue.isEmpty()) {
                Toast
                    .makeText(this@ProfileFragment.requireContext(), "Debes ingresar el NIT de la tienda para continuar", Toast.LENGTH_SHORT)
                    .show();

                return false;
            }

            if(userStoreAddressValue.isEmpty()) {
                Toast
                    .makeText(this@ProfileFragment.requireContext(), "Debes ingresar la dirección de la tienda para continuar", Toast.LENGTH_SHORT)
                    .show();

                return false;
            }
        }

        // Validar los campos de los artistas si es necesario
        if(typeInstrustyUserView.text.toString() == "Artista") {
            if(userNameArtistValue.isEmpty()) {
                Toast
                    .makeText(this@ProfileFragment.requireContext(), "Debes ingresar tu nombre artístico o el de la banda/grupo para continuar", Toast.LENGTH_SHORT)
                    .show();

                return false;
            }

            if(typeGenreIndexSelected == 0) {
                Toast
                    .makeText(this@ProfileFragment.requireContext(), "El género seleccionado no es valido", Toast.LENGTH_SHORT)
                    .show();

                return false;
            }

            if(userArtistBiographyValue.isEmpty()) {
                Toast
                    .makeText(this@ProfileFragment.requireContext(), "Debes ingresar una biografía descriptiva para continuar", Toast.LENGTH_SHORT)
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
            .makeText(this@ProfileFragment.requireContext(), "Perfil editado correctamente", Toast.LENGTH_SHORT)
            .show();
    }
}