package kmp

const val ALPHABET = "ABCDEFGH"

/**
 * Составить конечный автомат и прохождение по нему для поиска шаблона в строке, 2 байта.
 */
fun createDelta(pattern: String): Array<Array<Int>> {
    val delta = Array(pattern.length) { Array(ALPHABET.length) { 0 } }
    var q = 0

    while (q < pattern.length - 1) {
        for (ch in ALPHABET) {
            val line = pattern.left(q) + ch
            var k = q + 1
            while (line.right(k) != pattern.left(k)) {
                k--
            }
            delta[k][ch.code - ALPHABET[0].code] = k
        }
        q++
    }

    return delta
}

/**
 * Самостоятельно написать функцию вычисления префикс-функции, медленный вариант, 2 байта.
 */
fun createPiSLow(pattern: String): Array<Int> {
    val pi = Array(pattern.length + 1) { 0 }

    for (i in 1 .. pattern.length) {
        val line = pattern.left(i)
        for (j in 1 until i) {
            if (line.left(j) == line.right(j)) {
                pi[i] = j
            }
        }
    }

    return pi
}

/**
 * Переписать алгоритм быстрого вычисления префикс-функции и разобраться в нём, 2 байта.
 */
fun createPiFast(pattern: String): Array<Int> {
    val pi = Array(pattern.length + 1) { 0 }

    for (q in 1 until pattern.length) {
        var len = pi[q]
        while (len != 0 && pattern[q] != pattern[len]) {
            len = pi[len]
        }
        if (pattern[q] == pattern[len]) {
            len++
        }
        pi[q + 1] = len
    }
    return pi
}


fun search(text: String, delta: Array<Array<Int>>, pattern: String): Int {
    var q = 0
    for (i in text.indices) {
        q = delta[q][text[i].code - ALPHABET[0].code]
        if (q == pattern.length) {
            return i - pattern.length + 1
        }
    }

    return -1
}

fun String.left(n: Int): String {
    return substring(0, n)
}

fun String.right(n: Int): String {
    return substring(this.length - n)
}