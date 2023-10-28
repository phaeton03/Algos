package queue

class IQueue<T> : Queue<T> {
    private val list: MutableList<T> = mutableListOf()

    override fun enqueue(priority: Int, item: T) {
        TODO("Not yet implemented")
    }

    override fun enqueue(item: T) {
        list.add(item)
    }

    override fun enqueue(items: List<T>) {
        list.addAll(items)
    }

    override fun dequeu(): T? {
        return list.removeLast()
    }

    override fun isEmpty(): Boolean {
        return list.isEmpty()
    }

    override fun last(): T {
        return list.last()
    }

    override fun clear() {
        list.clear()
    }

}