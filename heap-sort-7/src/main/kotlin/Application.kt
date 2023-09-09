import sorts.heapSort
import kotlin.random.Random

fun main() {
    val array = LongArray(20) { Random.nextLong(from = -10_000, until = 10_000) }

    println(array.contentToString())

    heapSort(array)

    println(array.contentToString())
}
