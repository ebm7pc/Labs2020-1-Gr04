package co.edu.udea.compumovil.gr04_20201.proyecFinal.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.edu.udea.compumovil.gr04_20201.proyecFinal.R
import co.edu.udea.compumovil.gr04_20201.proyecFinal.base.BaseViewHolder
import co.edu.udea.compumovil.gr04_20201.proyecFinal.model.Product
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.sitios_row.view.*

class MainAdapter(
                  private val itemClickLister: MainAdapter.OnProductClickListener) :
    RecyclerView.Adapter<BaseViewHolder<*>>(){

    private var ProductList = mutableListOf<Product>()

    interface OnProductClickListener{
        fun OnProductClick(product: Product)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return MainViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.sitios_row,parent,false))
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder){
            is MainViewHolder -> holder.bind(ProductList[position],position)
        }
    }

    override fun getItemCount(): Int {
        return ProductList.size
    }

    inner class MainViewHolder(itemView: View): BaseViewHolder<Product>(itemView){
        override fun bind(item: Product, position: Int) {
            Glide.with(itemView.context).load(item.image).centerCrop().into(itemView.img_product)
            itemView.txt_title.text = item.name
            itemView.txt_description.text = item.description
            itemView.setOnClickListener { itemClickLister.OnProductClick(item)}
        }

    }
    fun updatePostList(posts: List<Product>?) {
        this.ProductList.clear()
        posts?.let { this.ProductList.addAll(it) }
        notifyDataSetChanged()
    }
}