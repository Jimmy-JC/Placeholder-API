package jimmy.placeholder_api.ui.photos

import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import jimmy.placeholder_api.base.BaseViewModel
import jimmy.placeholder_api.data.model.Photo
import jimmy.placeholder_api.data.network.response.Resource
import jimmy.placeholder_api.data.repository.PhotosRepository

@Suppress("UnstableApiUsage")
class PhotosViewModel(private val photosRepository: PhotosRepository) : BaseViewModel<Photo>() {

    fun insertPhoto(photo: Photo) {
        photosRepository.insertPhoto(photo)
    }

    fun deletePhoto(photo: Photo) {
        photosRepository.deletePhoto(photo)
    }

    override fun getDataList(id: Int?) {
        id?.let { albumId ->
            val favouritePhotos = photosRepository.getFavouritePhotos()
            compositeDisposable.add(photosRepository.getAlbumPhotos(albumId)
                .flatMap { Flowable.fromIterable(it) }
                .map { updateFavourite(it, favouritePhotos) }
                .toList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe { listLiveData.value = Resource.loading() }
                .doOnTerminate { listLiveData.value = Resource.finished() }
                .subscribe(
                    { data -> listLiveData.value = Resource.success(data) },
                    { error -> listLiveData.value = Resource.error(error) }
                ))
        }
    }

    private fun updateFavourite(photo: Photo, favouritePhotos: List<Photo>): Photo {
        for (favouritePhoto in favouritePhotos) {
            if (favouritePhoto.id == photo.id)
                photo.favourite = true
        }
        return photo
    }


}