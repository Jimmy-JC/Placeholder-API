package jimmy.placeholder_api.ui.comments

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import jimmy.placeholder_api.base.BaseViewModel
import jimmy.placeholder_api.data.model.Comment
import jimmy.placeholder_api.data.network.response.Resource
import jimmy.placeholder_api.data.repository.CommentsRepository

class CommentsViewModel(private val commentsRepository: CommentsRepository) : BaseViewModel<Comment>() {

    override fun getDataList(id: Int?) {
        id?.let { postId ->
            compositeDisposable.add(commentsRepository.getPostComments(postId)
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
}