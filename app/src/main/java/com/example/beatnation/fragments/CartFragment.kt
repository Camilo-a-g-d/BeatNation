package com.example.beatnation.fragments

import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.beatnation.R
import com.example.beatnation.adapters.CartAdapter
import com.example.beatnation.model.Product
import com.example.beatnation.utils.CartManager
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.*

class CartFragment : Fragment() {

    private lateinit var adapter: CartAdapter
    private lateinit var cartItems: MutableList<Product>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recycler = view.findViewById<RecyclerView>(R.id.cartRecycler)
        val totalText = view.findViewById<TextView>(R.id.cartTotal)
        val checkoutBtn = view.findViewById<Button>(R.id.btnCheckout)

        // Obtener productos actuales del carrito
        cartItems = CartManager.getProducts().toMutableList()

        adapter = CartAdapter(cartItems) { productToRemove, position ->
            if (position in cartItems.indices) {
                CartManager.removeProduct(productToRemove)
                cartItems.removeAt(position)
                adapter.notifyItemRemoved(position)

                val newTotal = CartManager.getTotal()
                totalText.text = "Total: $ ${String.format("%,.0f", newTotal)}"

                if (cartItems.isEmpty()) {
                    totalText.text = "Tu carrito está vacío 😔"
                }

                Snackbar.make(view, "${productToRemove.name} eliminado", Snackbar.LENGTH_SHORT).show()
            }
        }

        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.adapter = adapter

        val total = CartManager.getTotal()
        totalText.text = "Total: $ ${String.format("%,.0f", total)}"

        checkoutBtn.setOnClickListener {
            val fecha = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(Date())

            // Crear bundle con los datos del pedido
            val bundle = Bundle().apply {
                putParcelableArrayList("products", ArrayList(cartItems))
                putDouble("total", CartManager.getTotal())
                putString("date", fecha)
            }

            // Limpiar carrito
            CartManager.clearCart()
            cartItems.clear()
            adapter.notifyDataSetChanged()
            totalText.text = "Tu carrito está vacío 😌"

            // Navegar al estado de compra
            findNavController().navigate(R.id.purchaseStatusFragment, bundle)
        }
    }
}
