package jimmy.placeholder_api.ui.comments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import jimmy.placeholder_api.R
import jimmy.placeholder_api.data.model.Comment
import jimmy.placeholder_api.databinding.CommentItemLayoutBinding

class CommentsAdapter(private val comments: List<Comment>) :
    RecyclerView.Adapter<CommentsAdapter.PostsHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsHolder {
        val itemBinding: CommentItemLayoutBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.comment_item_layout, parent, false
        )
        return PostsHolder(itemBinding)
    }

    override fun getItemCount() = comments.size

    override fun onBindViewHolder(holder: PostsHolder, position: Int) {
        holder.itemBinding.comment = comments[position]
    }

    class PostsHolder(val itemBinding: CommentItemLayoutBinding) :
        RecyclerView.ViewHolder(itemBinding.root)
}
