import com.keriackus.thatwaseasy.Configure
import com.keriackus.thatwaseasy.businesslib.core.Feature
import com.keriackus.thatwaseasy.businesslib.core.HttpConnectionUsecase
import com.keriackus.thatwaseasy.businesslib.core.Usecase
import com.sun.org.apache.xpath.internal.operations.Bool
import kotlinx.coroutines.*

abstract class BusinessAction(
    private val parentFeature: Feature?
) {
    protected lateinit var completionBlock: (error: Throwable?, objects: MutableList<Any>?) -> Unit
    private lateinit var  coroutineJob : Job

    var actionOrder: Int = 0
    var businessResults = mutableListOf<Any>()
    private fun isStandalone(): Boolean {
        return parentFeature == null
    }

     fun execute() {

        GlobalScope.launch{
            println(  "before delay")
            delay(10000)
            doTheJob()

            println(  "after delay")

        }
    }

    internal open fun doTheJob() {

        println(javaClass.simpleName +  "before delay")
        //  Log.i("AsyncWork of: ", this::class.simpleName);
    }

    fun moveOn(e: Throwable?) {
        if (parentFeature == null) {
            completionBlock(e, businessResults)
        } else {
            if (e == null) {
                parentFeature.continueExecution(this)
            } else {
                parentFeature.onError(this, e)
            }
        }
    }

   internal fun getCoroutineJob() : Job{
       if(parentFeature == null){
           return CoroutineScope(Dispatchers.IO).launch {  }
       }else{
           return parentFeature.getCoroutineJob()
       }
   }
    constructor(completionBlock: (error: Throwable?, objects: MutableList<Any>?) -> Unit) : this(
        null
    ) {
        this.completionBlock = completionBlock
    }

    fun scream(voice: String): Int {
        //screeaaam
        return 23
    }
}
/*
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
}*/