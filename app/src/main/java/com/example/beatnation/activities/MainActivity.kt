package com.example.beatnation.activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.beatnation.R
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    // Propiedades del menú de navegación lateral
    private lateinit var navController: NavController;
    private lateinit var appBarConfiguration: AppBarConfiguration;
    private lateinit var drawerLayout: DrawerLayout;
    private lateinit var toolbar: Toolbar;
    private lateinit var drawerToggle: ActionBarDrawerToggle;
    private lateinit var sharedPreferences: SharedPreferences;
    private lateinit var navView: NavigationView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE);

        // Inicializar toolbar
        toolbar = findViewById(R.id.mainToolBar);
        setSupportActionBar(toolbar);

        // Configurar controlador de fragments
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment;
        navController = navHostFragment.navController;

        // Inicializar drawer layout
        drawerLayout = findViewById(R.id.mainActivity);
        val navView = findViewById<NavigationView>(R.id.mainNavMenu);

        // Inicializar Toogle
        drawerToggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.open_drawer,
            R.string.close_drawer
        );

        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        // Configurar rutas del navegador
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragmentOption
            ),
            drawerLayout
        );

        setupActionBarWithNavController(navController, appBarConfiguration);
        navView.setupWithNavController(navController);

        // Ajustar el menú y la navegación inicial según el tipo de usuario
        val typeUser = sharedPreferences.getString("typeUser", "");
        val homeProductsItem = navView.menu.findItem(R.id.homeProductsFragmentOption);
//        homeProductsItem.isVisible = false;

        if (typeUser == "Industria") {
            homeProductsItem.isVisible = true;
            navController.navigate(R.id.homeProductsFragmentOption);
        } else {
            homeProductsItem.isVisible = false;
        }

        // Listener para los items del menú de navegación
        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.homeFragmentOption -> {
                    navController.navigate(R.id.homeFragmentOption)
                }
                R.id.homeProductsFragmentOption -> {
                    navController.navigate(R.id.homeProductsFragmentOption)
                }
                R.id.profileFragmentOption -> {
                    navController.navigate(R.id.profileFragmentOption)
                }
                R.id.storeAddressFragmentOption -> {
                    navController.navigate(R.id.storeAddressFragmentOption)
                }
                R.id.categoryClothingFragmentOption -> {
                    navController.navigate(R.id.categoryClothingFragmentOption)
                }
                R.id.categoryInstrumentsFragmentOption -> {
                    navController.navigate(R.id.categoryInstrumentsFragmentOption)
                }
                R.id.categoryAccessoriesFragmentOption -> {
                    navController.navigate(R.id.categoryAccessoriesFragmentOption)
                }
                R.id.categoryMakeupFragmentOption -> {
                    navController.navigate(R.id.categoryMakeupFragmentOption)
                }
                R.id.categoryPostersFragmentOption -> {
                    navController.navigate(R.id.categoryPostersFragmentOption)
                }
                R.id.cartFragmentOption -> {
                    navController.navigate(R.id.cartFragmentOption)
                }
                R.id.purchaseStatusFragment -> navController.navigate(R.id.purchaseStatusFragment)
                R.id.logoutOption -> {
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }

            drawerLayout.closeDrawer(navView)
            true
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp();
    }

    override fun onStart() {
        super.onStart()

        Log.d("MainActivity", "onStart: MainActivity está en segundo plano");
    }

    override fun onResume() {
        super.onResume()

        Log.d("MainActivity", "onResume: Entro en onResume");
    }

    override fun onStop() {
        super.onStop()

        Log.d("MainActivity", "onStop: Entro en onStop");
    }

    override fun onPause() {
        super.onPause()

        Log.d("MainActivity", "onPause: Entro en onPause");
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.d("MainActivity", "onDestroy: Entro en onDestroy");
    }
}