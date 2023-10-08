package sorts

import util.swap


fun selectionSort(array: LongArray) {
    var j = array.size - 1
    while (j > 0) {
        swap(array, findMax(array, j), j)
        j--
    }
}

private fun findMax(array: LongArray, r: Int): Int {
    var maxInd = 0

    for (i in 1 .. r) {
        if (array[maxInd] < array[i]) {
            maxInd = i
        }
    }

    return maxInd
}