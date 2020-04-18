package jimmy.placeholder_api.ui.photos

import jimmy.placeholder_api.data.model.Photo

interface PhotoCheckedListener {
    fun onPhotoChecked(photo: Photo, position: Int)
}