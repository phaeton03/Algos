package exponential_algorithm

import kotlin.math.pow
import kotlin.math.sqrt

/**
 * 02. +1 байт. Реализовать рекурсивный O(2^N) и итеративный O(N) алгоритмы поиска чисел Фибоначчи.
 */

fun fiboIterative(n: Int): Long {
    var currF = 1L
    var prevF = 1L
    var nextF = 0L

    if (n == 1 || n == 2) {
        return 1L
    }

    for (i in 3 .. n) {
        nextF = prevF + currF
        prevF = currF
        currF = nextF
    }

    return nextF
}

fun fiboRecursive(n: Int): Long {

    if(n == 0) return 0
    if (n == 1 || n == 2) {
        return 1
    }

    return fiboRecursive(n - 1) + fiboRecursive(n - 2 )
}

/**
 * 13. +1 байт. Реализовать алгоритм поиска чисел Фибоначчи по формуле золотого сечения.
 */

fun fiboGoldenRatio(n: Int): Long {
    val F = (1 + sqrt(5.0)) / 2

    return (F.pow(n.toDouble()) / sqrt(5.0) + 0.5).toLong()
}

/**
 * 14. +1 байт. Написать класс умножения матриц, реализовать алгоритм возведения матрицы в степень через
 * двоичное разложение показателя степени, реализовать алгоритм поиска чисел Фибоначчи O(LogN) через умножение матриц,
 * используя созданный класс.
 */

fun fiboMatrix(n: Int): Long {
    val FiboQMatrix = Matrix( arrayOf(
        longArrayOf(1, 1),
        longArrayOf(1, 0)
    ))

 return powerMatrix(FiboQMatrix, n)[0,0]
}