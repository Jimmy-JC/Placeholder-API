package jimmy.placeholder_api.ui.photos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import jimmy.placeholder_api.R
import jimmy.placeholder_api.data.model.Photo
import jimmy.placeholder_api.dialogs.ImageDialog
import kotlinx.android.synthetic.main.photo_item_layout.view.*

class PhotosAdapter(
    private val photos: MutableList<Photo>,
    private val photoCheckedListener: PhotoCheckedListener?
) :
    RecyclerView.Adapter<PhotosAdapter.PhotosHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosHolder = PhotosHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.photo_item_layout, parent, false)
    )

    override fun getItemCount() = photos.size

    override fun onBindViewHolder(holder: PhotosHolder, position: Int) {
        holder.bind(photos[position], photoCheckedListener, position)
    }

    fun removePhoto(position: Int): Int {
        photos.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeRemoved(position, photos.size)
        return photos.size
    }

    class PhotosHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val photoView = itemView.photo
        private val favouriteCheck = itemView.favourite

        fun bind(
            photo: Photo,
            photoCheckedListener: PhotoCheckedListener?,
            position: Int
        ) {
            Picasso.get().load(photo.thumbnailUrl).placeholder(R.drawable.placeholder)
                .into(photoView)
            favouriteCheck.setOnCheckedChangeListener { _, isChecked ->
                photo.favourite = isChecked
                photoCheckedListener?.onPhotoChecked(photo, position)
            }
            favouriteCheck.isChecked = photo.favourite
            photoView.setOnClickListener { ImageDialog(photo.url).show(itemView.context) }
        }

    }
}