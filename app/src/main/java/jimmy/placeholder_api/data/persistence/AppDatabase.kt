package jimmy.placeholder_api.data.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import jimmy.placeholder_api.data.model.Photo

@Database(entities = [Photo::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun photoDao(): PhotoDao
}