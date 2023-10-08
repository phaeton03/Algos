package sorts

/**
 * JUNIOR
 * INS1. +1 байт. Реализовать алгоритм InsertionSort.
 */
fun insertSort(array: LongArray) {
    for (i in 1 until array.size) {
        var j = i - 1
        while (j >= 0 && array[j] > array[j + 1]) {
            swap(array, j, j + 1)
            j--
        }
    }
}

/**
 * Middle
 * INS2. +1 байт. Оптимизировать алгоритм InsertionSort, сделать сдвиг элементов вместо обмена.
 */

fun insertSortShift(array: LongArray) {
    for (i in 1 until array.size) {
        val k = array[i]
        var j = i - 1
        while (j >= 0 && array[j] > k) {
            array[j + 1] = array[j]
            j--
        }
        array[j + 1] = k
    }
}

/**
 * INS3. +1 байт. Оптимизировать алгоритм InsertionSort, сделать бинарный поиск места вставки.
 */

fun insertSortBinary(array: LongArray) {
    for (i in 1 until array.size) {
        var j = i - 1
        var k = array[i]
        val p = binarySearch(array, k, 0, i - 1)
        while (j >= p) {
            array[j + 1] = array[j]
            j--
        }
        array[j + 1] = k
    }
}

fun binarySearch(sortArray: LongArray, key: Long, low: Int, high: Int): Int {
    if (high <= low) {
        return if (key >= sortArray[low]) low + 1
        else low
    }

    val mid: Int = (low + high) / 2

    return if (key >= sortArray[mid]) binarySearch(sortArray, key, mid + 1, high)
    else binarySearch(sortArray, key, low, mid - 1)
}
