package jimmy.placeholder_api

import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import jimmy.placeholder_api.ui.navigation.NavigationActivity
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class NavigationActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(NavigationActivity::class.java)

    @Test
    fun emptyFavouritesTest() {
        pressBack()
        openFavouritesFragment()
        checkEmptyImageDisplayed()
    }

    private fun openFavouritesFragment() {
        val bottomNavigationItemView = onView(
            allOf(
                withId(R.id.favourites),
                withContentDescription("Favourites"),
                childAtPosition(childAtPosition(withId(R.id.bottom_navigation), 0), 2),
                isDisplayed()
            )
        )
        bottomNavigationItemView.perform(click())
    }

    private fun checkEmptyImageDisplayed() {
        val imageView = onView(
            allOf(
                withId(R.id.not_found),
                childAtPosition(childAtPosition(withId(R.id.fragment_container), 0), 1),
                isDisplayed()
            )
        )
        imageView.check(matches(isDisplayed()))
    }

    private fun childAtPosition(parentMatcher: Matcher<View>, position: Int): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }

}
