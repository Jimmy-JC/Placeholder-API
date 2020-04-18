package jimmy.placeholder_api.data.persistence

import androidx.room.*
import jimmy.placeholder_api.data.model.Photo

@Dao
interface PhotoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPhoto(photo: Photo)

    @Query("SELECT * FROM Photo")
    fun getPhotos(): List<Photo>

    @Delete
    fun deletePhoto(photo: Photo)
}