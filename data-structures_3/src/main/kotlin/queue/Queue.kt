package queue

interface Queue<T> {
    fun enqueue(priority: Int, item: T)

    fun dequeu(): T?

    fun enqueue(item: T)

    fun enqueue(items: List<T>)

    fun isEmpty(): Boolean

    fun last(): T

    fun clear()

}