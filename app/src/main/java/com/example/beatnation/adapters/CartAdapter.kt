package com.example.beatnation.adapters

import android.view.*
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.beatnation.R
import com.example.beatnation.model.Product

class CartAdapter(
    private val cartList: MutableList<Product>,
    private val onRemove: (Product, Int) -> Unit
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    inner class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.productName)
        val price: TextView = itemView.findViewById(R.id.productPrice)
        val image: ImageView = itemView.findViewById(R.id.productImage)
        val removeButton: ImageButton = itemView.findViewById(R.id.removeButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cart, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val product = cartList[position]
        holder.name.text = product.name
        holder.price.text = "$ ${String.format("%,.0f", product.price)}"
        holder.image.setImageResource(product.imageResId)

        holder.removeButton.setOnClickListener {
            onRemove(product, holder.adapterPosition)
        }
    }

    override fun getItemCount(): Int = cartList.size
}
