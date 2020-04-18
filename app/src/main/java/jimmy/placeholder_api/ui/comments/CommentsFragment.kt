package jimmy.placeholder_api.ui.comments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import jimmy.placeholder_api.R
import jimmy.placeholder_api.base.BaseFragment
import jimmy.placeholder_api.data.model.Comment
import jimmy.placeholder_api.databinding.FragmentCommentsBinding
import jimmy.placeholder_api.ui.navigation.SharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CommentsFragment : BaseFragment<Comment>() {

    lateinit var commentsBinding: FragmentCommentsBinding
    override val viewModel: CommentsViewModel by viewModel()
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        commentsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_comments, container, false)
        return commentsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedViewModel.postLiveData.observe(viewLifecycleOwner, Observer {
            commentsBinding.post = it
            viewModel.getDataList(it.id)
        })
    }

    override fun displayList(data: List<Comment>?) {
        commentsBinding.comments = data
    }

}
