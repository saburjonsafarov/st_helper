package tj.safarovsaburjon.sthelper.view

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import tj.safarovsaburjon.sthelper.R
import tj.safarovsaburjon.sthelper.core.base.BaseActivity

class MainActivity : BaseActivity(R.layout.activity_main),
    View.OnClickListener,
    NavigationBarView.OnItemSelectedListener {
    private lateinit var toolbar: Toolbar
    private lateinit var container: FragmentContainerView
    private lateinit var bottomNavigationView: BottomNavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        container = findViewById(R.id.container)
        toolbar = findViewById(R.id.toolbar)
        bottomNavigationView = findViewById(R.id.bottomNavigationView)


        toolbar.setNavigationOnClickListener {
            startActivity(Intent(this, AuthenticationActivity::class.java))
            finish()
        }

        bottomNavigationView.apply {
            setOnItemSelectedListener(this@MainActivity)
            getOrCreateBadge(R.id.newsItem).apply {
                number = 12
            }
        }
        toolbar.setOnClickListener(this)
        container.setOnClickListener(this)

        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.container,
                NewsFragment()
            )
            .commit()


    }

    override fun onStop() {
        super.onStop()
        finish()
    }

    override fun onClick(v: View?) {
        v?.let {
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.analyticItem -> {
                openFragmentHelper(ProfileFragment(), "statistics")
                return true
            }

            R.id.newsItem -> {
                openFragmentHelper(NewsFragment(), "news")
                return true
            }

            R.id.alarmItem -> {
                openFragmentHelper(NotificationFragment(), "alarm")
                return true
            }

            R.id.profileItem -> {
                openFragmentHelper(ProfileFragment(), "profile")
                return true
            }

        }

        return false
    }

    private fun openFragmentHelper(fragment: Fragment, toolbarTitle: String?) {

        toolbarTitle?.let {
            toolbar.title = toolbarTitle
        }
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }
}