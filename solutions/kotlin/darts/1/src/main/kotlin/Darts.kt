import kotlin.math.pow
import kotlin.math.sqrt

object Darts {

    fun score(x: Number, y: Number): Int {
        val distance = sqrt(x.toDouble().pow(2) + y.toDouble().pow(2))
        return when {
            (distance <= 1) -> 10
            (distance <= 5) -> 5
            (distance <= 10) -> 1
            else -> 0
        }
    }
}
