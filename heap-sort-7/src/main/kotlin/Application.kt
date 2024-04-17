import sorts.heapSort
import sorts.heapify
import kotlin.random.Random

fun main() {
//    val array = LongArray(20) { Random.nextLong(from = -0, until = 10) }
//
//    println(array.contentToString())
//
//    heapSort(array)
//
//    println(array.contentToString())

    val array2 = longArrayOf(5L, 15L, 10L, 10L, 2L, 17L, 2L, 2L, 3L, 4L, 0L, 5L)

    println(array2.contentToString())
    var root = array2.size / 2 - 1
    while (root >= 0) {
        heapify(array2, root, array2.size)
        root--
    }

    println(array2.contentToString())
}
