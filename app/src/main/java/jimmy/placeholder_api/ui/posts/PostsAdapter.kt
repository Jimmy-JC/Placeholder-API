package jimmy.placeholder_api.ui.posts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import jimmy.placeholder_api.R
import jimmy.placeholder_api.data.model.Post
import jimmy.placeholder_api.databinding.PostItemLayoutBinding
import jimmy.placeholder_api.ui.comments.CommentsFragment
import jimmy.placeholder_api.ui.navigation.NavigationActivity
import jimmy.placeholder_api.ui.navigation.OpenFragmentType.ADD

class PostsAdapter(private val posts: List<Post>) :
    RecyclerView.Adapter<PostsAdapter.PostsHolder>() {

    private lateinit var activity: NavigationActivity

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsHolder {

        val itemBinding: PostItemLayoutBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.post_item_layout, parent, false
        )
        return PostsHolder(itemBinding)
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        activity = recyclerView.context as NavigationActivity
    }

    override fun getItemCount() = posts.size

    override fun onBindViewHolder(holder: PostsHolder, position: Int) {
        holder.itemBinding.post = posts[position]
        holder.itemBinding.setClickListener {
            activity.sharedViewModel.postLiveData.value = posts[position]
            activity.openFragment(fragment = CommentsFragment(), type = ADD)
        }
    }

    class PostsHolder(val itemBinding: PostItemLayoutBinding) :
        RecyclerView.ViewHolder(itemBinding.root)
}
