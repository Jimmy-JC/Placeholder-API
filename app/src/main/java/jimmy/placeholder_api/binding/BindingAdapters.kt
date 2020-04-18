package jimmy.placeholder_api.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.squareup.picasso.Picasso
import jimmy.placeholder_api.R
import jimmy.placeholder_api.data.model.Album
import jimmy.placeholder_api.data.model.Comment
import jimmy.placeholder_api.data.model.Photo
import jimmy.placeholder_api.data.model.Post
import jimmy.placeholder_api.ui.albums.AlbumsAdapter
import jimmy.placeholder_api.ui.comments.CommentsAdapter
import jimmy.placeholder_api.ui.photos.PhotoCheckedListener
import jimmy.placeholder_api.ui.photos.PhotosAdapter
import jimmy.placeholder_api.ui.posts.PostsAdapter

@BindingAdapter("selectedItem")
fun setSelectedItem(view: BottomNavigationView, itemId: Int) {
    view.selectedItemId = itemId
}

@BindingAdapter("onNavigationItemSelected")
fun setOnNavigationItemSelectedListener(
    view: BottomNavigationView,
    listener: BottomNavigationView.OnNavigationItemSelectedListener?
) {
    view.setOnNavigationItemSelectedListener(listener)
}

@BindingAdapter("postsList")
fun setPostsList(postsRecycler: RecyclerView, posts: List<Post>?) {
    posts?.let {
        postsRecycler.adapter = PostsAdapter(it)
    }
}

@BindingAdapter("commentsList")
fun setCommentsList(commentsRecycler: RecyclerView, comments: List<Comment>?) {
    comments?.let {
        commentsRecycler.adapter = CommentsAdapter(it)
    }
}

@BindingAdapter("albumsList")
fun setAlbumsList(albumsRecycler: RecyclerView, albums: List<Album>?) {
    albums?.let {
        albumsRecycler.adapter = AlbumsAdapter(it)
    }
}

@BindingAdapter(value = ["photosList", "photoCheckListener"], requireAll = true)
fun setPhotoList(
    photosRecycler: RecyclerView,
    photos: List<Photo>?,
    photoCheckedListener: PhotoCheckedListener?
) {
    photos?.let {
        photosRecycler.layoutManager = GridLayoutManager(photosRecycler.context, 3)
        photosRecycler.adapter = PhotosAdapter(photos.toMutableList(), photoCheckedListener)
    }
}

@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url: String) {
    Picasso.get().load(url).placeholder(R.drawable.placeholder).into(imageView)
}