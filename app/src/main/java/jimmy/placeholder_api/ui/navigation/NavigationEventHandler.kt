package jimmy.placeholder_api.ui.navigation

import android.view.MenuItem
import androidx.fragment.app.Fragment
import jimmy.placeholder_api.R
import jimmy.placeholder_api.ui.albums.AlbumsFragment
import jimmy.placeholder_api.ui.favourites.FavouritesFragment
import jimmy.placeholder_api.ui.posts.PostsFragment

class NavigationEventHandler(private val activity: NavigationActivity) {

    fun onNavigationClick(item: MenuItem): Boolean {
        var fragment: Fragment? = null

        when (item.itemId) {
            R.id.posts -> fragment = PostsFragment()
            R.id.albums -> fragment = AlbumsFragment()
            R.id.favourites -> fragment = FavouritesFragment()
        }

        if (fragment != null) {
            activity.openFragment(fragment, OpenFragmentType.REPLACE, item.title)
        }
        return true
    }

}
