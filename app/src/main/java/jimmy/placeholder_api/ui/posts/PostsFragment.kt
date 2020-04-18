package jimmy.placeholder_api.ui.posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import jimmy.placeholder_api.R
import jimmy.placeholder_api.base.BaseFragment
import jimmy.placeholder_api.data.model.Post
import jimmy.placeholder_api.databinding.FragmentPostsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class PostsFragment : BaseFragment<Post>() {

    lateinit var postsBinding: FragmentPostsBinding
    override val viewModel: PostsViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        postsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_posts, container, false)
        return postsBinding.root
    }

    override fun displayList(data: List<Post>?) {
        postsBinding.posts = data
    }

}
