package co.edu.udea.compumovil.gr04_20201.proyecFinal.repository

import androidx.lifecycle.LiveData
import co.edu.udea.compumovil.gr04_20201.proyecFinal.domain.PostsService
import co.edu.udea.compumovil.gr04_20201.proyecFinal.domain.ProductDao
import co.edu.udea.compumovil.gr04_20201.proyecFinal.model.Product
import co.edu.udea.compumovil.gr04_20201.proyecFinal.model.asCacheModel

class PostsRepository (
    private val postDao: ProductDao,
    private val apiService: PostsService
) {
    var allPosts: LiveData<List<Product>> = postDao.getAll()

    suspend fun deleteAll() {
        postDao.deleteAll()
    }

    suspend fun insert(post: Product) {
        postDao.insert(post)
    }

    suspend fun refreshPost() {
        val postResponse = apiService.requestPosts()
        postDao.insertPosts(postResponse.asCacheModel())
    }
}




