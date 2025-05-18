package com.example.beatnation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.beatnation.R
import com.example.beatnation.adapters.ProductAdapter
import com.example.beatnation.model.Product

class CategoryClothingFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_category_clothing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Referencias a vistas
        val banner: ImageView = view.findViewById(R.id.categoryBanner)
        val title: TextView = view.findViewById(R.id.categoryTitle)
        val desc: TextView = view.findViewById(R.id.categoryDesc)
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerCategoryProducts)

        // Datos estáticos (pueden venir de servidor después)
        title.text = "Productos de Ropa"
        desc.text = "Explora prendas de vestir con estilo urbano, comodidad y esencia artística para todos los gustos."
        banner.setImageResource(R.drawable.banner_ropa) // Debe existir en drawable

        val clothingProducts = listOf(
            Product(
                name = "Camiseta Negra - Beat Nation",
                price = 65550.0,
                rating = 4.4,
                imageResId = R.drawable.product_camiseta
            ),
            Product(
                name = "Chaqueta Estilo Urbano",
                price = 120000.0,
                rating = 4.7,
                imageResId = R.drawable.product_chaqueta
            ),
            Product(
                name = "Sudadera Oversize Artística",
                price = 98000.0,
                rating = 4.5,
                imageResId = R.drawable.product_sudadera
            ),
            Product(
                name = "Gorra Snapback Beat",
                price = 45000.0,
                rating = 4.3,
                imageResId = R.drawable.product_gorra
            )
        )

        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        recyclerView.adapter = ProductAdapter(clothingProducts)
    }
}
