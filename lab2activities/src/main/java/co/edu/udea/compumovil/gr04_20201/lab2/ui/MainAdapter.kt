package co.edu.udea.compumovil.gr04_20201.lab2.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.edu.udea.compumovil.gr04_20201.lab2.R
import co.edu.udea.compumovil.gr04_20201.lab2.base.BaseViewHolder
import co.edu.udea.compumovil.gr04_20201.lab2.data.model.Sitios
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_lugares.*
import kotlinx.android.synthetic.main.sitios_row.view.*

class MainAdapter(private val context: Context,private val sitiosList:List<Sitios>,
                  private val itemClickLister: MainAdapter.OnSitioClickListener) :
    RecyclerView.Adapter<BaseViewHolder<*>>(){

    interface OnSitioClickListener{
        fun OnSitioClick(sitios: Sitios)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return MainViewHolder(LayoutInflater.from(context).inflate(R.layout.sitios_row,parent,false))
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder){
            is MainViewHolder -> holder.bind(sitiosList[position],position)
        }
    }

    override fun getItemCount(): Int {
        return sitiosList.size
    }

    inner class MainViewHolder(itemView: View): BaseViewHolder<Sitios>(itemView){
        override fun bind(item: Sitios, position: Int) {
            Glide.with(context).load(item.imagen).centerCrop().into(itemView.img_sitio)
            itemView.txt_titulo.text = item.nombre
            itemView.txt_descripcion.text = item.descripcion
            itemView.setOnClickListener { itemClickLister.OnSitioClick(item)}
        }


    }


}