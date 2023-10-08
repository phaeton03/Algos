package queue

interface Queue<T> {
    fun enqueue(priority: Int, item: T)

    fun dequeu(): T?
}