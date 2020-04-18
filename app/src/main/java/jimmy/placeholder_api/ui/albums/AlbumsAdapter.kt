package jimmy.placeholder_api.ui.albums

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import jimmy.placeholder_api.R
import jimmy.placeholder_api.data.model.Album
import jimmy.placeholder_api.databinding.AlbumItemLayoutBinding
import jimmy.placeholder_api.ui.comments.CommentsFragment
import jimmy.placeholder_api.ui.navigation.NavigationActivity
import jimmy.placeholder_api.ui.navigation.OpenFragmentType
import jimmy.placeholder_api.ui.photos.PhotosFragment

class AlbumsAdapter(private val albums: List<Album>) :
    RecyclerView.Adapter<AlbumsAdapter.AlbumsHolder>() {

    private lateinit var activity: NavigationActivity

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumsHolder {
        val itemBinding: AlbumItemLayoutBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.album_item_layout, parent, false
        )
        return AlbumsHolder(itemBinding)
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        activity = recyclerView.context as NavigationActivity
    }

    override fun getItemCount() = albums.size

    override fun onBindViewHolder(holder: AlbumsHolder, position: Int) {
        holder.itemBinding.album = albums[position]
        holder.itemBinding.setClickListener {
            activity.sharedViewModel.albumLiveData.value = albums[position]
            activity.openFragment(fragment = PhotosFragment(), type = OpenFragmentType.ADD)
        }
    }

    class AlbumsHolder(val itemBinding: AlbumItemLayoutBinding) :
        RecyclerView.ViewHolder(itemBinding.root)
}