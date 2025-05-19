package com.example.beatnation.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity.MODE_PRIVATE
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.beatnation.R
import com.example.beatnation.adapters.ProductAdapter
import com.example.beatnation.model.ProductViewModel

class HomeProductsFragment : Fragment() {
    // Propiedades
    private lateinit var sharedPreferences: SharedPreferences;
    private lateinit var userNameInHomeProducts: TextView;
    private lateinit var btnGoToCreateProduct: Button;
    private val productViewModel: ProductViewModel by activityViewModels();
    private lateinit var adapter: ProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("HomeProductsFragment", "Se entro a HomeProductsFragment");
        return inflater.inflate(R.layout.home_products_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState);

        // Inicializar propiedades
        sharedPreferences = this.requireContext().getSharedPreferences("userInfo", MODE_PRIVATE);
        userNameInHomeProducts = view.findViewById(R.id.userNameInHomeProducts);
        btnGoToCreateProduct = view.findViewById(R.id.addNewProduct);
        userNameInHomeProducts.setText("${sharedPreferences.getString("userName", "")} (${sharedPreferences.getString("userStoreName", "")})")

        Log.d("HomeProductsFragment", "Vista creada correctamente");

        val productsRecyclerView = view.findViewById<RecyclerView>(R.id.productsRecyclerViewHomeProducts);
        productsRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2);

        adapter = ProductAdapter(emptyList());
        productsRecyclerView.adapter = adapter;

        // Observa los cambios en la lista de productos del ViewModel
        productViewModel.products.observe(viewLifecycleOwner) { productList ->
            adapter = ProductAdapter(productList, "HomeProductsFragment");
            productsRecyclerView.adapter = adapter
        }

        btnGoToCreateProduct.setOnClickListener {
            findNavController().navigate(R.id.createNewProductFragment);
        }
    }
}