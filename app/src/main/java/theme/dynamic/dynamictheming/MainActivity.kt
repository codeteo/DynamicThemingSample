package theme.dynamic.dynamictheming

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.widget.FrameLayout
import theme.dynamic.dynamictheming.extensions.inTransaction
import theme.dynamic.dynamictheming.fragments.FavoriteFragment
import theme.dynamic.dynamictheming.fragments.HomeFragment
import theme.dynamic.dynamictheming.fragments.ReplayFragment
import theme.dynamic.dynamictheming.fragments.StarFragment

class MainActivity : AppCompatActivity() {

    lateinit var flContainer: FrameLayout
    lateinit var bottomBar: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.inTransaction {
            add(R.id.fl_main_container, HomeFragment())
        }

        bottomBar = findViewById(R.id.bottom_navigation)
        bottomBar.setOnNavigationItemSelectedListener {
            item ->
                when (item.itemId) {
                    R.id.action_home -> {
                        fragmentTransaction(HomeFragment())
                        true
                    }
                    R.id.action_replay -> {
                        fragmentTransaction(ReplayFragment())
                        true
                    }
                    R.id.action_favorite-> {
                        fragmentTransaction(FavoriteFragment())
                        true
                    }
                    R.id.action_star-> {
                        fragmentTransaction(StarFragment())
                        true
                    }
                    else -> {
                        fragmentTransaction(HomeFragment())
                        true
                    }
                }
        }
    }

    private fun fragmentTransaction(fragment: Fragment) {
        supportFragmentManager.inTransaction {
            replace(R.id.fl_main_container, fragment)
        }
    }
}
