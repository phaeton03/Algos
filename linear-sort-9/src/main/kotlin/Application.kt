import sorts.bucketSort
import sorts.countingRadixSort
import sorts.countingSort
import kotlin.random.Random

fun main() {
    val arr2 = Array (100) { Random.nextInt(from = 0, until = 100_00) }.toIntArray()

    println(arr2.contentToString())

    val resultCountingSort = bucketSort(arr2)

    println(resultCountingSort.contentToString())
}
