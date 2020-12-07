package co.edu.udea.compumovil.gr04_20201.proyectoFinal.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Parcelize
@Entity(tableName = "productTable")
data class Product (
    val name: String = "",
    val image: String ="",
    val description: String = "",
    val price: String = "",
    val localization: String = "",
    @PrimaryKey(autoGenerate = true)
    val idProduct: Int= 0
): Parcelable