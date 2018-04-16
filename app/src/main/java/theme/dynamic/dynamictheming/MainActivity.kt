package theme.dynamic.dynamictheming

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.widget.FrameLayout
import androidx.core.content.edit
import theme.dynamic.dynamictheming.extensions.inTransaction
import theme.dynamic.dynamictheming.fragments.FavoriteFragment
import theme.dynamic.dynamictheming.fragments.HomeFragment
import theme.dynamic.dynamictheming.fragments.ReplayFragment
import theme.dynamic.dynamictheming.fragments.StarFragment

const val KEY_PREF = "pref_theme"

class MainActivity : AppCompatActivity() {

    lateinit var flContainer: FrameLayout
    lateinit var bottomBar: BottomNavigationView

    lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(getCurrentTheme())
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.inTransaction {
                add(R.id.fl_main_container, HomeFragment())
            }
        }

        bottomBar = findViewById(R.id.bottom_navigation)
        bottomBar.setOnNavigationItemSelectedListener {
            item ->
                when (item.itemId) {
                    R.id.action_home -> {
                        changeTheme("Home")
                        fragmentTransaction(HomeFragment())
                        true
                    }
                    R.id.action_replay -> {
                        changeTheme("Replay")
                        fragmentTransaction(ReplayFragment())
                        true
                    }
                    R.id.action_favorite-> {
                        changeTheme("Favorite")
                        fragmentTransaction(FavoriteFragment())
                        true
                    }
                    R.id.action_star-> {
                        changeTheme("Star")
                        fragmentTransaction(StarFragment())
                        true
                    }
                    else -> {
                        changeTheme("Home")
                        fragmentTransaction(HomeFragment())
                        true
                    }
                }
        }
    }

    private fun getCurrentTheme(): Int {
        sharedPref = PreferenceManager.getDefaultSharedPreferences(this)

        val savedTheme = sharedPref.getString(KEY_PREF, "Home")

        return when (savedTheme) {
            "Home" -> R.style.HomeTheme
            "Replay" -> R.style.ReplayTheme
            "Favorite" -> R.style.FavoriteTheme
            "Star" -> R.style.StarTheme
            else -> R.style.HomeTheme
        }
    }

    private fun changeTheme(theme: String) {
        sharedPref.edit {
            putString(KEY_PREF, theme)
        }
        recreate()
    }

    private fun fragmentTransaction(fragment: Fragment) {
        supportFragmentManager.inTransaction {
            replace(R.id.fl_main_container, fragment)
        }
    }
}
