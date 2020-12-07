package co.edu.udea.compumovil.gr04_20201.proyecFinal.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import co.edu.udea.compumovil.gr04_20201.proyecFinal.domain.Repo
import co.edu.udea.compumovil.gr04_20201.proyecFinal.valueObjet.Resource
import kotlinx.coroutines.Dispatchers
import kotlin.Exception

class MainViewModel(private val repo: Repo) : ViewModel() {

    val fetchPlacesList = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(repo.getProductList())
        } catch (e: Exception) {

        }
    }
}