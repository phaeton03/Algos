package sorts

/**
 * Junior
 * SHS1. +1 байт. Реализовать алгоритм ShellSort.
 */

fun shellSort(array: LongArray) {
    var gap = array.size / 2
    while (gap > 0) {
        for (i in gap until array.size) {
            var j = i - gap
            while (j >= 0 && array[j] > array[j + gap]) {
                swap(array, j, j + gap)
                j -= gap
            }
        }
        gap /= 2
    }
}
