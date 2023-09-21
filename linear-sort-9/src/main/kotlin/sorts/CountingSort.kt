package sorts

/**УРОВЕНЬ JUNIOR.
 *Выполнить все пункты.
 *CS1. +1 байт. Реализовать алгоритм сортировки подсчётом CountingSort.
 */

fun countingSort(array: IntArray): IntArray {
    val digitArray = Array(max(array) + 1) { 0 }
    val resultArray = Array(array.size) { 0 }

    array.forEach { digitArray[it]++ }

    var sum = 0
    for (i in digitArray.indices) {
        sum += digitArray[i]
        digitArray[i] = sum
    }

    for (i in array.size - 1 downTo 0) {
        val value = array[i]
        val sortInd = --digitArray[value]
        resultArray[sortInd] = value
    }

    return resultArray.toIntArray()
}


fun max(array: IntArray): Int {
    var max = array[0]

    for (i in 1 until array.size) {
        if (max < array[i]) max = array[i]
    }

    return max
}

/**
 * RS1. +1 байт. Реализовать алгоритм поразрядкнй сортировки RadixSort.
 */
fun countingRadixSort(array: IntArray): IntArray {
    val max = array.max()
    var resultArray = array.copyOf()

    val k = countDigits(max)
    var exp = 1

    for (i in 0 until k) {
        resultArray = countingSortForRadix(resultArray, exp)
        exp *= 10
    }

    return resultArray
}

fun countingSortForRadix(array: IntArray, exp: Int): IntArray {
    val resultArray = Array(array.size) { 0 }
    val digitArray = Array(10) { 0 }

    array.forEach {
            digitArray[(it / exp) % 10]++
        }

    var sum = 0
    for (i in digitArray.indices) {
        sum += digitArray[i]
        digitArray[i] = sum
    }

    for (i in array.size - 1 downTo 0) {
        val value = (array[i] / exp) % 10
        val sortInd = --digitArray[value]
        resultArray[sortInd] = array[i]
    }

    return resultArray.toIntArray()
}

fun countDigits(num: Int): Long {
    var count: Long = 1
    var remainder = num / 10
    while (remainder != 0) {
        count++
        remainder /= 10
    }

    return count
}