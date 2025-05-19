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

class CategoryPostersFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_category_posters, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<TextView>(R.id.categoryTitle).text = "Posters"
        view.findViewById<TextView>(R.id.categoryDesc).text = "Decora tu espacio con tus artistas y géneros favoritos."
        view.findViewById<ImageView>(R.id.categoryBanner).setImageResource(R.drawable.banner_posters)

        val recycler = view.findViewById<RecyclerView>(R.id.recyclerCategoryProducts)
        recycler.layoutManager = GridLayoutManager(requireContext(), 2)

        val products = listOf(
            Product("Poster edición Maluma", 30000.0, 4.6, R.drawable.product_poster_maluma),
            Product("Beat Nation colección", 25000.0, 4.7, R.drawable.product_poster_beat)
        )

        recycler.adapter = ProductAdapter(products)
    }
}
