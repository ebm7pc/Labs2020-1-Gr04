package co.edu.udea.compumovil.gr04_20121.Lab3Architecture.repository

import androidx.lifecycle.LiveData
import co.edu.udea.compumovil.gr04_20121.Lab3Architecture.domain.PostsService
import co.edu.udea.compumovil.gr04_20121.Lab3Architecture.domain.ProductDao
import co.edu.udea.compumovil.gr04_20121.Lab3Architecture.model.Product
import co.edu.udea.compumovil.gr04_20121.Lab3Architecture.model.asCacheModel

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




