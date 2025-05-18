package com.example.beatnation.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity.MODE_PRIVATE
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.beatnation.R
import com.example.beatnation.adapters.ProductAdapter
import com.example.beatnation.model.Product

class HomeProductsFragment : Fragment() {
    // Propiedades
    private lateinit var sharedPreferences: SharedPreferences;
    private lateinit var userNameInHomeProducts: TextView;

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.home_products_fragment, container, false);

        // Inicializar propiedades
        sharedPreferences = this.requireContext().getSharedPreferences("userInfo", MODE_PRIVATE);
        userNameInHomeProducts = view.findViewById(R.id.userNameInHomeProducts);
        userNameInHomeProducts.setText("${sharedPreferences.getString("userName", "")} (${sharedPreferences.getString("userStoreName", "")})")

        return view;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState);

        val productsRecyclerView = view.findViewById<RecyclerView>(R.id.productsRecyclerViewHomeProducts);
        productsRecyclerView.layoutManager  = GridLayoutManager(requireContext(), 2);

        // Listado de productos iniciales
        val productList  = listOf(
            Product("Guitarra Eléctrica Vintage", 450000.0, 4.8, R.drawable.product_guitarra),
            Product("Camiseta Banda Rock", 60000.0, 4.6, R.drawable.product_camiseta)
        );

        val adapter = ProductAdapter(productList);
        productsRecyclerView.adapter = adapter;
    }
}