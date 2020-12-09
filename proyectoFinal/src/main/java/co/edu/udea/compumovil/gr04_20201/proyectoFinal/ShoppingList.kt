package co.edu.udea.compumovil.gr04_20201.proyectoFinal

import co.edu.udea.compumovil.gr04_20201.proyectoFinal.model.Product
import okhttp3.internal.Internal.instance

public class ShoppingList {

    object Singleton {
        var shoppingList: MutableList<Product> = mutableListOf()
    }



}


