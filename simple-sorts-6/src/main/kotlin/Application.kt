import sorts.insertSortBinary
import sorts.insertSortShift
import sorts.shellSort
import kotlin.random.Random

fun main() {
    val arr = longArrayOf(2,5,0,15,100,50,33,55,60,1000, -100, 100000,400)
    val arr2 = Array (10) { Random.nextLong(from = -10_000, until = 10_000) }.toLongArray()
    val sortArr = Array (10) { it.toLong() }.toLongArray()

    println(arr2.contentToString())

  //  insertSortShift(arr2)
    shellSort(arr2)

    println(arr2.contentToString())
}
