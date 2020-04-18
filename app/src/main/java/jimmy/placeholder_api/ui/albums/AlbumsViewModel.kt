package jimmy.placeholder_api.ui.albums

import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import jimmy.placeholder_api.base.BaseViewModel
import jimmy.placeholder_api.data.model.Album
import jimmy.placeholder_api.data.network.response.Resource
import jimmy.placeholder_api.data.repository.AlbumsRepository

@Suppress("UnstableApiUsage")
class AlbumsViewModel(private val albumsRepository: AlbumsRepository) : BaseViewModel<Album>() {

    override fun getDataList(id: Int?) {
        compositeDisposable.add(albumsRepository.getAlbums()
            .flatMap { Flowable.fromIterable(it) }
            .take(10)
            .toList()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnSubscribe { listLiveData.value = Resource.loading() }
            .doOnTerminate { listLiveData.value = Resource.finished() }
            .subscribe(
                { albums -> listLiveData.value = Resource.success(albums) },
                { error -> listLiveData.value = Resource.error(error) }
            ))
    }
}