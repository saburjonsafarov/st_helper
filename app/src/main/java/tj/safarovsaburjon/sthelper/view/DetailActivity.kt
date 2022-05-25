package tj.safarovsaburjon.sthelper.view

import android.app.Application
import android.graphics.Color
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import tj.safarovsaburjon.sthelper.R
import tj.safarovsaburjon.sthelper.core.base.BaseActivity

class DetailActivity : BaseActivity(R.layout.activity_detail) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.containerDetail, ProfileFragment())
            .commit()

        findViewById<FloatingActionButton>(R.id.fAButton).setOnClickListener {
            Toast.makeText(this, "changed", Toast.LENGTH_SHORT).show()

        }



    }

}