import sorts.externalSortV1
import sorts.externalSortV2
import sorts.mergeSort
import sorts.quickSort
import kotlin.random.Random

fun main() {
    val arr2 = Array (100) { Random.nextLong(from = -10_000, until = 10_000) }.toLongArray()

    println(arr2.contentToString())

    println(externalSortV2(arr2).contentToString())

}
