package jimmy.placeholder_api.data.repository

import jimmy.placeholder_api.data.model.Photo
import jimmy.placeholder_api.data.network.api.ApiService
import jimmy.placeholder_api.data.persistence.PhotoDao

class PhotosRepository(private val api: ApiService) {

    private lateinit var photoDao: PhotoDao

    constructor(api: ApiService, photoDao: PhotoDao) : this(api) {
        this.photoDao = photoDao
    }

    fun insertPhoto(photo: Photo) = photoDao.insertPhoto(photo)

    fun deletePhoto(photo: Photo) = photoDao.deletePhoto(photo)

    fun getFavouritePhotos() = photoDao.getPhotos()

    fun getAlbumPhotos(id: Int) = api.getAlbumPhotos(id)

}