package jimmy.placeholder_api.data.repository

import io.reactivex.Flowable
import jimmy.placeholder_api.data.model.Comment
import jimmy.placeholder_api.data.network.api.ApiService

class CommentsRepository(private val api: ApiService) {
    fun getPostComments(id: Int): Flowable<List<Comment>> = api.getPostComments(id)
}