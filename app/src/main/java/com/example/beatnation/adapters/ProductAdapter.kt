package com.example.beatnation.adapters

import android.view.LayoutInflater
import com.example.beatnation.utils.CartManager
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.beatnation.R
import com.example.beatnation.model.Product

class ProductAdapter(private val productList: List<Product>) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.productImage)
        val name: TextView = view.findViewById(R.id.productName)
        val rating: TextView = view.findViewById(R.id.productRating)
        val price: TextView = view.findViewById(R.id.productPrice)
        val button: Button = view.findViewById(R.id.addButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        holder.image.setImageResource(product.imageResId)
        holder.name.text = product.name
        holder.rating.text = "${product.rating} DE CALIFICACIONES"
        holder.price.text = "$ ${String.format("%,.0f", product.price)}"

        holder.button.setOnClickListener {
            CartManager.addProduct(product)
            Toast.makeText(
                holder.itemView.context,
                "${product.name} agregado al carrito 🛒",
                Toast.LENGTH_SHORT
            ).show()
        }

    }

    override fun getItemCount(): Int = productList.size
}
