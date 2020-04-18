package jimmy.placeholder_api.ui.favourites

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import jimmy.placeholder_api.data.model.Photo
import jimmy.placeholder_api.data.repository.PhotosRepository

class FavouritesViewModel(private val photosRepository: PhotosRepository): ViewModel() {

    val photosLiveData = MutableLiveData<List<Photo>>()

    fun getFavouritePhotos() {
        photosLiveData.value = photosRepository.getFavouritePhotos()
    }

    fun deleteFavouritePhoto(photo: Photo) {
        photosRepository.deletePhoto(photo)
    }
}