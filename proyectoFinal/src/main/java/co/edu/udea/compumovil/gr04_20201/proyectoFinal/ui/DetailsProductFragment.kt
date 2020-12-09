package co.edu.udea.compumovil.gr04_20201.proyectoFinal.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.edu.udea.compumovil.gr04_20201.proyectoFinal.R
import co.edu.udea.compumovil.gr04_20201.proyectoFinal.ShoppingList
import co.edu.udea.compumovil.gr04_20201.proyectoFinal.model.Product
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_details_product.*

class DetailsProductFragment : Fragment() {
    private lateinit var product: Product

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireArguments().let {
            product = it.getParcelable("productTable")!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details_product, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(requireContext()).load(product.image).into(img_place)
        txt_title.text = product.name
        txt_description.text = product.description
        txt_price.text = product.price

        bte_addcart.setOnClickListener {
            ShoppingList.Singleton.shoppingList.add(product)
        }

    }
}