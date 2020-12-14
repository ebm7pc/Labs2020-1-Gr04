package co.edu.udea.compumovil.gr04_20201.proyectoFinal.valueObjet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import co.edu.udea.compumovil.gr04_20201.proyectoFinal.R
import co.edu.udea.compumovil.gr04_20201.proyectoFinal.base.AppDatabase
import co.edu.udea.compumovil.gr04_20201.proyectoFinal.model.Product
import kotlinx.android.synthetic.main.fragment_register_product.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class
RegisterProductFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_register_product, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val database = this.context?.let {
            AppDatabase.getDatabase(
                it,
                this.viewLifecycleOwner.lifecycleScope
            )
        }
        btn_save.setOnClickListener {
            val name = name_et.text.toString()
            val image = img_et.text.toString()
            val description = description_et.text.toString()
            val price = price_et.text.toString()

            if (name_et.text.isEmpty() || img_et.text.isEmpty() || description_et.text.isEmpty()) {
                Toast.makeText(
                    requireContext(),
                    "Por favor diligencie todos los campos",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                val productos = Product(name, image, description, price)
                Toast.makeText(requireContext(), "Nuevo registro guardado", Toast.LENGTH_SHORT)
                    .show()

                CoroutineScope(Dispatchers.IO).launch {
                    if (database != null) {
                        database.product().insertAll(productos)
                    }
                }
                findNavController().navigate(R.id.productFragment)
            }
        }
    }
}

