package com.example.beatnation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.beatnation.R
import com.example.beatnation.adapters.ProductAdapter
import com.example.beatnation.model.Product

class GenreDetailFragment : Fragment() {

    private lateinit var genre: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        genre = arguments?.getString("genre") ?: "Pop"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_genre_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bannerImage = view.findViewById<ImageView>(R.id.bannerGenre)
        val genreTitle = view.findViewById<TextView>(R.id.titleGenre)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerGenreProducts)

        genreTitle.text = "Bienvenido a tu $genre"

        val bannerResId = when (genre.lowercase()) {
            "pop" -> R.drawable.pop_genre
            "rock" -> R.drawable.rock_genre
            "rap" -> R.drawable.rap_genre
            "edm" -> R.drawable.edm_genre
            "reggaetón" -> R.drawable.reggaeton_genre
            "k-pop" -> R.drawable.kk_pop_genre
            else -> R.drawable.pop_genre
        }
        bannerImage.setImageResource(bannerResId)

        val products = when (genre.lowercase()) {
            "pop" -> listOf(
                Product("Camiseta Negra – Marca Artista", 65550.0, 4.4, R.drawable.product_camiseta),
                Product("Maquillaje paleta de colores oscuros", 96000.0, 4.9, R.drawable.product_maquillaje)
            )
            "rock" -> listOf(
                Product("Guitarra Eléctrica Vintage", 450000.0, 4.8, R.drawable.product_guitarra),
                Product("Camiseta Banda Rock", 60000.0, 4.6, R.drawable.product_camiseta)
            )
            "rap" -> listOf(
                Product("Hoodie estilo urbano", 80000.0, 4.5, R.drawable.product_hoodie),
                Product("Cadena dorada", 125000.0, 4.7, R.drawable.product_chain)
            )
            "edm" -> listOf(
                Product("Luces LED para escenario", 150000.0, 4.8, R.drawable.product_lights),
                Product("Camiseta electrónica", 50000.0, 4.4, R.drawable.product_camiseta)
            )
            "reggaetón" -> listOf(
                Product("Gorra urbana edición limitada", 45000.0, 4.6, R.drawable.product_hat),
                Product("Camiseta urbana", 55000.0, 4.4, R.drawable.product_camiseta)
            )
            "k-pop" -> listOf(
                Product("Lightstick oficial", 135000.0, 4.9, R.drawable.product_lightstick),
                Product("Posters K-pop edición limitada", 30000.0, 4.5, R.drawable.product_posters)
            )
            else -> emptyList()
        }

        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        recyclerView.adapter = ProductAdapter(products)

        val backButton = view.findViewById<Button>(R.id.backButton)
        backButton.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }


    }
}
