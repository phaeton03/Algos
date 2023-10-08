package exponential_algorithm

import kotlin.math.sqrt

/**
 * Реализовать алгоритм поиска количества простых чисел через перебор делителей, O(N^2).
 */

fun countPrimeNumbers(n: Long): Long {
    if (n == 1L) return 1

    var primeNumber = 2L

    for (i in 1..n) {
        for (j in 2 until i) {
            if (i % j == 0L) {
                break
            }
            if (j == i - 1) {
                primeNumber++
            }
        }
    }

    return primeNumber
}

/**
 * 15. +1 байт. Реализовать алгоритм поиска простых чисел с оптимизациями поиска и
 * делением только на простые числа, O(N * Sqrt(N) / Ln (N)).
 */
fun countPrimeNumbersV2(n: Long): Long {
    if (n == 1L) return 1

    val primeList = mutableListOf<Long>(2)

    for (i in 3..n step 2) {
        val limit = sqrt(i.toDouble()).toLong()
        var isPrime = true

        for (p in primeList) {
            //           println("i: $i, p: $p")
            if (p > limit) break
            if (i % p == 0L) {
                isPrime = false
                break
            }
        }
        if (isPrime) {
            primeList.add(i)
        }
    }

    return primeList.size + 1L
}

/**
 * 16. +1 байт. Реализовать алгоритм "Решето Эратосфена" для быстрого поиска простых чисел O(N Log Log N).
 */

fun countPrimeNumbersEratosphenV2(n: Long): Long {
    val numsArr = BooleanArray(n.toInt() + 1) { true }
    var count = 1L

    var p = 2
    while (p <= n) {
        if (numsArr[p]) {
            count++
            var j = p * p
            while (j <= n) {
                numsArr[j] = false
                j += p
            }
        }
        p++
    }

    return count
}

/**
 * 17. +1 байт. Решето Эратосфена с оптимизацией памяти, используя битовую матрицу,
 * сохраняя по 32 значения в одном int, хранить биты только для нечётных чисел.
 */

fun countPrimeNumbersBitMatrix(n: Int): Int {
    val primeSiege = IntArray((n shr 6) + 1) { -1 }
    var count = 2
    for (i in 3..n step 2) {
        if (primeSiege[i shr 6] and (1 shl (i shr 1 and 31)) != 0) {
            count++
            for (j in (i * i)..n step (i shl 1)) {
                primeSiege[j shr 6] = primeSiege[j shr 6] and (1 shl (j shr 1 and 31)).inv()
            }
        }
    }
    return count
}

/**
 * 18. +1 байт. Решето Эратосфена со сложностью O(n), см. дополнительный материал.
 */

fun countPrimeNumbersN(n: Int): Int {
    val allNumbers = IntArray(n + 1) { 0 }
    // простые числа распределны примерно ln(n) на больших n
    val primeNumbers = mutableListOf<Int>()

    var countPrime = 1
    for (i in 2..n) {
        val divider = allNumbers[i]
        if (divider == 0) {
            countPrime++
            primeNumbers.add(i)
        }
        for (p in primeNumbers) {
            if (p * i > n) break
            if (divider != 0 && p > divider) break
            allNumbers[p * i] = p
        }
    }
    return countPrime
}
