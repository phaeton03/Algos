package sorts

import java.util.Collections.sort
import kotlin.math.max
import kotlin.math.min
import kotlin.random.Random

/**
 * Middle
 * ES1. +1 байта. Реализовать алгоритм внешеней сортировки ExternalSort первым способом, создание T разных файлов с последующим слиянием.
 */

val array = LongArray(100) { Random.nextLong(from = -10_000, until = 10_000) }

val inputFile = List(100) { Random.nextLong(from = -10_000, until = 10_000) }


fun externalSortV1(inputFile: List<Long>): LongArray {
    val chunkSize = 10
    val outputArr = LongArray(inputFile.size)

    var chunked = inputFile.chunked(chunkSize).map { it.toMutableList() }.toMutableList()
    chunked.forEach {
        sort(it)
    }


    for (i in outputArr.indices) {
        val iMin = minWithNull(chunked)
        outputArr[i] = chunked[iMin].removeFirst()
    }

    return outputArr
}

fun min(array: MutableList<MutableList<Long>>): Int {
    var iMin = 0
    while (array[iMin].isEmpty()) {
        iMin++
    }

    for (i in 1 until array.size) {
        if (array[i].isNotEmpty() && array[i][0] < array[iMin][0]) {
            iMin = i
        }
    }

    return iMin
}

fun minWithNull(array: MutableList<MutableList<Long>>): Int {
    var iMin = -1
    var min: Long? = null

    for (i in 0 until array.size) {
        if (array[i].isNotEmpty() && (min == null || min > array[i][0])) {
            min = array[i][0]
            iMin = i
        }
    }

    return iMin
}

/**
 * ES2. +1 байта. Реализовать алгоритм внешеней сортировки ExternalSort вторым способом, с двумя вспомогательными файлами.
 */
fun externalSortV2(inputFile: LongArray): LongArray {
    val chunkSize = 10
    val tempArr = LongArray(inputFile.size)
    val chunkArr = LongArray(chunkSize)

    val (auxFileSize1, auxFileSize2) = getAuxFileSize(inputFile.size, chunkSize)

    val auxFile1 = LongArray(auxFileSize1)
    val auxFile2 = LongArray(auxFileSize2)

    var isOdd = true
    var auxInd1 = 0
    var auxInd2 = 0
    for (i in tempArr.indices step chunkSize) {
        val chunkArrEndInd = min(chunkSize, inputFile.size - i) - 1

        for (j in 0..chunkArrEndInd) {
            chunkArr[j] = inputFile[i + j]
        }
        chunkArr.sort()

        if (isOdd) {
            for (chunkI in 0 until chunkSize) {
                if (auxInd1 > auxFileSize1 - 1) break
                auxFile1[auxInd1++] = chunkArr[chunkI]
            }
        } else {
            for (chunkI in 0 until chunkSize) {
                if (auxInd2 > auxFileSize2 - 1) break
                auxFile2[auxInd2++] = chunkArr[chunkI]
            }
        }
        isOdd = !isOdd
    }

    return mergeV2(auxFile1, auxFile2)
}

fun getAuxFileSize(size: Int, chunk: Int): Pair<Int, Int> {
    var auxFile1Size = 0
    var auxFile2Size = 0

    var isOdd = true
    var i = chunk

    while (i <= size) {
        if (isOdd) auxFile1Size += chunk else auxFile2Size += chunk
        i += chunk
        isOdd = !isOdd
    }

    if (size > i - chunk) {
        auxFile2Size += size - (i - chunk)
    }

    return Pair(max(auxFile1Size, auxFile2Size), min(auxFile1Size, auxFile2Size))
}

fun merge(arr1: LongArray, arr2: LongArray): LongArray {
    val outArr = LongArray(arr1.size + arr2.size)

    var i = 0
    var j = 0
    var k = 0

    while (i < arr1.size && j < arr2.size) {
        if (arr1[i] <= arr2[j]) {
            outArr[k++] = arr1[i++]
        } else {
            outArr[k++] = arr2[j++]
        }
    }

    while (i < arr1.size) {
        outArr[k++] = arr1[i++]
    }

    while (j < arr2.size) {
        outArr[k++] = arr2[j++]
    }

    return outArr
}

fun mergeV2(arr1: LongArray, arr2: LongArray): LongArray {
    val fileArr1 = mutableListOf<Long>()
    val fileArr2 = mutableListOf<Long>()

    if (arr2.isEmpty()) return arr1
    if (arr1.isEmpty()) return arr2

    var i = 0
    var j = 0
    var prevValue: Long = min(arr1[0], arr2[0])

    /**
     * I этап
     * Берем две колоды карт и сравниваем их друг с другом и с картой которую мы уже положили в колоду
     */
    while (i < arr1.size && j < arr2.size) {
        if (arr1[i] <= arr2[j] && prevValue <= arr1[i]) {
            fileArr1.add(arr1[i++])
            prevValue = fileArr1[i + j - 1]
        } else if (arr1[i] > arr2[j] && prevValue <= arr2[j]) {
            fileArr1.add(arr2[j++])
            prevValue = fileArr1[i + j - 1]
        } else {
            break
        }
    }
    /**
     * II этап
     * Одна колода карт закончилась. Кладем в нее карты сравнивая с текущей картой в колоде
     */
    while (i < arr1.size && j != arr2.size - 1) {
        if (prevValue <= arr1[i]) {
            fileArr1.add(arr1[i++])
            prevValue = fileArr1[i + j - 1]
        } else break
    }

    /**
     * II этап
     * Одна колода карт закончилась. Кладем в нее карты сравнивая с текущей картой в колоде
     */
    while (j < arr2.size && i != arr1.size - 1) {
        if (prevValue <= arr2[j]) {
            fileArr1.add(arr2[j++])
            prevValue = fileArr1[i + j - 1]
        } else break
    }

    /**
     * III этап. Кладем оставшиеся карты
     */
    while (i < arr1.size) {
        fileArr2.add(arr1[i++])
    }

    /**
     * III этап. Кладем оставшиеся карты
     */
    while (j < arr2.size) {
        fileArr2.add(arr2[j++])
    }

    return mergeV2(fileArr1.toLongArray(), fileArr2.toLongArray())
}