package tj.safarovsaburjon.sthelper.view

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.navigation.NavigationBarView
import tj.safarovsaburjon.sthelper.R
import tj.safarovsaburjon.sthelper.core.base.BaseActivity
import tj.safarovsaburjon.sthelper.model.StudentModel
import tj.safarovsaburjon.sthelper.repository.MainRepository

class MainActivity : BaseActivity(R.layout.activity_main),
    View.OnClickListener,
    NavigationBarView.OnItemSelectedListener {
    private lateinit var toolbar: Toolbar
    private lateinit var container: FragmentContainerView
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var accountItem: CardView
    var c = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        container = findViewById(R.id.container)
        toolbar = findViewById(R.id.toolbar)
        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        accountItem = findViewById(R.id.accountItem)

        accountItem.setOnClickListener(this)
        bottomNavigationView.selectedItemId = R.id.newsItem

        toolbar.setNavigationOnClickListener {
            startActivity(Intent(this, AuthenticationActivity::class.java))
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

    override fun onStop() {
        super.onStop()
        finish()
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
                openFragmentHelper(ProfileFragment(), "statistics".uppercase())
                return true
            }

            R.id.newsItem -> {
                openFragmentHelper(NewsFragment(), "news".uppercase())
                return true
            }

            R.id.alarmItem -> {
                openFragmentHelper(NotificationFragment(), "alarm".uppercase())
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