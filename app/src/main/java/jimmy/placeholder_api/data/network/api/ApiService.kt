package jimmy.placeholder_api.data.network.api

import io.reactivex.Flowable
import jimmy.placeholder_api.data.model.Album
import jimmy.placeholder_api.data.model.Comment
import jimmy.placeholder_api.data.model.Photo
import jimmy.placeholder_api.data.model.Post
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("posts")
    fun getPosts(): Flowable<List<Post>>

    @GET("posts/{post_id}/comments")
    fun getPostComments(@Path("post_id") id: Int): Flowable<List<Comment>>

    @GET("albums")
    fun getAlbums(): Flowable<List<Album>>

    @GET("albums/{album_id}/photos")
    fun getAlbumPhotos(@Path("album_id") id: Int): Flowable<List<Photo>>
}