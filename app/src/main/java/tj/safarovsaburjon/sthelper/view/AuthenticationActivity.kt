package tj.safarovsaburjon.sthelper.view

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import tj.safarovsaburjon.sthelper.R
import tj.safarovsaburjon.sthelper.core.base.BaseActivity
import kotlin.math.log

class AuthenticationActivity : BaseActivity(R.layout.activity_authentication) {
    private lateinit var passwordEditText: EditText
    private lateinit var loginEditText: EditText
    private lateinit var submitButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        submitButton = findViewById(R.id.authenticationSubmitButton)
        passwordEditText = findViewById(R.id.authenticationPasswordEditText)
        loginEditText = findViewById(R.id.authenticationUserIdEditText)


        submitButton.setOnClickListener {

            if (loginChecker("safarovsaburjon2002@gmail.com", "3377")) {
                loginEditText.setCompoundDrawablesWithIntrinsicBounds(
                    0,
                    0,
                    R.drawable.ic_twotone_check_circle,
                    0
                )
                passwordEditText.setCompoundDrawablesWithIntrinsicBounds(
                    0,
                    0,
                    R.drawable.ic_twotone_check_circle,
                    0
                )
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "password or login is not correct !", Toast.LENGTH_SHORT)
                    .show()
                loginEditText.text.clear()
                passwordEditText.text.clear()
                loginEditText.requestFocus()
                loginEditText.setCompoundDrawablesWithIntrinsicBounds(
                    0,
                    0,
                    R.drawable.ic_twotone_error,
                    0
                )

                passwordEditText.setCompoundDrawablesWithIntrinsicBounds(
                    0,
                    0,
                    R.drawable.ic_twotone_error,
                    0

                )

                loginEditText

            }
        }
    }

    override fun onPause() {
        super.onPause()
        loginEditText.text.clear()
        passwordEditText.text.clear()
        loginEditText.requestFocus()

    }


    fun loginChecker(correctLogin: String, correctPassword: String): Boolean =
        (passwordEditText.text.isNotEmpty() && loginEditText.text.isNotEmpty()) && (passwordEditText.text.toString() == correctPassword && loginEditText.text.toString() == correctLogin)

}