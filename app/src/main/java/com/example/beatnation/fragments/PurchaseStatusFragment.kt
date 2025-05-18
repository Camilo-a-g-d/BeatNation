package com.example.beatnation.fragments

import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.core.os.BundleCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.beatnation.R
import com.example.beatnation.adapters.CartAdapter
import com.example.beatnation.model.Product

class PurchaseStatusFragment : Fragment() {

    private var products: List<Product> = emptyList()
    private var total: Double = 0.0
    private var date: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_purchase_status, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recycler = view.findViewById<RecyclerView>(R.id.purchaseRecycler)
        val totalText = view.findViewById<TextView>(R.id.purchaseTotal)
        val dateText = view.findViewById<TextView>(R.id.purchaseDate)
        val statusText = view.findViewById<TextView>(R.id.purchaseStatus)

        //  Protección total: evitar crashes por nulls o casting
        try {
            arguments?.let { bundle ->
                products = BundleCompat.getParcelableArrayList(bundle, "products", Product::class.java) ?: emptyList()
                total = bundle.getDouble("total", 0.0)
                date = bundle.getString("date") ?: ""
            }
        } catch (e: Exception) {
            products = emptyList()
            total = 0.0
            date = ""
        }

        recycler.layoutManager = LinearLayoutManager(requireContext())

        if (products.isNotEmpty()) {
            recycler.visibility = View.VISIBLE
            totalText.visibility = View.VISIBLE
            dateText.visibility = View.VISIBLE

            recycler.adapter = CartAdapter(products.toMutableList()) { _, _ -> }

            totalText.text = "Total pagado: $ ${String.format("%,.0f", total)}"
            dateText.text = "Fecha de compra: $date"
            statusText.text = "Estado: 🟢 Pagado"
        } else {
            //  Evitar mostrar elementos vacíos
            recycler.visibility = View.GONE
            totalText.visibility = View.GONE
            dateText.visibility = View.GONE
            statusText.text = "No hay compras registradas aún 😔"
        }
    }
}
