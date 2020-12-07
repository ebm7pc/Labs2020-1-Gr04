package co.edu.udea.compumovil.gr04_20201.proyectoFinal.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "posts")
class Posts_Entity (
    val image: String,
    val price: String,
    val description: String,
    val title: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
): Serializable

