package jimmy.placeholder_api.ui.favourites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import jimmy.placeholder_api.R
import jimmy.placeholder_api.data.model.Photo
import jimmy.placeholder_api.databinding.FragmentFavouritesBinding
import jimmy.placeholder_api.ui.photos.PhotoCheckedListener
import jimmy.placeholder_api.ui.photos.PhotosAdapter
import kotlinx.android.synthetic.main.fragment_favourites.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavouritesFragment : Fragment(), PhotoCheckedListener {

    lateinit var favouriteBinding: FragmentFavouritesBinding
    private val viewModel: FavouritesViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        favouriteBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_favourites, container, false)
        return favouriteBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.getFavouritePhotos()
        viewModel.photosLiveData.observe(viewLifecycleOwner,
            Observer {
                displayPhotos(it)
                switchViews(it.isNotEmpty())
            })
    }

    private fun displayPhotos(photos: List<Photo>) {
        favouriteBinding.photoCheckListener = this
        favouriteBinding.photos = photos
    }

    private fun switchViews(isNotEmpty: Boolean) {
        if (isNotEmpty) {
            not_found.visibility = View.GONE
            favourite_recycler.visibility = View.VISIBLE
        } else {
            not_found.visibility = View.VISIBLE
            favourite_recycler.visibility = View.GONE
        }
    }

    override fun onPhotoChecked(photo: Photo, position: Int) {
        if (!photo.favourite) {
            viewModel.deleteFavouritePhoto(photo)
            val listSize = getPhotoAdapter().removePhoto(position)
            if (listSize == 0) switchViews(false)
        }
    }

    private fun getPhotoAdapter(): PhotosAdapter {
        return favouriteBinding.favouriteRecycler.adapter as PhotosAdapter
    }
}
