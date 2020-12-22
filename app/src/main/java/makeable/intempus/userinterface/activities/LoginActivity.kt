package makeable.intempus.userinterface.activities

import android.os.Bundle
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*
import makeable.intempus.R
import makeable.intempus.domain.SignIn.SignInFeature

/**
 * A login screen that offers login via email/password.
 */
class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        SignInFeature(
            fun(error: Throwable?, objects: MutableList<Any>?) {
                if (error != null) {
                    Toast.makeText(this, error.message, LENGTH_LONG).show()
                }
            }, email.text.toString(),
            password.text.toString()
        )
    //.execute()


    }
}
