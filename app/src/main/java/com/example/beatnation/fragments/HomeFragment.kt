package com.example.beatnation.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity.MODE_PRIVATE
import androidx.fragment.app.Fragment
import com.example.beatnation.R

class HomeFragment : Fragment() {
    // Propiedades
    private lateinit var sharedPreferences: SharedPreferences;
    private lateinit var userNameInHome: TextView;

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.home_fragment, container, false);

        // Inicializar propiedades
        sharedPreferences = this.requireContext().getSharedPreferences("userInfo", MODE_PRIVATE);
        userNameInHome = view.findViewById(R.id.userNameInHome);
        val typeUser = sharedPreferences.getString("typeUser", "");
        val typeIndsutryUser = sharedPreferences.getString("typeIndustryUser", "");
        var userNameFormat = "";

        if(typeUser.equals("Fan")) {
            userNameFormat = "${sharedPreferences.getString("userName", "")} ${sharedPreferences.getString("userLastname", "")}"
            userNameInHome.setText(userNameFormat);
        } else {

            if(typeIndsutryUser.toString() == "Comerciante") {
                userNameFormat = "${sharedPreferences.getString("userName", "")} (${sharedPreferences.getString("userStoreName", "")})";
            } else {
                userNameFormat = "${sharedPreferences.getString("userName", "")} (${sharedPreferences.getString("userNameArtist", "")})";
            }

            userNameInHome.setText(userNameFormat);
        }

        return view;
    }
}