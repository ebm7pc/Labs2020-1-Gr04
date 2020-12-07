package co.edu.udea.compumovil.gr04_20201.proyectoFinal.domain

import co.edu.udea.compumovil.gr04_20201.proyectoFinal.model.Product
import co.edu.udea.compumovil.gr04_20201.proyectoFinal.valueObjet.Resource


interface Repo {

    fun getProductList(): Resource<List<Product>>



}