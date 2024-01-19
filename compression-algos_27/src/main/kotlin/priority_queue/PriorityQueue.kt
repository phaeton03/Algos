package priority_queue

import huffman_tree.Node
import util.swap

class PriorityQueue(
    val array: MutableList<Node> = mutableListOf(),
    val nodeComparator: Comparator<Node> = compareBy({ it.priority }, { it.sequenceNumber })
) {
    private var counter = 0

    fun enqueue(node: Node) {
        node.sequenceNumber = ++counter
        array.add(node)
        var currentIndex = array.size - 1
        //bottom up operation
        while (currentIndex >= 0)  {
            val parentIndex = (currentIndex - 1) / 2
            if (nodeComparator.compare(array[currentIndex], array[parentIndex]) >= 0) {
                break
            }
            swap(array, currentIndex, parentIndex)

            currentIndex = parentIndex
        }
    }

    fun dequeue(): Node {
        val node = array.removeFirst()
        heapify(0, array.size)

        return node
    }

    fun isEmpty(): Boolean {
        return array.isEmpty()
    }

    fun heapify(parentIndex: Int, size: Int) {
        val L = 2 * parentIndex + 1
        val R = 2 * parentIndex + 2

        var root = parentIndex

        if (L < size && nodeComparator.compare(array[L], array[root]) < 0) {
            root = L
        }
        if (R < size && nodeComparator.compare(array[R], array[root]) < 0) {
            root = R
        }

        if (root == parentIndex) return

        swap(array, parentIndex, root)

        heapify(root, size)
    }

    fun size(): Int = array.size
}