package tree.cartesian_tree

import tree.Node
import kotlin.io.path.fileVisitor

/**
 * УРОВЕНЬ MIDDLE.
 * +3 байта. Создать Декартово дерево поиска.
 */
class Treap {
    var root: Node? = null
    fun merge(leftTree: Node?, rightTree: Node?): Node {
        if (leftTree == null) return rightTree!!
        if (rightTree == null) return leftTree

        return if (leftTree.priority > rightTree.priority) {
            leftTree.R = merge(leftTree.R, rightTree)

            leftTree
        } else {
            rightTree.L = merge(leftTree, rightTree.R)

            rightTree
        }
    }

    fun split(node: Node? = root, key: Int): Pair<Node?, Node?> {
        if (node == null) return Pair(null, null)

        return if (key > node.key) {
            val (leftTree, rightTree) = split(node.R, key)
            node.R = leftTree

            Pair(node, rightTree)
        } else {
            val (leftTree, rightTree) = split(node.L, key)
            node.L = rightTree

            Pair(leftTree, node)
        }
    }

    fun add(node: Node? = root, key: Int): Node {
        val insertNode = Node(key = key)
        val (leftTree, rightTree) = split(node, key)

        return merge(leftTree, merge(insertNode, rightTree))
    }

    fun remove(node: Node? = root, key: Int): Node? {
        if (node == null) return null

        if (key == node.key) return merge(node.L, node.R)

        if (key < node.key) {
           node.L = remove(node.L, key)
        } else {
            node.R = remove(node.R, key)
        }

        return node
    }
}