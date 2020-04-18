package jimmy.placeholder_api.ui.navigation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import jimmy.placeholder_api.data.model.Album
import jimmy.placeholder_api.data.model.Post

class SharedViewModel : ViewModel() {
    val postLiveData = MutableLiveData<Post>()
    val albumLiveData = MutableLiveData<Album>()
}