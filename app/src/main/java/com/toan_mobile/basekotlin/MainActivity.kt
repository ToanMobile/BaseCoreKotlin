package com.mytel.android

import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.netacom.base.chat.base.BaseActivity

class MainActivity : BaseActivity(
    R.layout.activity_main
) {
    private val showNavFragments = listOf(R.id.conversationFragment, R.id.callFragment, R.id.contactFragment, R.id.profileFragment)
    private val navController by lazy {
        (supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment).navController
    }

    override fun initData() {
    }

    override fun initViews() {
        with(navController) {
            setupWithNavController(findViewById<BottomNavigationView>(R.id.navView), this)
            addOnDestinationChangedListener { _, destination, _ ->
                if (showNavFragments.contains(destination.id)) {
                    showNavBottom(isShow = true)
                } else {
                    showNavBottom(isShow = false)
                }
            }
        }
    }

    override fun onBackPressed() {
        if (navController.graph.startDestinationId == navController.currentDestination?.id) {
            moveTaskToBack(true)
        } else {
            super.onBackPressed()
        }
    }

    override fun onSupportNavigateUp(): Boolean = navController.navigateUp()

    private fun showNavBottom(isShow: Boolean) {
        with(findViewById<BottomNavigationView>(R.id.navView)) {
            if (isShow) {
                if (isGone) {
                    isVisible = true
                }
            } else if (navController.currentDestination?.navigatorName != "dialog") {
                isGone = true
            }
        }
    }
}
