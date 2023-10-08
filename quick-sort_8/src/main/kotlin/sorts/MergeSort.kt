package sorts

/**
 * JUNIOR
 * MS2. +1 байт. Реализовать алгоритм сортировки слиянием MergeSort.
 */

fun mergeSort(array: LongArray, L: Int = 0, R: Int = array.size - 1) {
    if (L >= R) return

    val m = (L + R) / 2

    mergeSort(array,0, m)
    mergeSort(array, m + 1, R)

    merge(array, L, R)
}

fun merge(array: LongArray, L: Int, R: Int) {
    val resultArray = LongArray(R - L + 1)

    val m = (L + R) / 2

    var a = L
    var b = m + 1
    var i = 0

    while (a <= m && b <= R) {
        if (array[a] <= array[b]) {
            resultArray[i++] = array[a++]
        } else {
            resultArray[i++] = array[b++]
        }
    }
    while (a <= m) {
        resultArray[i++] = array[a++]
    }
    while (b <= R) {
        resultArray[i++] = array[b++]
    }

    for (j in L .. R) {
        array[j] = resultArray[j - L]
    }
}