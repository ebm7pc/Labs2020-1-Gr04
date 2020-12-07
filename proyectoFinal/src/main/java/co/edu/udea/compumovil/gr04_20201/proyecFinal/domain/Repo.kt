package co.edu.udea.compumovil.gr04_20201.proyecFinal.domain

import co.edu.udea.compumovil.gr04_20201.proyecFinal.model.Product
import co.edu.udea.compumovil.gr04_20201.proyecFinal.valueObjet.Resource


interface Repo {

    fun getProductList(): Resource<List<Product>>



}