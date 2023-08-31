import queue.PriorityQueue

/*
Билет с 2N значным номером считается счастливым,
если сумма N первых цифр равна сумме последних N цифр.
Посчитать, сколько существует счастливых 2N-значных билетов.

Начальные данные: число N от 1 до 10.
Вывод результата: количество 2N-значных счастливых билетов.
 */

fun main() {
    val priorityQueue: PriorityQueue<Int> = PriorityQueue()

    priorityQueue.enqueue(0, 10)
    priorityQueue.enqueue(50, 100)
    priorityQueue.enqueue(50, 101)
    priorityQueue.enqueue(50, 102)
    priorityQueue.enqueue(80, 80)
}

