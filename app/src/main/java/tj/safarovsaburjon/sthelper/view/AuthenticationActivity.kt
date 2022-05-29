package tj.safarovsaburjon.sthelper.view

import android.app.KeyguardManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.hardware.biometrics.BiometricPrompt
import android.os.Build
import android.os.Bundle
import android.os.CancellationSignal
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import tj.safarovsaburjon.sthelper.R
import tj.safarovsaburjon.sthelper.core.base.BaseActivity

class AuthenticationActivity : BaseActivity(R.layout.activity_authentication),
    View.OnClickListener {
    private lateinit var passwordEditText: EditText
    private lateinit var loginEditText: EditText
    private lateinit var submitButton: Button
    private lateinit var fingerPrintButton: ImageView
    private lateinit var biometricPrompt: BiometricPrompt

    private var cancellationSignal: CancellationSignal? = null
    private val authenticationCallback: BiometricPrompt.AuthenticationCallback
        get() = @RequiresApi(Build.VERSION_CODES.P)
        object : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationError(errorCode: Int, errString: CharSequence?) {
                super.onAuthenticationError(errorCode, errString)
                notifyUser("Authentication Error : $errString")
            }

            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult?) {
                super.onAuthenticationSucceeded(result)
                notifyUser("Authentication Succeeded")
                fingerPrintButton.setImageResource(R.drawable.ic_fingerprint_black)
                startActivity(Intent(this@AuthenticationActivity, MainActivity::class.java))
                finish()

            }
        }


    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        checkBiometricSupport()
        submitButton = findViewById(R.id.authenticationSubmitButton)
        passwordEditText = findViewById(R.id.authenticationPasswordEditText)
        loginEditText = findViewById(R.id.authenticationUserIdEditText)
        fingerPrintButton = findViewById(R.id.fingerPrintCardView)


        submitButton.setOnClickListener(this)

        biometricPrompt = BiometricPrompt.Builder(this)
            .setTitle("воридшави бо изи ангушт")
            .setNegativeButton(
                "cancel",
                this.mainExecutor
            ) { _, _ ->
            }.build()

        biometricCall()

        fingerPrintButton.setOnClickListener(this)


    }


    @RequiresApi(Build.VERSION_CODES.P)
    private fun biometricCall() {
        biometricPrompt.authenticate(
            getCancellationSignal(),
            mainExecutor,
            authenticationCallback
        )
    }

    override fun onPause() {
        super.onPause()
        loginEditText.text.clear()
        passwordEditText.text.clear()
        loginEditText.requestFocus()

    }

    private fun loginChecker(correctLogin: String, correctPassword: String): Boolean =
        (passwordEditText.text.isNotEmpty() && loginEditText.text.isNotEmpty()) && (passwordEditText.text.toString() == correctPassword && loginEditText.text.toString() == correctLogin)

    private fun notifyUser(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onClick(v: View?) {
        v?.let {
            when (v) {
                submitButton -> {

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
                        Toast.makeText(
                            this,
                            "password or login is not correct !",
                            Toast.LENGTH_SHORT
                        )
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

                fingerPrintButton -> {
                    if  (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                        biometricCall()

                    } else {
                        Toast.makeText(
                            this,
                            "тачхизоти шумо изи ангуштро дастгири намекунад",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                else -> {
                    Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun getCancellationSignal(): CancellationSignal {
        cancellationSignal = CancellationSignal()
        cancellationSignal?.setOnCancelListener {
            notifyUser("Authentication was Cancelled by the user")
        }
        return cancellationSignal as CancellationSignal
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun checkBiometricSupport(): Boolean {
        val keyguardManager = getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager
        if (!keyguardManager.isDeviceSecure) {
            notifyUser("Fingerprint authentication has not been enabled in settings")
            return false
        }
        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.USE_BIOMETRIC
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            notifyUser("Fingerprint Authentication Permission is not enabled")
            return false
        }
        return if (packageManager.hasSystemFeature(PackageManager.FEATURE_FINGERPRINT)) {
            true
        } else true
    }

}