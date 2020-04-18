package jimmy.placeholder_api.ui.albums

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import jimmy.placeholder_api.R
import jimmy.placeholder_api.base.BaseFragment
import jimmy.placeholder_api.data.model.Album
import jimmy.placeholder_api.databinding.FragmentAlbumsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class AlbumsFragment : BaseFragment<Album>() {

    lateinit var albumsBinding: FragmentAlbumsBinding
    override val viewModel: AlbumsViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        albumsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_albums, container, false)
        return albumsBinding.root
    }

    override fun displayList(data: List<Album>?) {
        albumsBinding.albums = data
    }
}
