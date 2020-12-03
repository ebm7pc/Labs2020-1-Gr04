package co.edu.udea.compumovil.gr04_20121.Lab3Architecture.domain

import androidx.lifecycle.LiveData
import androidx.room.*
import co.edu.udea.compumovil.gr04_20121.Lab3Architecture.model.Product


@Dao
interface ProductDao {
    @Query("SELECT * FROM  productTable")
    fun getAll(): LiveData<List<Product>>

    @Query("SELECT * FROM productTable WHERE idProduct = :id")
    fun get(id: Int): LiveData<Product>

    @Insert
    fun insertAll(vararg productTable: Product)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(post: Product)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPosts(posts: List<Product>)

    @Query("DELETE FROM productTable")
    suspend fun deleteAll()

    @Update
    fun update(product: Product)

}