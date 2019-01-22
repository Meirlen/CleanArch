package meirlen.cleanarch

import android.content.Context
import android.os.Build
import kotlinx.android.synthetic.main.activity_main.*
import meirlen.cleanarch.ui.board.BoardsFragment
import meirlen.cleanarch.ui.home.HomeFragment
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1], application = TestApplication::class)
class HomeActivityTest {

    private lateinit var activity: HomeActivity
    private lateinit var context: Context

    @Before
    fun setUpTest() {
        context = RuntimeEnvironment.application.baseContext
        val intent = HomeActivity.getStartIntent(context)
        activity = Robolectric.buildActivity(HomeActivity::class.java, intent).create().start().get()
    }

    @Test
    fun shouldSelectHomeTabOnCreate() {
        assertEquals(HomeActivity.HOME, activity.bottomTabNavigation.selectedTabPosition)
        assertTrue(activity.supportFragmentManager.findFragmentById(R.id.frame_container) is BoardsFragment)
    }

    @Test
    fun shouldShowProfileFragment() {
        activity.bottomTabNavigation.getTabAt(HomeActivity.PROFILE)?.select()
        assertEquals(HomeActivity.PROFILE, activity.bottomTabNavigation.selectedTabPosition)
    }

    @After
    fun tearDown() {
        activity.finish()
    }
}