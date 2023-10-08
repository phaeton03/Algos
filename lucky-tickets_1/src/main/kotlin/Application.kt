/*
Билет с 2N значным номером считается счастливым,
если сумма N первых цифр равна сумме последних N цифр.
Посчитать, сколько существует счастливых 2N-значных билетов.

Начальные данные: число N от 1 до 10.
Вывод результата: количество 2N-значных счастливых билетов.
 */

fun main() {
    println(luckyTickets(10 ))
}


fun luckyTickets(n: Int): Long {

    return calculateSum(n).map { it * it }.reduce { acc, sum -> sum + acc }
}

fun calculateSum(n: Int): List<Long> {
    var prevSum = List<Long>(10) { 1 }
    var currentSumArray = mutableListOf<Long>()

    if (n == 0) {
        return emptyList()
    }
    if(n == 1) {
        return prevSum
    }
    var cursorSum = 0L
    for (digit in 2..n) {
        currentSumArray = mutableListOf()
        for (i in 0..9 * digit) {
            for (j in i - 9..i) {
                cursorSum += prevSum.getOrElse(j) { 0 }
            }
            currentSumArray.add(cursorSum)
            cursorSum = 0
        }
        prevSum = currentSumArray
    }

    return currentSumArray
}
