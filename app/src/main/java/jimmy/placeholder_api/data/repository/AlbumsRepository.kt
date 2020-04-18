package jimmy.placeholder_api.data.repository

import io.reactivex.Flowable
import jimmy.placeholder_api.data.model.Album
import jimmy.placeholder_api.data.network.api.ApiService

class AlbumsRepository(private val api: ApiService) {
    fun getAlbums(): Flowable<List<Album>> = api.getAlbums()
}