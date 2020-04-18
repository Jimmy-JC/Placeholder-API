package jimmy.placeholder_api.ui.photos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import jimmy.placeholder_api.R
import jimmy.placeholder_api.base.BaseFragment
import jimmy.placeholder_api.data.model.Photo
import jimmy.placeholder_api.databinding.FragmentPhotosBinding
import jimmy.placeholder_api.ui.navigation.SharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class PhotosFragment : BaseFragment<Photo>(),
    PhotoCheckedListener {

    lateinit var photosBinding: FragmentPhotosBinding
    override val viewModel: PhotosViewModel by viewModel()
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        photosBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_photos, container, false)
        return photosBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedViewModel.albumLiveData.observe(viewLifecycleOwner, Observer {
            photosBinding.album = it
            viewModel.getDataList(it.id)
        })
    }

    override fun onPhotoChecked(photo: Photo, position: Int) {
        if (photo.favourite)
            viewModel.insertPhoto(photo)
        else
            viewModel.deletePhoto(photo)
    }

    override fun displayList(data: List<Photo>?) {
        photosBinding.photoCheckListener = this
        photosBinding.photos = data
    }
}
