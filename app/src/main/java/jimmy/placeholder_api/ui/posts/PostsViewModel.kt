package jimmy.placeholder_api.ui.posts

import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import jimmy.placeholder_api.base.BaseViewModel
import jimmy.placeholder_api.data.model.Post
import jimmy.placeholder_api.data.network.response.Resource
import jimmy.placeholder_api.data.repository.PostsRepository

@Suppress("UnstableApiUsage")
class PostsViewModel(private val postsRepository: PostsRepository) : BaseViewModel<Post>() {

    override fun getDataList(id: Int?) {
        compositeDisposable.add(postsRepository.getPosts()
            .flatMap { Flowable.fromIterable(it) }
            .take(10)
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