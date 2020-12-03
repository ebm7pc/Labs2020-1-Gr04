package co.edu.udea.compumovil.gr04_20121.Lab3Architecture.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.edu.udea.compumovil.gr04_20121.Lab3Architecture.R
import co.edu.udea.compumovil.gr04_20121.Lab3Architecture.model.Product
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
        txt_temperature.text = product.temperature

        bte_localization.setOnClickListener {
            val gmmIntentUri = Uri.parse("geo:0,0?q=city+" + product.name)
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            startActivity(mapIntent)
        }
        val url =
            "https://www.google.com/search?ei=6IOLX8D8HIOG5wKfoYXwDA&q=Atracciones+destacadas+en " + product.name
        bte_places_recommended.setOnClickListener {
            val gmmIntentUri = Uri.parse(url)
            val intent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            startActivity(intent)
        }
    }
}