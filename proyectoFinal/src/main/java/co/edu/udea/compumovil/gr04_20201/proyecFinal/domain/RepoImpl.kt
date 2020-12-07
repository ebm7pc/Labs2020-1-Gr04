package co.edu.udea.compumovil.gr04_20201.proyecFinal.domain

import co.edu.udea.compumovil.gr04_20201.proyecFinal.model.DataSource
import co.edu.udea.compumovil.gr04_20201.proyecFinal.model.Product
import co.edu.udea.compumovil.gr04_20201.proyecFinal.valueObjet.Resource

class RepoImpl(private val dataSource: DataSource): Repo {
    override fun getProductList(): Resource<List<Product>> {
        return dataSource.generateProductList
    }
}