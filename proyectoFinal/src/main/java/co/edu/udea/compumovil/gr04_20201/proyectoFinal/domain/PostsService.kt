package co.edu.udea.compumovil.gr04_20201.proyectoFinal.domain

import co.edu.udea.compumovil.gr04_20201.proyectoFinal.model.PostResponse
import retrofit2.http.GET

interface PostsService {
    @GET("posts")
    suspend fun requestPosts(): List<PostResponse>
}