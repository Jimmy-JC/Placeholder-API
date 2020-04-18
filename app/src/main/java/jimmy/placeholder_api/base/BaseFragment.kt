package jimmy.placeholder_api.base

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import jimmy.placeholder_api.R
import jimmy.placeholder_api.data.network.response.Resource
import jimmy.placeholder_api.data.network.response.Status.*
import jimmy.placeholder_api.dialogs.ProgressDialog

abstract class BaseFragment<T> : Fragment() {

    abstract val viewModel: BaseViewModel<T>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.listLiveData.observe(viewLifecycleOwner, responseObserver)
        viewModel.getDataList()
    }

    private val responseObserver = Observer<Resource<List<T>>> {
        when (it.status) {
            LOADING -> ProgressDialog.show(requireContext())
            FINISHED -> ProgressDialog.dismiss()
            SUCCESS -> displayList(it.data)
            ERROR -> Toast.makeText(activity,
                it.throwable?.message, Toast.LENGTH_SHORT).show()
        }
    }

    abstract fun displayList(data: List<T>?)

    fun addFragment(fragment: Fragment) {
        activity?.supportFragmentManager
            ?.beginTransaction()
            ?.add(R.id.fragment_container, fragment)
            ?.addToBackStack(null)?.commit()
    }

}