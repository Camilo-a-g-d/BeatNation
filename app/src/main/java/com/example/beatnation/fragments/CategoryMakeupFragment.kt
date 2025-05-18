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

class CategoryMakeupFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_category_makeup, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<TextView>(R.id.categoryTitle).text = "Maquillaje"
        view.findViewById<TextView>(R.id.categoryDesc).text = "Expresa tu estilo con paletas, labiales y más."
        view.findViewById<ImageView>(R.id.categoryBanner).setImageResource(R.drawable.banner_makeup)

        val recycler = view.findViewById<RecyclerView>(R.id.recyclerCategoryProducts)
        recycler.layoutManager = GridLayoutManager(requireContext(), 2)

        val products = listOf(
            Product("Paleta colores oscuros", 96000.0, 4.9, R.drawable.product_maquillaje),
            Product("Labial Beat Nation", 32000.0, 4.5, R.drawable.product_labial)
        )

        recycler.adapter = ProductAdapter(products)
    }
}
