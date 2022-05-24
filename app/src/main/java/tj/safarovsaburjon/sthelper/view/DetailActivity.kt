package tj.safarovsaburjon.sthelper.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import tj.safarovsaburjon.sthelper.R

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.containerDetail, ProfileFragment())
            .commit()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, MainActivity::class.java))
    }
}