package util

fun swap(array: LongArray, el1: Int, el2: Int) {
    val temp = array[el1]
    array[el1] = array[el2]
    array[el2] = temp
}