package makeable.intempus.userinterface.activities

import android.os.Bundle
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.*
import makeable.intempus.R
import makeable.intempus.domain.SignIn.SignInFeature
import kotlin.coroutines.CoroutineContext

/**
 * A login screen that offers login via email/password.
 */
class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val exceptionHandler = CoroutineExceptionHandler { context, error ->
            println(error.message)
            println(context.toString())
        }
        val parentJob = CoroutineScope(Dispatchers.Main + exceptionHandler).launch {
             val result = withContext(Dispatchers.IO){
                 runFeature()
             }
            showObjects(result)

         }
    }

    fun runFeature() : List<Any> {
        SignInFeature(
            fun(error: Throwable?, objects: MutableList<Any>?) {
                if (error != null) {
                    Toast.makeText(this, error.message, LENGTH_LONG).show()
                }
            }, email.text.toString(),
            password.text.toString()
        ).execute()

        return  mutableListOf<Any>()
    }
    fun showObjects(objs : List<Any>){

    }
}
