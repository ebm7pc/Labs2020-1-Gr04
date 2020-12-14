package co.edu.udea.compumovil.gr04_20201.proyectoFinal.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import co.edu.udea.compumovil.gr04_20201.proyectoFinal.R
import co.edu.udea.compumovil.gr04_20201.proyectoFinal.ShoppingList
import co.edu.udea.compumovil.gr04_20201.proyectoFinal.model.Product
import co.edu.udea.compumovil.gr04_20201.proyectoFinal.ui.viewmodel.PostViewModel
import kotlinx.android.synthetic.main.fragment_product.*
import kotlinx.android.synthetic.main.fragment_shopping_cart.*


class ShoppingCartFragment : Fragment(),MainAdapter.OnProductClickListener  {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_shopping_cart, container, false)
    }

    private lateinit var viewModel: PostViewModel
    private lateinit var postAdapter: MainAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PostViewModel::class.java)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()

        Total1.text = ShoppingList.Singleton.shoppingList.sumBy { it.price.toInt() }.toString()
        floatingActionDelete.setOnClickListener {
            ShoppingList.Singleton.shoppingList.clear()
            postAdapter.updatePostList(ShoppingList.Singleton.shoppingList)
            Total1.text = "0"
        }
        val url =
            "https://www.pse.com.co/persona?utm_source=omd_digital&utm_medium=google_search&utm_term=categoria&utm_content=keywords&utm_campaign=ach_pse_12_trafico_search_cpc&gclid=CjwKCAiAt9z-BRBCEiwA_bWv-C4dVjA92nSPbeY_Hidv4C-m8ZhAUlGq0UHNyzkZE1BXQzfyeX94JxoCReAQAvD_BwE "
        btn_pagos.setOnClickListener {
            val gmmIntentUri = Uri.parse(url)
            val intent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            startActivity(intent)
        }
    }

    private fun setupRecyclerView() {
        rv_cartproduct.layoutManager = LinearLayoutManager(requireContext())
        rv_cartproduct.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
        postAdapter = MainAdapter(this as MainAdapter.OnProductClickListener)
        postAdapter.updatePostList(ShoppingList.Singleton.shoppingList)
        rv_cartproduct.adapter = postAdapter
    }

    override fun OnProductClick(product: Product) {
        val bundle = Bundle()
        bundle.putParcelable("productTable", product)
        findNavController().navigate(R.id.detailsProductFragment, bundle)
    }
}
