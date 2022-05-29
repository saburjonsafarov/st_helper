package tj.safarovsaburjon.sthelper.view

import android.annotation.SuppressLint
import android.app.KeyguardManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.hardware.biometrics.BiometricPrompt
import android.os.Build
import android.os.Bundle
import android.os.CancellationSignal
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import tj.safarovsaburjon.sthelper.R
import tj.safarovsaburjon.sthelper.core.base.BaseActivity

class LoginActivity : BaseActivity(R.layout.activity_login), View.OnClickListener {
    private lateinit var verticalLinearLayout: LinearLayout
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
                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                finish()

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

    private fun notifyUser(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    @RequiresApi(Build.VERSION_CODES.P)
    private fun biometricCall() {
        biometricPrompt.authenticate(
            getCancellationSignal(),
            mainExecutor,
            authenticationCallback
        )
    }

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("SetTextI18n", "NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        verticalLinearLayout = findViewById(R.id.loginKeyboardVerticalLinearLayout)
        checkBiometricSupport()
        biometricPrompt = BiometricPrompt.Builder(this)
            .setTitle("воридшави бо изи ангушт")
            .setNegativeButton(
                "cancel",
                this.mainExecutor
            ) { _, _ ->
            }.build()

        biometricCall()


        val inflater = LayoutInflater.from(this)
        var c = 1
        repeat(4) {
            if (c > 10) c = 0
            val verticalItem =
                inflater.inflate(R.layout.login_keyboard_item, verticalLinearLayout, false)

            verticalItem.findViewById<Button>(R.id.loginKeyboardButtonStart).apply {

                text = (c).toString()
                tag = c
                setOnClickListener(this@LoginActivity)
            }
            verticalItem.findViewById<Button>(R.id.loginKeyboardButtonCenter).apply {
                if (c == 9)
                    text = "FP"
                text = (c + 1).toString()
                tag = c + 1
                setOnClickListener(this@LoginActivity)

            }
            verticalItem.findViewById<Button>(R.id.loginKeyboardButtonEnd).apply {
                if (it == 3)
                    text = "clear"
                text = (c + 2).toString()
                tag = c + 2
                setOnClickListener(this@LoginActivity)
            }



            c += 3

            when (c) {
                13 -> {
                    verticalItem.findViewById<Button>(R.id.loginKeyboardButtonStart).apply {
                        text = "FP"
                        tag = c
                        setOnClickListener(this@LoginActivity)
                    }
                }
                16 -> {
                    verticalItem.findViewById<Button>(R.id.loginKeyboardButtonCenter).apply {
                        text = 0.toString()
                        tag = c
                        setOnClickListener(this@LoginActivity)
                    }
                }

                12 -> {
                    verticalItem.findViewById<Button>(R.id.loginKeyboardButtonEnd).apply {
                        text = "clear"
                        tag = c
                        setOnClickListener(this@LoginActivity)
                    }

                }
            }



            verticalLinearLayout.addView(verticalItem)
        }


    }

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onClick(v: View?) {
        v?.let {
            when (v.tag as Int) {
                1 -> {
                    Toast.makeText(this, (it.tag as Int).toString(), Toast.LENGTH_SHORT).show()
                }

                2 -> {

                }

                3 -> {

                }

                4 -> {

                }

                5 -> {

                }

                6 -> {

                }


                7 -> {

                }


                8 -> {

                }


                9 -> {

                }

                10 -> {

                    Toast.makeText(this, (it.tag as Int).toString(), Toast.LENGTH_SHORT).show()


                }

                11 -> {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                        biometricCall()

                    } else {
                        Toast.makeText(
                            this,
                            "тачхизоти шумо изи ангуштро дастгири намекунад",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                12 -> {

                }
            }
        }
    }
}