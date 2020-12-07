package co.edu.udea.compumovil.gr04_20201.proyecFinal.model

import com.google.gson.annotations.SerializedName

data class PostResponse(

    @SerializedName("description")
    val description: String,

    @SerializedName("id")
    val id: Int,

    @SerializedName("title")
    val title: String,

    @SerializedName("image")
    val image: String,

    @SerializedName("price")
     val price: String
)

fun List<PostResponse>.asCacheModel(): List<Product> {
    return map {
        Product(
            idProduct = it.id,
            name = it.title,
            image = it.image,
            description = it.description,
            price = it.price
        )
    }
}