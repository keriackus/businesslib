package makeable.intempus.userinterface.activities

import android.os.Bundle
import android.view.View
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.launch
import makeable.intempus.R
import makeable.intempus.domain.SignIn.SignInFeature

/**
 * A login screen that offers login via email/password.
 */
class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var signInFeature: SignInFeature
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        signInFeature = SignInFeature(
            fun(error: Throwable?, objects: MutableList<Any>?) {
                if (error != null) {
                    Toast.makeText(this, error.message, LENGTH_LONG).show()
                }
            }, email.text.toString(),
            password.text.toString()
        )

        email_sign_in_button.setOnClickListener(View.OnClickListener {
            lifecycleScope.launch {
                signInFeature = SignInFeature(
                    fun(error: Throwable?, objects: MutableList<Any>?) {
                        if (error != null) {
                          //  Toast.makeText(this, error.message, LENGTH_LONG).show()
                        }
                    }, email.text.toString(),
                    password.text.toString()
                )
                signInFeature.execute()
            }

        })

    }
}
