package co.edu.udea.compumovil.gr04_20121.Lab3Architecture.base

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import carlose.morales.udea.roomsqlite.Interface.UserDao
import co.edu.udea.compumovil.gr04_20121.Lab3Architecture.domain.ProductDao
import co.edu.udea.compumovil.gr04_20121.Lab3Architecture.model.Product
import co.edu.udea.compumovil.gr04_20121.Lab3Architecture.model.UserEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Product::class, UserEntity::class], version = 4)
abstract class AppDatabase : RoomDatabase() {
    abstract fun product(): ProductDao
    abstract fun UserDao(): UserDao

    companion object {
        private var INSTANCE: AppDatabase? = null
        fun getDatabase(context: Context, scope: CoroutineScope): AppDatabase {
            val tempInstance =
                INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).addCallback(PostDatabaseCallback(scope)).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }
    }

    private class PostDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.product())
                }
            }
        }

        suspend fun populateDatabase(postDao: ProductDao) {

        }
    }
}
