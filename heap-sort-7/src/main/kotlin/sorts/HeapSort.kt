package sorts

import util.swap

fun heapSort(array: LongArray) {
    var root = array.size / 2 - 1

    while (root >= 0) {
        heapify(array, root, array.size)
        root--
    }

    var j = array.size - 1
    while (j >= 0) {
        swap(array, 0, j)
        heapify(array, 0, j)
        j--
    }
}

private fun heapify(array: LongArray, parentIndex: Int, size: Int) {
    val L = 2 * parentIndex + 1
    val R = 2 * parentIndex + 2

    var root = parentIndex

    if (L < size && array[L] > array[root]) root = L
    if (R < size && array[R] > array[root]) root = R

    if (root == parentIndex) return

    swap(array, parentIndex, root)

    heapify(array, root, size)
}
