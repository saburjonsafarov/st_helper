package tj.safarovsaburjon.sthelper.view

import android.content.Intent
import android.content.IntentFilter
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorStateListDrawable
import android.os.Build
import android.os.Bundle
import android.os.PowerManager
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import tj.safarovsaburjon.sthelper.R
import tj.safarovsaburjon.sthelper.core.base.BaseActivity
import tj.safarovsaburjon.sthelper.repository.MainRepository

class MainActivity : BaseActivity(R.layout.activity_main),
    View.OnClickListener,
    NavigationBarView.OnItemSelectedListener {
    private lateinit var toolbar: Toolbar
    private lateinit var container: FragmentContainerView
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var accountItem: ImageView
    private var c = 0

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        container = findViewById(R.id.container)
        toolbar = findViewById(R.id.toolbar)
        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        accountItem = findViewById(R.id.accountItem)

        accountItem.setOnClickListener(this)
        bottomNavigationView.apply {
            selectedItemId = R.id.newsItem
            itemActiveIndicatorColor = getColorStateList(R.color.blue_dark)
        }
        toolbar.setNavigationOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        MainRepository.items.forEach { el ->
            if (el.liked)
                c++
        }

        bottomNavigationView.apply {
            setOnItemSelectedListener(this@MainActivity)
            getOrCreateBadge(R.id.newsItem).apply {
                number = c
            }
        }

        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.container,
                NewsFragment()
            )
            .commit()


    }


    override fun onClick(v: View?) {
        v?.let {
            when (it) {
                accountItem ->
                    startActivity(Intent(this, DetailActivity::class.java))
            }
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.analyticItem -> {
                openFragmentHelper(AnalyticsFragment(), "analytics")
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


            R.id.timetableItem -> {
                openFragmentHelper(NotificationFragment(), "timetable")
                return true
            }

        }

        return false
    }

    private fun openFragmentHelper(fragment: Fragment, toolbarTitle: String) {

        toolbar.title = toolbarTitle.uppercase()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }

}