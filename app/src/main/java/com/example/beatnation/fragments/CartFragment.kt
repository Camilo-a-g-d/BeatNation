package com.example.beatnation.fragments

import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.beatnation.R
import com.example.beatnation.adapters.ProductAdapter
import com.example.beatnation.model.Product
import com.example.beatnation.utils.CartManager

class CartFragment : Fragment() {

    // TEMPORAL: productos quemados (en el futuro vendrán de un ViewModel o Singleton)
    private lateinit var cartProducts: List<Product>


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        cartProducts = CartManager.getProducts()
        val recycler = view.findViewById<RecyclerView>(R.id.cartRecycler)
        val totalText = view.findViewById<TextView>(R.id.cartTotal)
        val checkoutBtn = view.findViewById<Button>(R.id.btnCheckout)

        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.adapter = ProductAdapter(cartProducts)

        // 💰 Mostrar total
        cartProducts = CartManager.getProducts()
        val total = CartManager.getTotal()
        totalText.text = "Total: $ ${String.format("%,.0f", total)}"

        checkoutBtn.setOnClickListener {
            Toast.makeText(requireContext(), "Compra finalizada 🎉", Toast.LENGTH_SHORT).show()
        }
    }
}
