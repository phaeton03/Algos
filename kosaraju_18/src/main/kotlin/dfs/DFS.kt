package dfs

import queue.IQueue
import queue.Queue

class DFS(
    val visited: MutableSet<Int> = mutableSetOf(),
    var graph: Array<List<Int>>,
    var stack: Queue<Int> = IQueue()
) {
    fun dfsRecursive(vert: Int) {
        if (visited.contains(vert)) {
            return
        }
        visited.add(vert)

        for (neighbour in graph[vert]) {
            dfsRecursive(neighbour)
        }
        stack.enqueue(vert)
    }

    fun dfsIterative(vert: Int) {
        val queue: Queue<Int> = IQueue()
        queue.enqueue(vert)

        while (!queue.isEmpty()) {
            val vertDequeue = queue.dequeu()!!
            if (!visited.contains(vertDequeue)) {
                visited.add(vertDequeue)
                stack.enqueue(vertDequeue)
                for (neighbour in graph[vertDequeue]) {
                    queue.enqueue(neighbour)
                }
            }
        }
    }

    fun clearAllVisitedVertex() {
        visited.clear()
    }
}