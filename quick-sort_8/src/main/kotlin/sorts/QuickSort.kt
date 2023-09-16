package sorts

import util.swap

/**
 * УРОВЕНЬ JUNIOR.
 * Выполнить все пункты.
 * QS1. +1 байт. Реализовать алгоритм быстрой сортировки QuickSort.
 */
fun quickSort(array: LongArray, L: Int = 0, R: Int = array.size - 1) {
    if (L >= R) return

    val pivot = array[R]

    var m = L - 1

    for (i in L until   R) {
        if (array[i] <= pivot) {
            swap(array, ++m, i)
        }
    }

    swap(array, ++m, R)

    quickSort(array,0, m - 1)
    quickSort(array,m + 1, R)
}