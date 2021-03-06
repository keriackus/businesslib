import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.withContext
import com.keriackus.thatwaseasy.businesslib.core.Feature

abstract class BusinessAction(
    private val parentFeature: Feature?
) {
    protected lateinit var completionBlock: (error: Throwable?, objects: MutableList<Any>?) -> Unit
    var actionOrder: Int = 0
    var businessResults = mutableListOf<Any>()
    private fun isStandalone(): Boolean {

        return parentFeature == null
    }

    suspend fun execute() {

        val x = withContext(newSingleThreadContext("BINO")) { doTheJob() }
        //    Log.i("Execute: ", this::class.simpleName);
    }

    protected open suspend fun doTheJob() {
        //  Log.i("AsyncWork of: ", this::class.simpleName);
    }

    protected open suspend fun moveOn(e: Throwable?) {
        if (isStandalone()) {
            completionBlock(e, businessResults)
        } else {
            if (e == null) {
                parentFeature?.continueExecution(this)
            } else {
                parentFeature?.onError(this, e)
            }
        }
    }

    constructor(completionBlock: (error: Throwable?, objects: MutableList<Any>?) -> Unit) : this(
        null
    ) {
        this.completionBlock = completionBlock
    }

}
/*

 fun scream(voice: String): Int {
        //screeaaam
        return 23
    }
Example for extension functions, higher order functions, and lambda, yeay
//inline to rpelace it with the code instead of creating anonymous objects
inline fun <T> BusinessAction.higherOrderScreamBeforeAndAfter(
    callable: BusinessAction.(boil: Long, fry: Boolean) -> T,
    magnoon: Int
): T {
    this.scream("MaleVoice")

    var result = callable(23, true)
    this.scream("FemaleVoice")
    return result
}

fun x() {
    var b: Usecase = HttpConnectionUsecase(null)
 var result =   b.higherOrderScreamBeforeAndAfter({ l: Long, b: Boolean ->
        {
            if(b){
                var result = l + this.actionOrder  //we can access this here because the parameter unction itself is an extension of BusinessAction
                 result
            }else {
                -3
            }
        }
    }, 90)
}

  this.higherOrderScreamBeforeAndAfter({ l: Long, b: Boolean ->
            if (b) {
                ""
            } else {
                "YAS QUEEN"
            }

        }, 2)
*/
