package co.edu.udea.compumovil.gr04_20201.proyectoFinal.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import co.edu.udea.compumovil.gr04_20201.proyectoFinal.R
import co.edu.udea.compumovil.gr04_20201.proyectoFinal.model.Product
import co.edu.udea.compumovil.gr04_20201.proyectoFinal.ui.viewmodel.PostViewModel
import kotlinx.android.synthetic.main.fragment_product.*

class ProductFragment : Fragment(), MainAdapter.OnProductClickListener {

    private lateinit var viewModel: PostViewModel
    private lateinit var postAdapter: MainAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PostViewModel::class.java)
        viewModel.allPosts.observe(viewLifecycleOwner, Observer {
            postAdapter.updatePostList(it)
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)

        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_product, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupSearchView()

        btn_insert_product.setOnClickListener {
            findNavController().navigate(R.id.registerProductFragment)
        }

    }

    private fun setupRecyclerView() {
        rv_product.layoutManager = LinearLayoutManager(requireContext())
        rv_product.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
        postAdapter = MainAdapter(this as MainAdapter.OnProductClickListener)
        rv_product.adapter = postAdapter
    }

    override fun OnProductClick(product: Product) {
        val bundle = Bundle()
        bundle.putParcelable("productTable", product)
        findNavController().navigate(R.id.detailsProductFragment, bundle)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_post_list, menu)
        inflater.inflate(R.menu.menu_cart, menu)
    }

    private fun setupSearchView(){
        searchView.setOnQueryTextListener(object:SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.allPosts.observe(viewLifecycleOwner, Observer {
                    postAdapter.updatePostList(it.filter{product -> product.name.equals(query,true)})
                })
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.mnCart) {
            goToCart()
        }

        return when (item.itemId) {
            R.id.action_refresh -> {
                Log.d("ProductFragment", "Action refresh")
                viewModel.requestPosts()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun goToCart() {
        findNavController().navigate(R.id.shoppingCartFragment)    }
}