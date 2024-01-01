package tree.splay

import tree.Node
import kotlin.random.Random

/**
 * RANDT. +2 байта. Реализовать рандомизированное дерево поиска.
 * +2 байта. Протестировать.
 */
class RandomSplayTree {
    var root: Node? = null

    fun find(node: Node? = root, key: Int): Node? {
        if (node == null) return null

        if (key == node.key) return node

        return if (key < node.key) {
            find(node.L, key)
        } else {
            find(node.R, key)
        }
    }

    fun insert(node: Node? = root, key: Int): Node {
         if (root == null) return Node(key = key)

         if (node == null) return Node(key = key)

         if (key < node.key) {
             node.L = insert(node.L, key)
         }
         if (key > node.key) {
             node.R = insert(node.R, key)
         }
         fixSize(node)
         return node
    }

    fun insertRoot(node: Node? = root, key: Int): Node {
        if (root == null) return Node(key = key)

        if (node == null) return Node(key = key)

        if (key < node.key) {
            node.L = insertRoot(node.L, key)
            return rotateRight(node)
        }
        if (key > node.key) {
            node.R = insertRoot(node.R, key)
            return rotateLeft(node)
        }
        fixSize(node)
        return node
    }


    fun insertRandom(node: Node? = root, key: Int): Node {
        if (node == null) {
            root = Node(key = key)
            return Node(key = key)
        }

        return if (Random.nextInt(node.size + 1) == 0) {
            insertRoot(node, key)
        } else {
            insert(node, key)
        }
    }

    fun join(leftTree: Node?, rightTree: Node?):Node {
        if (leftTree == null) return rightTree!!
        if (rightTree == null) return leftTree

        if (Random.nextInt(leftTree.size + rightTree.size) < leftTree.size) {
            leftTree.R = join(leftTree.R, rightTree)
            fixSize(leftTree)

            return leftTree
        } else {
            rightTree.L = join(leftTree, rightTree.L)
            fixSize(rightTree)

            return rightTree
        }
    }

    fun remove(p: Node?, key: Int): Node? {
        if (p == null) return null

        if (key == p.key) {
            return join(p.L, p.R)
        }
        if (key < p.key) {
            p.L = remove(p.L, key)
        } else {
            p.R = remove(p.R, key)
        }

        return p
    }

    private fun fixSize(node: Node) {
        val sizeL: Int = node.L?.size ?: 0
        val sizeR: Int = node.R?.size ?: 0

        node.size = sizeL + sizeR + 1
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