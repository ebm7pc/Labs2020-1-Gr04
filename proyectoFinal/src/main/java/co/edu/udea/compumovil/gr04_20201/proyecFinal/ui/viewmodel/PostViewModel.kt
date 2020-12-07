package co.edu.udea.compumovil.gr04_20201.proyecFinal.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import co.edu.udea.compumovil.gr04_20201.proyecFinal.base.AppDatabase
import co.edu.udea.compumovil.gr04_20201.proyecFinal.domain.RetrofitFactory
import co.edu.udea.compumovil.gr04_20201.proyecFinal.model.Product
import co.edu.udea.compumovil.gr04_20201.proyecFinal.repository.PostsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PostViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: PostsRepository
    var allPosts: LiveData<List<Product>> = MutableLiveData()

    init {
        val dao = AppDatabase.getDatabase(application, viewModelScope).product()
        val apiService = RetrofitFactory.postService()
        repository = PostsRepository(dao, apiService)
        allPosts = repository.allPosts
    }

    fun requestPosts() {
        viewModelScope.launch {
            requestRemotePosts()
        }
    }

    private suspend fun requestRemotePosts() {
        return withContext(Dispatchers.IO) {
            repository.refreshPost()
        }
    }

    fun deletePosts() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAll()
    }
}