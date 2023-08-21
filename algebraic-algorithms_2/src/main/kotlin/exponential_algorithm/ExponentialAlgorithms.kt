package exponential_algorithm

/**
 * УРОВЕНЬ JUNIOR
 * Выполнить все пункты БЕЗ системы тестирования, проверить алгоритмы вручную.
 * 01. +1 байт. Реализовать итеративный O(N) алгоритм возведения числа в степень.
 */

fun powerIterativeJunior(n: Double, power: Long): Double {
    var result = 1.0
    for (p in 1..power) {
        result *= n
    }

    return result
}

/**
 * УРОВЕНЬ MIDDLE
 * Выполнить не менее 4 пунктов БЕЗ системы тестирования, проверить алгоритмы вручную.
 * 11. +1 байт. Реализовать алгоритм возведения в степень через домножение O(N/2+LogN) = O(N).
 * 2^100 = 2^64 * 2^36
 */

fun powerMultipleAlgo(n: Double, power: Long): Double {
    var i = 2L
    var currPower = i

    var result = n
    if (power == 0L) {
        return 1.0
    }
    if (power == 1L) {
        return n
    }

    while (i <= power) {
        result *= result
        currPower = i
        i *= 2
    }

    if (power > 2) {
        for (j in currPower until power) {
            result *= n
        }
    }

    return result
}

/**
 * 12. +1 байт. Реализовать алгоритм возведения в степень через
 * двоичное разложение показателя степени O(2LogN) = O(LogN). Решение через рекурсию
 */

fun powerBinaryRecursive(n: Double, power: Long): Double {
    if (power == 0L) {
        return 1.0
    }
    if (power == 1L) {
        return n
    }

    return if (power % 2 == 0L) {
        val x = powerBinaryRecursive(n, power / 2)
        x * x
    } else {
        n * powerBinaryRecursive(n, power - 1)
    }
}

/**
 * 12. +1 байт. Реализовать алгоритм возведения в степень через
 * двоичное разложение показателя степени O(2LogN) = O(LogN). Решение через цикл
 */

fun powerBinaryIterative(n: Double, power: Long): Double {
    var powerEnd = power
    var powerStart = 2L
    var totalResult = 1.0
    var oddVar = 1.0

    if (power == 0L) {
        return 1.0
    }
    if (power == 1L) {
        return n
    }

    if (power % 2 != 0L) {
        oddVar = n
        powerEnd--
    }

    while (powerEnd != 0L) {
        var powerStep = 2L
        var resultStep = n
        while (powerStep <= powerEnd) {
            resultStep *= resultStep
            powerStart = powerStep
            powerStep *= 2
        }
        powerEnd -= powerStart
        totalResult *= resultStep
    }

    return totalResult * oddVar
}

/**
 * экспоненциальное возведение в степень матриц
 */

fun powerMatrix(m: Matrix, power: Int): Matrix {
    if (power == 0) return m.toIdentity()
    if (power == 1) return m

    return if (power % 2 == 0) {
        val powerMatrix = powerMatrix(m, power / 2)
        powerMatrix * powerMatrix
    } else {
        m * powerMatrix(m, (power - 1))
    }
}