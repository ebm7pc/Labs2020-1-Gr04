package co.edu.udea.compumovil.gr04_20201.proyectoFinal.domain

import co.edu.udea.compumovil.gr04_20201.proyectoFinal.model.DataSource
import co.edu.udea.compumovil.gr04_20201.proyectoFinal.model.Product
import co.edu.udea.compumovil.gr04_20201.proyectoFinal.valueObjet.Resource

class RepoImpl(private val dataSource: DataSource): Repo {
    override fun getProductList(): Resource<List<Product>> {
        return dataSource.generateProductList
    }
}