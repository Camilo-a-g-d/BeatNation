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

class CategoryAccessoriesFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_category_accessories, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<TextView>(R.id.categoryTitle).text = "Accesorios"
        view.findViewById<TextView>(R.id.categoryDesc).text = "Dale el toque final a tu outfit con nuestros accesorios únicos."
        view.findViewById<ImageView>(R.id.categoryBanner).setImageResource(R.drawable.banner_accessories)

        val recycler = view.findViewById<RecyclerView>(R.id.recyclerCategoryProducts)
        recycler.layoutManager = GridLayoutManager(requireContext(), 2)

        val products = listOf(
            Product("Cadena dorada", 120000.0, 4.5, R.drawable.product_chain),
            Product("Pulsera personalizada", 30000.0, 4.3, R.drawable.product_pulsera)
        )

        recycler.adapter = ProductAdapter(products)
    }
}
