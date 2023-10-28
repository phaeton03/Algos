package queue

class PriorityQueue<T>: Queue<T> {
    private val MAX_PRIORITY = 100
    private val list = MutableList<MutableList<T>>(MAX_PRIORITY) { mutableListOf() }
    private var listHighestPriority = list[MAX_PRIORITY - 1]
    override fun enqueue(priority: Int, item: T) {
        list[priority].add(item)
    }

    override fun dequeu(): T? {
        if (listHighestPriority.isEmpty()) {
            listHighestPriority = (MAX_PRIORITY - 1 downTo 0)
                .find { list[it].isNotEmpty() }
                ?.let { list[it] } ?: listHighestPriority
        }
        //Оставлю чтобы было понятнее что выполняет верхняя часть
//            for (i in MAX_PRIORITY - 1 downTo 0) {
//                if (list[i].isNotEmpty()) {
//                    listHighestPriority = list[i]
//                    break
//                }
//            }
//        }
        return if (listHighestPriority.isNotEmpty()) {
            listHighestPriority.removeAt(listHighestPriority.size - 1)
        } else {
            null
        }
    }

    override fun enqueue(item: T) {
        TODO("Not yet implemented")
    }

    override fun isEmpty(): Boolean {
        TODO("Not yet implemented")
    }

    override fun enqueue(items: List<T>) {
        TODO("Not yet implemented")
    }

    override fun last(): T {
        TODO("Not yet implemented")
    }

    override fun clear() {
        TODO("Not yet implemented")
    }

}