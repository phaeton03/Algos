package sorts

/**
 * УРОВЕНЬ JUNIOR
 * - **Выполнить все пункты**
 *     1. **BUB1**: +1 байт. Реализовать алгоритм BubbleSort.
 */
fun bub1(array: LongArray) {
    for (j in array.size - 1 downTo 1) {
        for (i in 0 until j) {
            if (array[i] > array[i + 1]) {
                swap(array, i, i + 1)
            }
        }
    }
}

/**
 * УРОВЕНЬ MIDDLE.
 * Выполнить не менее двух пунктов и обязательно последний пункт.
 * BUB2. +1 байт. Оптимизировать алгоритм BubbleSort.
 */

fun bub2(array: LongArray) {
    for (j in array.size - 1 downTo 1) {
        var flag = true
        for (i in 0 until j) {
            if (array[i] > array[i + 1]) {
                swap(array, i, i + 1)
                flag = false
            }
        }
        if (flag) break
    }
}

fun swap(array: LongArray, el1: Int, el2: Int) {
    val temp = array[el1]
    array[el1] = array[el2]
    array[el2] = temp
}