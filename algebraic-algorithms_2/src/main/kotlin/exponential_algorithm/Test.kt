package exponential_algorithm

/**
 * Найдите с помощью простейшего микрокалькулятора количество единичных бит в числе 1234567890.
 */

fun countBit(num: Long): Long {
    var varNum = num
    var count = 0L
    while (varNum > 0) {
        println(varNum and 1)
        if (varNum and 1 == 1L) {
            count++
        }
        varNum = varNum shr 1
    }

    return count
}