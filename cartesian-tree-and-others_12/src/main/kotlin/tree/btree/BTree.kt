package tree.btree

import tree.Node
import kotlin.random.Random

/**
 * УРОВЕНЬ SENIOR.
 * +5 байт. Реализовать B-дерево.
 */
class BTree {
    val root: BNode? = null

    fun search(node: BNode? = root, key: Int): BNode? {
        if (node == null) return null

        if (node.keys.binarySearch(key) >= 0) return node


        var i = 0
        while (i < node.keys.size && node.keys[i] != null) {
            if (node.keys[i]!! > key) {
                break
            }
            i++
        }

        return search(node.children[i], key)
    }



}

class BNode(
    var size: Int = 3,
    var keys: Array<Int?> = Array(size) { null },
    var children: Array<BNode?> = Array(size) { null }
)