package com.example.beatnation.fragments

import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.beatnation.R
import com.example.beatnation.adapters.ProductAdapter
import com.example.beatnation.model.Product

class CategoryInstrumentsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_category_instruments, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<TextView>(R.id.categoryTitle).text = "Instrumentos musicales"
        view.findViewById<TextView>(R.id.categoryDesc).text = "Desde guitarras hasta equipos de DJ. Encuentra el instrumento que necesitas."
        view.findViewById<ImageView>(R.id.categoryBanner).setImageResource(R.drawable.banner_instruments)

        val recycler = view.findViewById<RecyclerView>(R.id.recyclerCategoryProducts)
        recycler.layoutManager = GridLayoutManager(requireContext(), 2)

        val products = listOf(
            Product("Guitarra eléctrica Pro", 320000.0, 4.8, R.drawable.product_guitarra),
            Product("Controlador MIDI", 150000.0, 4.6, R.drawable.product_midi),
            Product("Audífonos de estudio", 85000.0, 4.7, R.drawable.product_headphones)
        )

        recycler.adapter = ProductAdapter(products)
    }
}
