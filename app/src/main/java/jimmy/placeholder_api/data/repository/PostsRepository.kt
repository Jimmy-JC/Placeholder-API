package jimmy.placeholder_api.data.repository

import io.reactivex.Flowable
import jimmy.placeholder_api.data.model.Post
import jimmy.placeholder_api.data.network.api.ApiService

class PostsRepository(private val api: ApiService) {
    fun getPosts(): Flowable<List<Post>> = api.getPosts()
}