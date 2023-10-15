package tree.splay

import tree.Node

/**
 * УРОВЕНЬ JUNIOR.
 * SPLAY. +2 байта. Реализовать расширяющееся дерево поиска.
 */
class SplayTree {
    var root: Node? = null
    fun insert(node: Node? = root, key: Int): Node {
        if (root == null) {
            root = Node(key = key)
        }

        if (node == null) {
            return Node(key = key)
        }

        if (key < node.key) {
            node.L = insert(node.L, key)
            return rotateRight(node)
        } else if (key > node.key) {
            node.R = insert(node.R, key)
            return rotateLeft(node)
        }

        return node
    }

    fun search(node: Node? = root, key: Int): Node? {
        if (node == null) return null

        if (key < node.key) {
           val searchNode = search(node.L, key)

           return searchNode?.let { rotateRight(it) }
        } else if (key > node.key) {
            val searchNode = search(node.R, key)

            return searchNode?.let { rotateLeft(node) }
        }

        return node
    }

    fun remove(node: Node?, key: Int): Node? {
        if (node == null) return null

        if (node.key == key) {
            return join(node.L, node.R)
        }
        if (key < node.key) {
            node.L = remove(node.L, key)
        }
        if (key > node.key) {
             node.R = remove(node.R, key)
        }

        return node
    }

    private fun join(p: Node?, q: Node?): Node? {
        if (p == null) return q
        if (q == null) return p

        val minNodeQ = findMin(q)
        minNodeQ.L = p

        return q
    }

    private fun findMin(p: Node): Node {
        val leftNode = p.L ?: return p

        return findMin(leftNode)
    }



    private fun rotateLeft(p: Node): Node {
        val rightS = p.R ?: return p

        val leftGS = rightS.L
        rightS.L = p
        rightS.R = leftGS

        return rightS
    }

    private fun rotateRight(p: Node): Node {
        val leftS = p.L ?: return p

        val rightGS = leftS.R
        leftS.R = p
        leftS.L = rightGS

        return leftS
    }
}