package jimmy.placeholder_api.ui.navigation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import jimmy.placeholder_api.R
import jimmy.placeholder_api.databinding.ActivityNavigationBinding
import jimmy.placeholder_api.ui.navigation.OpenFragmentType.*

class NavigationActivity : AppCompatActivity() {

    val sharedViewModel: SharedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityNavigationBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_navigation)
        binding.selectedItemId = R.id.posts
        binding.handler = NavigationEventHandler(this)
    }

    fun openFragment(fragment: Fragment, type: OpenFragmentType, title: CharSequence? = null) {

        val transaction = supportFragmentManager.beginTransaction()
        when (type) {
            REPLACE -> transaction.replace(R.id.fragment_container, fragment)
            ADD -> transaction.add(R.id.fragment_container, fragment).addToBackStack(null)
        }
        transaction.commit()

        title?.let {
            setTitle(getString(R.string.title_prefix) + title)
        }
    }

}
