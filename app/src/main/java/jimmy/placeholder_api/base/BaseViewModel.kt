package jimmy.placeholder_api.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import jimmy.placeholder_api.data.network.response.Resource

abstract class BaseViewModel<T> : ViewModel() {

    val compositeDisposable = CompositeDisposable()

    val listLiveData = MutableLiveData<Resource<List<T>>>()

    abstract fun getDataList(id: Int? = null)

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}