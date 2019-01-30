package meirlen.cleanarch

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v7.app.AppCompatActivity
import com.example.gateway.entity.Board
import com.example.gateway.entity.Column
import meirlen.cleanarch.utill.ext.replaceByTag
import meirlen.cleanarch.ui.board.BoardsFragment
import meirlen.cleanarch.ui.custom.SimpleOnTabSelectedListener
import meirlen.cleanarch.ui.home.HomeFragment
import meirlen.cleanarch.ui.profile.ProfileFragment
import kotlinx.android.synthetic.main.activity_main.*

class HomeActivity : AppCompatActivity() {

    companion object {

        private const val CURRENT_SCREEN = "current_screen"
        const val HOME = 0
        const val SEARCH = 1
        const val SHARE = 2
        const val FAVOURITE = 3
        const val PROFILE = 4

        fun getStartIntent(context: Context, isNewTask: Boolean = false): Intent {
            val intent = Intent(context, HomeActivity::class.java)
            if (isNewTask) {
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            }
            return intent
        }
    }

    private var currentScreen: Int = HOME

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        currentScreen = savedInstanceState?.getInt(CURRENT_SCREEN, HOME) ?: HOME
        setupNavigation()
        switchFragment(currentScreen)
    }

    private fun setupNavigation() {
        bottomTabNavigation.getTabAt(currentScreen)?.select()
        bottomTabNavigation.addOnTabSelectedListener(object : SimpleOnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                 switchFragment(tab.position)
            }
        })
    }

    private fun switchFragment(position: Int) {
        currentScreen = position
        supportFragmentManager.replaceByTag(R.id.frame_container, position.toString(), {
            when (position) {
                HOME -> ProfileFragment()
                SEARCH -> HomeFragment()
                SHARE -> HomeFragment()
                FAVOURITE -> HomeFragment()
                PROFILE -> ProfileFragment()
                else -> HomeFragment()
            }
        }).commit()
    }


}
