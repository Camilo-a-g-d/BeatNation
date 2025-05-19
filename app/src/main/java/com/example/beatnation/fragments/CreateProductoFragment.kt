package com.example.beatnation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.beatnation.R
import com.example.beatnation.model.Product
import com.example.beatnation.model.ProductViewModel

class CreateProductoFragment : Fragment() {
    private val productViewModel: ProductViewModel by activityViewModels();
    private lateinit var nameInput: EditText;
    private lateinit var priceInput: EditText;
    private lateinit var ratingInput: EditText;
    private lateinit var createButton: Button;

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.create_product_fragment, container, false);
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState);

        nameInput = view.findViewById(R.id.nameNewProductInput);
        priceInput = view.findViewById(R.id.priceNewProductInput);
        ratingInput = view.findViewById(R.id.ratingNewProductInput);
        createButton = view.findViewById(R.id.btnCreateNewProduct);

        createButton.setOnClickListener {
            val name = nameInput.text.toString();
            val priceStr = priceInput.text.toString();
            val rating = ratingInput.text.toString();

            if (name.isNotEmpty() && priceStr.isNotEmpty() && rating.isNotEmpty()) {
                val price = priceStr.toDoubleOrNull();

                if (price != null) {
                    val newProduct = Product(name, price, rating.toDouble(), R.drawable.new_product_default_img);

                    // Añadir el producto al ViewModel
                    productViewModel.addProduct(newProduct)

                    // Opcional: Puedes navegar de vuelta a la lista de productos aquí
                    findNavController().navigate(R.id.homeProductsFragmentOption);
                } else {
                    Toast
                        .makeText(requireContext(), "Debes ingresar el precio del producto", Toast.LENGTH_SHORT)
                        .show();
                }
            } else {
                Toast
                    .makeText(requireContext(), "Todos los campos son obligatorios", Toast.LENGTH_SHORT)
                    .show();
            }
        }
    }
}