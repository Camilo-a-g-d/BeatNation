package com.example.beatnation.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.beatnation.R

class ProductViewModel : ViewModel() {
    val products = MutableLiveData<MutableList<Product>>()

    init {
        // Inicializar con dos productos por defecto
        products.value = mutableListOf(
            Product("Guitarra Eléctrica Vintage", 450000.0, 4.8, R.drawable.product_guitarra),
            Product("Camiseta Banda Rock", 60000.0, 4.6, R.drawable.product_camiseta),
            Product("CD | HIM - Razorblade Romance", 145000.0, 5.0, R.drawable.him_razorblade_romace)
            );
    }

    fun addProduct(product: Product) {
        val currentList = products.value ?: mutableListOf();
        currentList.add(product);
        products.value = currentList;
    }
}