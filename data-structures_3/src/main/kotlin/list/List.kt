package list

interface List<T> {
    fun add(element: T)

    fun size(): Long

    fun get(): T?

    fun get(index: Int): T?
}